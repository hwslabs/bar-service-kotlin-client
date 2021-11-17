/*
 * Copyright 2020 gRPC authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hypto.hws.services.starter

import com.hypto.hws.services.starter.BarGrpcKt.BarCoroutineStub
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.delay
import java.io.Closeable
import java.util.concurrent.TimeUnit

class BarClient(private val channel: ManagedChannel) : Closeable {
    private val stub: BarCoroutineStub = BarCoroutineStub(channel)

    suspend fun orderDrink(name: String, type: DrinkType, amountInMl: Long): Bill {
        val drink = Drink.newBuilder()
            .setName(name)
            .setType(type)
            .setAmountInMl(amountInMl)

        val request = DrinkRequest.newBuilder()
            .setDrink(drink)
            .build()

        println("Ordered at Bar:\n$request")
        val response = stub.orderDrink(request)
        println("Received from Bar:\n$response")

        return response.bill
    }

    suspend fun payBill(bill: Bill, paymentAmount: Long) : Long {
        val request = PaymentRequest.newBuilder()
            .setBill(bill)
            .setPaymentAmount(paymentAmount)
            .build()

        println("Paying at Bar:\n$request")
        val response = stub.payBill(request)
        println("Received from Bar:\n$response")

        return response.balanceAmount
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}

/**
 * Bar Client orders some drinks one by one and pays the bills
 * one by one.
 *
 * Also attempts to pay a wrong bill to test the server.
 */
suspend fun main() {
    val port = 50051
    //TODO: Fix this hardcode
    val address = "bar.hypto.co.in"

    val channel = ManagedChannelBuilder
        .forAddress(address, port)
        .build()

    val client = BarClient(channel)
    
    var wallet: Long = 1000

    val bills = mutableListOf<Bill>()
    bills.add(
        client.orderDrink(
            "Johnnie Walker Blue Label",
            DrinkType.WHISKY,
            30)
    )
    delay(2000)

    bills.add(
        client.orderDrink(
            "Absolut",
            DrinkType.VODKA,
            60)
    )
    delay(2000)

    bills.add(
        client.orderDrink(
            "Kingfisher Ultra",
            DrinkType.BEER,
            300)
    )
    delay(2000)

    bills.forEach {
        wallet -= client.payBill(it, wallet)
        delay(2000)
    }

    val dummyBill = Bill.newBuilder()
        .setOrderId("123")
        .setOrderAmount(100)
        .build()
    client.payBill(dummyBill, wallet)
    delay(2000)

    println("Final wallet balance is: $wallet")
}

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

package com.hypto.hws.services

import com.hypto.hws.services.StarterGrpcKt.StarterCoroutineStub
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import java.io.Closeable
import java.util.concurrent.TimeUnit

class StarterClient(private val channel: ManagedChannel) : Closeable {
    private val stub: StarterCoroutineStub = StarterCoroutineStub(channel)

    suspend fun printText(text: String): String {
        val request = InputRequest.newBuilder()
            .setText(text)
            .build()

        println("Input Request: $request")
        val response = stub.printText(request)
        return response.text
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}

/**
 * Print Hello World!
 */
suspend fun main() {
    val port = 50051
    val channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build()
    val client = StarterClient(channel)

    val responseText = client.printText("Hello World!")
    println("Output Response: $responseText")
}

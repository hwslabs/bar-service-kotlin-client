# gRPC Kotlin starter

![](logo/hypto_grpc_kotlin.png)

## Overview

This directory contains a simple bar service client written in Kotlin against generated models (protos)  
You can find detailed instructions for running the client below

- **Bar Service Client** using gRPC and Kotlin. For details, see the [project on github](https://github.com/hwslabs/bar-service-kotlin-client).

## File organization

The starter sources are organized into the following top-level folders:

- [bar-service-models](bar-service-models): `.proto` files for generating the stubs
- [bar-service-kotlin-stubs](bar-service-kotlin-stubs): regular Java & Kotlin stub artifacts from [protos][]
- [bar-service-client](bar-service-client): Kotlin clients based on regular [stub](bar-service-kotlin-stubs) artifacts

## Set up and run the client on macOS

- <details>
  <summary>Install Homebrew</summary>

  Download and install Homebrew:

  ```sh
  /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
  ```

- <details>
  <summary>Install JDK</summary>

  Install any version of JDK (8 preferred):

  ```sh
  brew install openjdk@8
  ```

  Add the installed version of JDK to your path through .zshrc or .bash_profile

  ```sh
  echo 'export PATH="/usr/local/opt/openjdk@8/bin:$PATH"' >> ~/.zshrc
  source ~/.zshrc
  ```

  or

  ```sh
  echo 'export PATH="/usr/local/opt/openjdk@8/bin:$PATH"' >> ~/.bash_profile
  source ~/.bash_profile
  ```

- <details>
  <summary>Clone the project</summary>

  Clone the project recursively cloning all submodules

  ```sh
  git clone https://github.com/hwslabs/grpc-kotlin-starter.git --recurse-submodules
  ```

  Navigate into the project:
  ```sh
  cd grpc-kotlin-starter
  ```

- <details>
  <summary>Run the client</summary>

  Run the client which will make requests to the server using 50051 port:

  ```sh
  ./gradlew bar-service-client:BarClient
  ```

[grpc.io Kotlin/JVM]: https://grpc.io/docs/languages/kotlin/
[Quick start]: https://grpc.io/docs/languages/kotlin/quickstart/
[Basics tutorial]: https://grpc.io/docs/languages/kotlin/basics/
[protos]: protos
[stub]: stub

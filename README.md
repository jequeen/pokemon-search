# Pokemon Search App

This repository contains the source code for a Pokemon search application. The app allows users to search for a Pokemon by name and view its details, including its height, weight, and moves. If you are unfamiliar with Pokemon, for sample input try "Pickachu", "Charizard", or "Mew"

## Features

- Search for a Pokemon by name
- Display Pokemon details (name, height, weight)
- View a list of moves for a Pokemon
- Navigate between screens using the Compose Navigation library

## Modules

The repository is organized into the following modules:

- **app**: Contains the main Android application code.
- **data**: Provides data-related functionality, including the data models and repository.
- **network**: Handles the network communication with the Pokemon API.
- **screens**: Contains the UI screens of the application.
- **di**: Includes the Dagger Hilt modules for dependency injection.

## Dependencies

The repository utilizes the following libraries and frameworks:

- Jetpack Compose: For building the user interface.
- Compose Navigation: For handling screen navigation within the app.
- Retrofit: For making network requests to the Pokemon API.
- Dagger Hilt: For dependency injection.
- Kotlin Coroutines: For managing asynchronous operations.
- Coil: For loading and displaying images.
- ViewModel: For managing the view models of the screens.

## How to Run

To run the application, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the project on an Android emulator or device.

Make sure you have the necessary dependencies and Android SDK installed.

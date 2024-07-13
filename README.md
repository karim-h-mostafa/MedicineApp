# Android Task Repository

## Overview

This repository contains a simple Android application that demonstrates key skills in modern Android development. The app features a login screen, personalized greetings based on the time of day, and a display of medicine information using a clean architecture approach.

## Features

1. **Login Screen**: 
   - Allows users to log in without validation.
  
2. **Personalized Greeting**:
   - Greets the user based on the time of day with a message displaying the username/email entered.
   - Shows medicine details (name, dose, strength) in a RecyclerView.
   - Medicine data is fetched from a mock API created using Mocky.io and store it to room using view model.
   - Tapping on each medicine card opens a detailed view with the same information.
  
3. **Medicine Display**:
   - show the tabbed medicine card

5. **Data Storage**:
   - Utilizes Room Database to store any relevant data.

6. **Unit Testing**:
   - Includes at least 3 unit tests covering various use cases.

## Technical Skills Demonstrated

- **Clean Architecture**: 
  - Organized code into distinct layers for separation of concerns.

- **Multi-Module Structure**:
  - The app consists of 4 modules:
    - `app`: Presentation layer and Dependency Injection.
    - `core`: Utility functions and classes.
    - `data`: Data source layer.
    - `domain`: Business logic layer.

- **MVVM**: 
  - Implements the Model-View-ViewModel pattern for better state management.

- **Jetpack Components**:
  - Utilizes Jetpack Navigation, ViewModel, Room, and Jetpack Compose for UI.

- **Hilt**: 
  - Uses Hilt for dependency injection to simplify dependency management.

- **Single-Screen App**: 
  - Designed as a single-screen application for streamlined user experience.

- **SOLID Principles**: 
  - Follows SOLID design principles to ensure code maintainability and scalability.

## How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/repo-name.git

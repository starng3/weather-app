# Weather App - Mobile Coding Challenge for JPMC (Android)

This is a simple weather application utilizing the Open Weather API. (https://openweathermap.org/)

## Basic Use Case

1. User enters a city of their choice into the search bar and clicks Search.
2. App displays a list of cities based on the query. User selects a city.
3. Weather data displays on the screen showing the following:
   * City name
   * Image of a weather icon corresponding to the weather condition
   * Current temperature (in Fahrenheit)
   * Feels like temperature (in Fahrenheit)

## Current Features
A quick breakdown on how the requirements were fulfilled:

* Users can search for a city
* Currently only a few fields of the weather data are displayed but can easily be extended if needed
  * Full JSON response from weather API is captured
* Weather icons are displayed
  * Icons are cached in memory with the Glide library
* When app is launched, the last city searched is auto loaded with up-to-date weather data
* When app is launched (for the first time), weather data for the device's current location is shown
  * location permissions are asked at runtime

## Technologies Used

* All code was written in Kotlin
* Utilized MVVM package structure and architecture
* REST API calls were utilized using Retrofit
* Unit tests were written using JUnit 
* UI was created completely using Jetpack Compose
* Utilized Kotlin Coroutines for networking
* Mocking in Unit tests was done using mockk

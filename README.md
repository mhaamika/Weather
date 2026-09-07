
# Weather Application

A Java-based weather application that accepts a user-entered city and state, retrieves the location's coordinates using the Open-Meteo Geocoding API, and uses those coordinates to retrieve current weather information.

## Project Overview

I built this project to gain hands-on experience working with REST APIs in Java and to practice making HTTP requests, processing API responses, and using external data in a Java application.

The application first uses the Open-Meteo Geocoding API to find the latitude and longitude of the entered location. It then uses those coordinates to request current weather information from the Open-Meteo Weather API.

## Features

* **Location Search**

  * Accepts a city and state entered by the user
  * Uses the Open-Meteo Geocoding API to find the location

* **Weather Information**

  * Retrieves current temperature
  * Retrieves relative humidity
  * Displays the location entered by the user

* **REST API Integration**

  * Sends HTTP GET requests to Open-Meteo APIs
  * Dynamically builds API URLs using latitude and longitude

* **Error Handling**

  * Detects when a location cannot be found
  * Handles errors that occur during API requests

## Technologies Used

* Java
* Open-Meteo REST APIs
* Java `HttpClient`
* Java `HttpRequest`
* Java `HttpResponse`
* Java `URI`
* Git/GitHub

### Example

The user can enter:

```text
Princeton, New Jersey
```

The program sends the location to the Open-Meteo Geocoding API and retrieves its latitude and longitude.

Those coordinates are then used to create a weather API request.

The program processes the response and displays information such as:

```text
========== WEATHER ==========
Location: Princeton, New Jersey
Temperature: 24.8 °C
Humidity: 65%
```

Weather values may differ slightly from other weather services because different providers can use different data sources, weather models, and update times.

## What I Learned

Through this project, I practiced:

* Making HTTP requests in Java
* Working with REST APIs
* Using Java's `HttpClient`
* Building URLs dynamically
* Working with latitude and longitude
* Processing API response data
* Using String methods to extract information from responses
* Handling exceptions
* Working with external data in a Java application

## Future Improvements

Potential improvements include:

* Displaying wind speed
* Adding support for more detailed weather information
* Improving JSON response parsing
* Adding a graphical user interface
* Improving input validation
* Allowing users to search for multiple locations

import java.util.Scanner;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("Weather forecasting");
        System.out.println("=================================");

        System.out.println("Please enter city, state(full name): ");
        String location = input.nextLine();


        System.out.println("You entered: " + location);

        // Create a version of the location that can be used in the URL.
        String encodedLocation = location.replace(" ", "%20");

        // Create the URL for the location API.
        String url = "https://geocoding-api.open-meteo.com/v1/search?name="
                + encodedLocation;

        System.out.println("Your URL is: " + url);

        // Create the HTTP client.
        HttpClient client = HttpClient.newHttpClient();

        // Create the first request.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try
        {
            // Send the request.
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // Get the response from the API.
            String data = response.body();



            // Find the latitude.
            int latitudePosition = data.indexOf("\"latitude\":");
            if (latitudePosition == -1)
            {
                System.out.println("Location not found.");
                return;
            }


            int latitudeStart = data.indexOf(":", latitudePosition) + 1;

            int latitudeEnd = data.indexOf(",", latitudePosition);

            String latitude =
                    data.substring(
                            latitudeStart,
                            latitudeEnd
                    );

            System.out.println("Latitude: " + latitude);

            // Find the longitude.
            int longitudePosition = data.indexOf("\"longitude\":");

            int longitudeStart = data.indexOf(":", longitudePosition) + 1;

            int longitudeEnd = data.indexOf(",", longitudePosition);

            String longitude =
                    data.substring(
                            longitudeStart,
                            longitudeEnd
                    );

            System.out.println("Longitude: " + longitude);

            // Create the weather API URL.
            String weatherUrl =
                    "https://api.open-meteo.com/v1/forecast?latitude="
                            + latitude
                            + "&longitude="
                            + longitude
                            + "&current=temperature_2m,relative_humidity_2m,wind_speed_10m";

            System.out.println("Weather URL: " + weatherUrl);

            // Create the weather request.
            HttpRequest weatherRequest =
                    HttpRequest.newBuilder()
                            .uri(URI.create(weatherUrl))
                            .GET()
                            .build();

            // Send the weather request.
            HttpResponse<String> weatherResponse =
                    client.send(
                            weatherRequest,
                            HttpResponse.BodyHandlers.ofString()
                    );

            // Get the weather information.
            String weatherData =
                    weatherResponse.body();

            System.out.println(weatherData);

            // Find the current weather section.
            int currentPosition = weatherData.indexOf("\"current\":");

            // Find the temperature.
            int temperaturePosition = weatherData.indexOf(
                            "\"temperature_2m\":",
                            currentPosition);

            int temperatureStart =
                    weatherData.indexOf(
                            ":",
                            temperaturePosition
                    ) + 1;

            int temperatureEnd =
                    weatherData.indexOf(
                            ",",
                            temperaturePosition
                    );

            String temperature =
                    weatherData.substring(
                            temperatureStart,
                            temperatureEnd
                    );

            // Find the humidity.
            int humidityPosition =
                    weatherData.indexOf(
                            "\"relative_humidity_2m\":",
                            currentPosition
                    );

            int humidityStart =
                    weatherData.indexOf(
                            ":",
                            humidityPosition
                    ) + 1;

            int humidityEnd =
                    weatherData.indexOf(
                            ",",
                            humidityPosition
                    );

            String humidity =
                    weatherData.substring(
                            humidityStart,
                            humidityEnd
                    );

            // Display the weather.
            System.out.println();
            System.out.println("========== WEATHER ==========");
            System.out.println("Location: " + location);
            System.out.println("Temperature: " + temperature + " °C");
            System.out.println("Humidity: " + humidity + "%");
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong.");
        }

        input.close();
    }
}
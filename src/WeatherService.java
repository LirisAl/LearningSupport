import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String API_KEY = "8c7534ac8d0f22d5ad0276b966598853";

    public static void weather() {
        try {
            System.out.println("Определяем местоположение...");
            JSONObject ipData = getLocationByIP();
            if (!"success".equals(ipData.getString("status"))) {
                throw new Exception("Не удалось определить местоположение");
            }

            String city = ipData.getString("city");
            double lat = ipData.getDouble("lat");
            double lon = ipData.getDouble("lon");

            System.out.println("Найдено: " + city);

            JSONObject weather = getWeatherByCords(lat, lon);
            printWeather(weather);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage() + "\n");
        }
    }

    private static JSONObject getLocationByIP() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://ip-api.com/json/"))
                .GET()
                .timeout(java.time.Duration.ofSeconds(10))
                .build();
        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        return new JSONObject(response.body());
    }

    private static JSONObject getWeatherByCords(double lat, double lon) throws Exception {
        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?lat=%.4f&lon=%.4f&appid=%s&units=metric&lang=ru",
                lat, lon, API_KEY
        );
        return sendWeatherRequest(url);
    }

    private static JSONObject sendWeatherRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .timeout(java.time.Duration.ofSeconds(10))
                .build();
        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new Exception("Сервер вернул ошибку: " + response.statusCode());
        }

        return new JSONObject(response.body());
    }

    private static void printWeather(JSONObject weather) {
        JSONObject main = weather.getJSONObject("main");

        double temp = main.getDouble("temp");
        double tempMin = main.getDouble("temp_min");
        double tempMax = main.getDouble("temp_max");
        int humidity = main.getInt("humidity");
        double wind = weather.getJSONObject("wind").getDouble("speed");
        String desc = weather.getJSONArray("weather")
                .getJSONObject(0).getString("description");

        String windPower = windDescription(wind);
        String moisture = airHumidity(humidity);

        System.out.println("\nТемпература: " + temp + "°C. Сегодня от " + tempMin + "°C до " + tempMax + "°C");
        System.out.println("Влажность: " + humidity + "%" + moisture);
        System.out.println("Ветер: " + wind + " м/с" + windPower);
        System.out.println("Описание: " + desc + "\n");
    }

    private static String windDescription(double wind) {
        double[] windSpeed = {0.2, 1.6, 3.4, 5.5, 8.0, 10.8, 13.9, 17.2, 20.8, 24.5, 28.5, 32.7};
        String[] descriptions = {
                " - штиль", " - тихий ветер", " - легкий ветер", " - слабый ветер", " - умеренный ветер",
                " - свежий ветер", " - сильный ветер", " - крепкий ветер", " - очень крепкий ветер",
                " - шторм", " - сильный шторм", " - жестокий шторм", " - ураган"
        };

        for (int i = 0; i < windSpeed.length; i++) {
            if (wind < windSpeed[i]) {
                return descriptions[i];
            }
        }
        return descriptions[descriptions.length - 1];
    }
    private static String airHumidity(int humidity) {
        if (humidity < 30) return " - критически сухо";
        if (humidity < 40) return " - норма в холодный (отопительный) период";
        if (humidity <= 60) return " - идеально";
        return " - повышенная влажность";
    }
}

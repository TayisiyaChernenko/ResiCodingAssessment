package main.java;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MarsRoverClient {
    private static final String API_KEY = System.getenv("NASA_API_KEY");
    private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";
    private static final String IMAGE_DIR = "src/main/resources/images";
    private final HttpClient httpClient;

    public MarsRoverClient() {
        //the NASA API URLs are returning a 301 redirect
        this.httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
    }

    private String getPhotosForDate(String date) throws IOException, InterruptedException {
        // any date recieved here is already in the format YYYY-MM-DD
        String url = String.format("%s?earth_date=%s&api_key=%s", BASE_URL, date, API_KEY);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Failed to fetch photos: " + response.statusCode());
        }

        return response.body();
    }

    private void savePhoto(String imageUrl, String date) throws IOException, InterruptedException {
        // Create a unique filename for each photo using the date
        String filename = String.format("mars_photo_%s.jpg", date);
        Path imagePath = Paths.get(IMAGE_DIR, filename);

        // Download the image  
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(imageUrl))
                .GET()
                .build();

        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

        if (response.statusCode() != 200) {
            throw new IOException("Failed to download image: " + response.statusCode());
        }

        // Save the image to the local directory
        try (InputStream inputStream = response.body();
             OutputStream outputStream = Files.newOutputStream(imagePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    //only exposed method for Main to call, abstracts a
    public void downloadPhotosForDate(String date) throws IOException, InterruptedException {
        String response = getPhotosForDate(date);
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray photos = jsonResponse.getJSONArray("photos");

        // If there are photos for the date, download the first photo
        if (photos.length() > 0) {
            // Get only the first photo for that date
            JSONObject photo = photos.getJSONObject(0);
            String imgSrc = photo.getString("img_src");
            savePhoto(imgSrc, date);
        } else {
            System.out.println("No photos found for date: " + date);
        }
    }
}

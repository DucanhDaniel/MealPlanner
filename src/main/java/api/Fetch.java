package api;

import java.net.HttpURLConnection;
import java.net.URL;

public class Fetch {
    public static HttpURLConnection fetchApiResponse(String urlString) {
        try {
            //Attempt to create a connection
            URL url = new URL(urlString);
            // get URLConnection and convert to HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // set request method to GET
            connection.setRequestMethod("GET");

            return connection;
        } catch(Exception e) {
            System.out.println("Error connecting to API: " + e.getMessage());
        }
        return null; // could not make a connection
    }
}

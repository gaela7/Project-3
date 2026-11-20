import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// This program asks the Open Trivia Database for questions
// and prints what it gets back.
public class Trivia {

    // The main web address of the trivia API
    private static final String BASE_URL = "https://opentdb.com/api.php";

    public static void main(String[] args) {
        // These are fixed for now
        int amount = 5;               // how many questions
        int category = 27;            // 27 = Animals
        String difficulty = "easy";   // easy, medium, or hard
        String type = "boolean";      // boolean = true/false questions

        // Make the web address
        String url = buildUrl(amount, category, difficulty, type);
        System.out.println("Requesting: " + url);

        // Get the response from the API
        String body = fetchResponseText(url);

        // Only print if we got a good response
        if (body != null) {
            System.out.println(body);
        }
    }

    // Puts the user's choices into a web address.
    public static String buildUrl(int amount, int category, String difficulty, String type) {
        return BASE_URL + "?amount=" + amount
                + "&category=" + category
                + "&difficulty=" + difficulty
                + "&type=" + type;
    }

    // Sends the request and returns the response text
    public static String fetchResponseText(String url) {
        // Create the tool that sends web requests
        HttpClient client = HttpClient.newHttpClient();

        // Build a GET request for our web address
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            // Send the request and get the answer back as text
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // 200 means the request worked
            if (response.statusCode() == 200) {
                return response.body();
            }

            // Any other number means a problem
            System.out.println("Request failed with status code: " + response.statusCode());
        } catch (IOException e) {
            
            System.out.println("Network error: " + e.getMessage());
        } catch (InterruptedException e) {
            // Happens if the request was stopped early
            Thread.currentThread().interrupt();
            System.out.println("Request was interrupted.");
        }

        // If we get here, something went wrong
        return null;
    }

    //Ask the user to pick a difficulty and return it.
    public static String userChoices() {
        return "easy";
    }

    //Get the JSON from the API and turn it into Java
    public static void getQuestions(String url) {
    }

    // how one question, get the user's answer,
    // and return true if they got it right.
    public static boolean askQuestion(String question, String correctAnswer) {
        return false;
    }

    //Print how many questions the user got right.
    public static void printFinalScore(int score, int total) {
    }
}
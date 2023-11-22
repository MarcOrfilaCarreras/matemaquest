package cloud.marcorfilacarreras.matemaquest.v1;

import cloud.marcorfilacarreras.matemaquest.common.Utils;
import cloud.marcorfilacarreras.matemaquest.libsql.LibsqlController;
import cloud.marcorfilacarreras.matemaquest.models.Exam;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * The SearchHandler class is responsible for handling and processing requests related to searches.
 */
public class SearchHandler implements Route {
    // Initializing the database controller for handling SQL operations
    private final LibsqlController db = new LibsqlController();

    @Override
    public Object handle(Request request, Response response) throws Exception {
        // Setting the response type to JSON for a uniform response format
        response.type("application/json");

        if (request.requestMethod().equals("GET")) {
            int page = 0; // Defaulting the page number to 0
            String lang = "es"; // Defaulting the lang to "es"
            String name = null; // Defaulting the name to null
                        
            // Initializing JSON objects for constructing the response
            JsonObject responseJson = new JsonObject();
            JsonArray responseArray = new JsonArray();
            JsonObject messageJson = new JsonObject();
            
            // Checking if the request contains a page parameter
            if (!request.queryMap("page").hasValue()) {
                // If no page parameter is provided, set the page to 1
                page = 1;
            } else {
                // Attempting to parse the provided page parameter
                try {
                    // Parsing the page number from the request
                    page = (int) request.queryMap("page").integerValue();
                } catch (NumberFormatException e) {
                    // Handling the case where the provided page parameter is not a valid integer
                    responseJson.addProperty("status", "fail");
                    messageJson.addProperty("message", "Invalid page. Please provide a valid page number.");
                    responseJson.add("data", messageJson);
                    response.status(400);
                    response.body(responseJson.toString());
                    return null;
                }
            }

            // Checking if the provided page is valid (greater than 0)
            if (page <= 0) {
                // Handling the case where an invalid page number is provided
                responseJson.addProperty("status", "fail");
                messageJson.addProperty("message", "Invalid page. Please provide a valid page number.");
                responseJson.add("data", messageJson);
                response.status(400);
                response.body(responseJson.toString());
                return null;
            }
            
            // Checking if the request contains a lang parameter
            if (!request.queryMap("lang").hasValue()) {
                // If no lang parameter is provided, set the lang to "es"
                lang = "es";
            } else {
                // Checking if the value is "es" or "ca"
                if (request.queryMap("lang").value().toLowerCase().equals("es") || request.queryMap("lang").value().toLowerCase().equals("ca")){
                    lang = request.queryMap("lang").value();
                } else {
                    responseJson.addProperty("status", "fail");
                    messageJson.addProperty("message", "Invalid lang. Please provide a valid lang (es / ca).");
                    responseJson.add("data", messageJson);
                    response.status(400);
                    response.body(responseJson.toString());
                    return null;
                }
            }
            
            // Checking if the request contains a name parameter
            if (request.queryMap("name").hasValue()) {
                try {
                    // Checking if the value is valid
                    name = new Utils().validateAndEscapeInput(request.queryMap("name").value().toLowerCase());
                } catch (IllegalArgumentException e){
                    responseJson.addProperty("status", "fail");
                    messageJson.addProperty("message", e.getMessage());
                    responseJson.add("data", messageJson);
                    response.status(400);
                    response.body(responseJson.toString());
                    return null;
                }
            }

            // Fetching search data from the database based on the provided page number, lang and name
            List<Exam> data = db.getSearch(page, lang.toLowerCase(), name);
            
            // Adding the fetched search data to the response array after converting it to the appropriate JSON format
            for (Exam exam : data){
                responseArray.add(new Gson().fromJson(exam.toJsonWithoutProperty("questions"), JsonObject.class));
            }

            // Adding metadata to the response message
            messageJson.addProperty("page", page);
            messageJson.addProperty("pageCount", db.getSearchPages(lang.toLowerCase(), name));
            
            // Constructing a success response with the search data
            responseJson.addProperty("status", "success");
            
            // Including the response data and metadata in the final response JSON
            responseJson.add("data", responseArray);
            responseJson.add("meta", messageJson);

            // Returning the constructed JSON response
            response.body(responseJson.toString());
            return null;
        }

        // Handling the case where an invalid request method is used
        response.body("{\"status\":\"fail\",\"data\":{\"message\":\"Your request was not valid.\"}}");
        return null;
    }
}

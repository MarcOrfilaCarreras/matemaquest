package cloud.marcorfilacarreras.matemaquest.v1;

import cloud.marcorfilacarreras.matemaquest.common.NotFoundHandler;
import cloud.marcorfilacarreras.matemaquest.libsql.LibsqlController;
import cloud.marcorfilacarreras.matemaquest.radian.RadianController;
import com.google.gson.JsonObject;
import java.io.InputStream;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Handler class to process requests for image-based questions.
 */
public class QuestionImageHandler implements Route {
    // Initializing the database controller
    private final LibsqlController db = new LibsqlController();

    @Override
    public Object handle(Request request, Response response) throws Exception {
        // Setting response type to JSON
        response.type("application/json");

        if (request.requestMethod().equals("GET")) {
            int id = 0;
            RadianController radian = new RadianController();

            // Initializing JSON objects for response
            JsonObject responseJson = new JsonObject();
            JsonObject messageJson = new JsonObject();

            // Validating the input ID
            try {
                // Parsing the provided ID into an integer
                id = Integer.parseInt(request.params(":id"));
            } catch (NumberFormatException e) {
                // Handling an invalid ID format
                responseJson.addProperty("status", "fail");
                messageJson.addProperty("message", "Invalid ID. Please provide a valid integer ID.");
                responseJson.add("data", messageJson);
                response.body(responseJson.toString());
                return null;
            }

            // Checking for a valid ID range
            if (id <= 0) {
                // Handling an invalid ID range
                responseJson.addProperty("status", "fail");
                messageJson.addProperty("message", "Invalid ID. Please provide a valid ID.");
                responseJson.add("data", messageJson);
                response.body(responseJson.toString());
                return null;
            }

            // Handling the case where no URI is specified
            if (!(request.splat().length > 0)) {
                responseJson.addProperty("status", "fail");
                messageJson.addProperty("message", "No URI specified.");
                responseJson.add("data", messageJson);
                response.body(responseJson.toString());
                return null;
            }
                        
            InputStream image = radian.getImage(id, request.splat()[0]);
            
            // Check if the image is null
            if (image == null) {
                return new NotFoundHandler().handle(request, response);
            }

            // Setting the response type to image/png
            response.type("image/png");

            // Returning the image content associated with the provided ID and URI
            return image;
        }

        // Handling an invalid request method
        response.body("{\"status\":\"fail\",\"data\":{\"message\":\"Your request was not valid.\"}}");
        return null;
    }
}

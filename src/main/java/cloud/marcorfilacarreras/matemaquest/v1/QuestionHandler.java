package cloud.marcorfilacarreras.matemaquest.v1;

import cloud.marcorfilacarreras.matemaquest.common.NotFoundHandler;
import cloud.marcorfilacarreras.matemaquest.libsql.LibsqlController;
import cloud.marcorfilacarreras.matemaquest.models.Question;
import cloud.marcorfilacarreras.matemaquest.redis.RedisController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Handler class to process requests for questions.
 */
public class QuestionHandler implements Route {
    private final LibsqlController db = new LibsqlController(); // Initializing the database controller
    private final RedisController redis = new RedisController(); // Initializing the redis controller

    @Override
    public Object handle(Request request, Response response) throws Exception {
        // Setting response type to JSON
        response.type("application/json");

        if (request.requestMethod().equals("GET")) {
            int id;

            // Initializing JSON objects for response
            JsonObject responseJson = new JsonObject();
            JsonObject messageJson = new JsonObject();

            // Validating the input ID
            try {
                id = Integer.parseInt(request.params(":id"));
            } catch (NumberFormatException e) {
                // Handling invalid ID format
                responseJson.addProperty("status", "fail");
                messageJson.addProperty("message", "Invalid ID. Please provide a valid integer ID.");
                responseJson.add("data", messageJson);
                response.status(404);
                return responseJson;
            }

            // Checking for valid ID range
            if (id <= 0) {
                // Handling invalid ID range
                responseJson.addProperty("status", "fail");
                messageJson.addProperty("message", "Invalid ID. Please provide a valid ID.");
                responseJson.add("data", messageJson);
                response.status(404);
                return responseJson;
            }
            
            // Fetching question data from Redis
            if (! (redis.getQuestion(id) == null)){
                // Formulating a success response with the question data
                responseJson.addProperty("status", "success");
                responseJson.add("data", new Gson().fromJson(redis.getQuestion(id), JsonObject.class));
                return responseJson;
            }

            // Fetching question data from the database
            Question question = db.getQuestion(id);
            
            // Check if the exam has questions
            if (question == null){
                return new NotFoundHandler().handle(request, response);
            }

            // Formulating a success response with the question data
            responseJson.addProperty("status", "success");
            responseJson.add("data", new Gson().fromJson(question.toJson(), JsonObject.class));

            // Returning the JSON response
            return responseJson;
        }

        // Handling invalid request method
        return "{\"status\":\"fail\",\"data\":{\"message\":\"Your request was not valid.\"}}";
    }
}

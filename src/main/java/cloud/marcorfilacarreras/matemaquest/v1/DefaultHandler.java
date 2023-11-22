package cloud.marcorfilacarreras.matemaquest.v1;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Handler class to process requests for /v1.
 */
public class DefaultHandler implements Route {
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        // Setting response type to JSON
        response.type("application/json");
        
        response.status(200);
        response.body(
                "{\n" +
                "  \"data\": {\n" +
                "    \"endpoints\": [\n" +
                "      {\n" +
                "        \"url\": \"/v1\",\n" +
                "        \"method\": \"GET\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"url\": \"/v1/exam/:id\",\n" +
                "        \"method\": \"GET\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"url\": \"/v1/search\",\n" +
                "        \"method\": \"GET\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"url\": \"/v1/question/:id\",\n" +
                "        \"method\": \"GET\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"url\": \"/v1/question/:id/image/exercise\",\n" +
                "        \"method\": \"GET\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"url\": \"/v1/question/:id/image/answer\",\n" +
                "        \"method\": \"GET\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "    \"status\": \"success\"\n" +
                "}");
        return null;
    }
}

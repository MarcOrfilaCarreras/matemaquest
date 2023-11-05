package cloud.marcorfilacarreras.matemaquest.common;

import spark.Request;
import spark.Response;
import spark.Route;

public class NotFoundHandler implements Route {
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("application/json");
        response.status(404);
        
        return "{\"status\":\"fail\",\"data\":{\"message\":\"Not found.\"}}";
    }
}

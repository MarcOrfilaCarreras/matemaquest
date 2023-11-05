package cloud.marcorfilacarreras.matemaquest.common;

import spark.Request;
import spark.Response;
import spark.Route;

public class InternalServerErrorHandler implements Route {
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("application/json");
        response.status(500);
        
        return "{\"status\":\"error\",\"message\":\"Internal Server Error: The monkeys are working on it!\"}";
    }
}

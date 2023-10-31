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
        response.redirect("/v1/exam/");
        return null;
    }
}

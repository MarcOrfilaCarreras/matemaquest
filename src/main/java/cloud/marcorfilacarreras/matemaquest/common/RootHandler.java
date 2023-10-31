package cloud.marcorfilacarreras.matemaquest.common;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Handler class to process requests for /.
 */
public class RootHandler implements Route {
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.redirect("/v1");
        return null;
    }
}

package cloud.marcorfilacarreras.matemaquest;

import cloud.marcorfilacarreras.cloud.common.NotFoundHandler;
import cloud.marcorfilacarreras.matemaquest.v1.ExamHandler;
import cloud.marcorfilacarreras.matemaquest.v1.DefaultHandler;
import cloud.marcorfilacarreras.cloud.common.RootHandler;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        // Set the port for the server
        port(8080);
        
        // Define routes for different endpoints
        // Redirect to RootHandler for the root endpoint "/"
        get("/", new RootHandler());
        
        // Call V1Handler for the "/v1" endpoint
        get("/v1", new DefaultHandler());
        
        // Call V1ExamHandler for the "/v1/exam/:id" endpoint
        get("/v1/exam/:id", new ExamHandler());
        
        // Set up a default message for 404 errors
        notFound(new NotFoundHandler());

        // Initialize the server
        init();
    }
    
}

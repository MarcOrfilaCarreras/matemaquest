package cloud.marcorfilacarreras.matemaquest;

import cloud.marcorfilacarreras.matemaquest.common.InternalServerErrorHandler;
import cloud.marcorfilacarreras.matemaquest.common.NotFoundHandler;
import cloud.marcorfilacarreras.matemaquest.v1.ExamHandler;
import cloud.marcorfilacarreras.matemaquest.v1.DefaultHandler;
import cloud.marcorfilacarreras.matemaquest.common.RootHandler;
import cloud.marcorfilacarreras.matemaquest.v1.QuestionHandler;
import cloud.marcorfilacarreras.matemaquest.v1.QuestionImageHandler;
import cloud.marcorfilacarreras.matemaquest.v1.SearchHandler;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        // Set the port for the server
        port(8080);
        
        // Redirect to RootHandler for the root endpoint "/"
        get("/", new RootHandler());
        
        /*
        * V1 routes
        */
        get("/v1", new DefaultHandler());
        get("/v1/exam/:id", new ExamHandler());
        get("/v1/question/:id", new QuestionHandler());
        get("/v1/question/:id/image/*", new QuestionImageHandler());
        get("/v1/search", new SearchHandler());
        
        /*
        * Default routes
        */
        notFound(new NotFoundHandler());
        internalServerError(new InternalServerErrorHandler());

        // Initialize the server
        init();
    }
    
}

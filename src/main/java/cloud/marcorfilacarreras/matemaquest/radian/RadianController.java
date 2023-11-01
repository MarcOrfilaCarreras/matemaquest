package cloud.marcorfilacarreras.matemaquest.radian;

import cloud.marcorfilacarreras.matemaquest.libsql.LibsqlController;
import cloud.marcorfilacarreras.matemaquest.models.Question;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class RadianController {
    
    /**
     * Returns an InputStream with an image.
     * @param id The ID of the question.
     * @param path The path of the image.
     * @return The InputStream of the image.
     */
    public InputStream getImage (int id, String path) throws MalformedURLException, IOException{
        LibsqlController db = new LibsqlController();
        
        // Getting the question based on the provided ID
        Question question = db.getQuestion(id);

        URL url = null; // Initializing a URL object

        // Checking if the path is for the exercise image
        if (path.equals("exercise")){
            // Setting the URL to the exercise image URL from the question
            url = new URL(question.getExercise_image());
        }

        // Checking if the path is for the answer image
        if (path.equals("answer")){
            // Setting the URL to the answer image URL from the question
            url = new URL(question.getAnswer_image());
        }

        // Opening an input stream based on the URL
        InputStream image = url.openStream();

        return image;
    }
    
}

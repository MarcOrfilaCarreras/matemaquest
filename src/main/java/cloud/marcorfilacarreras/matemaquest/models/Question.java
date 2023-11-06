package cloud.marcorfilacarreras.matemaquest.models;

import com.google.gson.Gson;


/**
 * Represents a question object that contains a file, title, exercise, exercise_image, options, answer and answer_image.
 */
public class Question {
    private int id;
    private String file;
    private String title;
    private String exercise;
    private String exercise_image;
    private String options;
    private String answer;
    private String answer_image;
    
    /**
     * Default constructor for QuestionObject.
     */
    public Question(){
    }
    
    /**
     * Constructor for QuestionObject with parameters.
     * @param id The id of the question.
     * @param file The file of the question.
     * @param title The title of the question.
     * @param exercise The exercise of the question.
     * @param exercise_image The exercise_image of the question.
     * @param options The options of the question.
     * @param answer The answer of the question.
     * @param answer_image The answer_image of the question.
     */
    public Question(int id, String file, String title, String exercise, String exercise_image, String options, String answer, String answer_image) {
        this.id = id;
        this.file = file;
        this.title = title;
        this.exercise = exercise;
        this.exercise_image = exercise_image;
        this.options = options;
        this.answer = answer;
        this.answer_image = answer_image;
    }
    
    /**
     * Gets the id of the question.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the exercise_image of the question.
     */
    public String getExercise_image() {
        return exercise_image;
    }

    /**
     * Gets the answer_image of the question.
     */
    public String getAnswer_image() {
        return answer_image;
    }
    
    /**
     * Converts the QuestionObject to JSON format using the Gson library.
     * @return The JSON representation of the QuestionObject.
     */
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    /**
     * Build the image urls.
     */
    public void buildUrl(){
        if (!this.exercise_image.equals("null")){
            this.exercise_image = "/v1/question/" + this.id + "/image/exercise";
        }
        
        if (!this.answer_image.equals("null")){
            this.answer_image = "/v1/question/" + this.id + "/image/answer";
        }
    }
    
    /**
     * Converts the QuestionObject to JSON format using the Gson library and formats the URLs.
     * @return The JSON representation of the QuestionObject.
     */
    public String toJsonWithBuildUrl(){
        buildUrl();
        return toJson();
    }
}

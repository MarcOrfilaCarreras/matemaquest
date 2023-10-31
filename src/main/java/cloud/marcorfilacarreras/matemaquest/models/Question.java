package cloud.marcorfilacarreras.matemaquest.models;

import com.google.gson.Gson;


/**
 * Represents a question object that contains a file, title, exercise, exercise_image, options, answer and answer_image.
 */
public class Question {
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
     * @param file The file of the question.
     * @param title The title of the question.
     * @param exercise The exercise of the question.
     * @param exercise_image The exercise_image of the question.
     * @param options The options of the question.
     * @param answer The answer of the question.
     * @param answer_image The answer_image of the question.
     */
    public Question(String file, String title, String exercise, String exercise_image, String options, String answer, String answer_image) {
        this.file = file;
        this.title = title;
        this.exercise = exercise;
        this.exercise_image = exercise_image;
        this.options = options;
        this.answer = answer;
        this.answer_image = answer_image;
    }
    
    /**
     * Converts the QuestionObject to JSON format using the Gson library.
     * @return The JSON representation of the QuestionObject.
     */
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

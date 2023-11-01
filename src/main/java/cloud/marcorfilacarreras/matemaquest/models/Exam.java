package cloud.marcorfilacarreras.matemaquest.models;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an exam object that contains an ID, name, level, and a list of question objects.
 */
public class Exam {
    private int id;
    private String name;
    private String level;
    private List<Question> questions = new ArrayList<>();
    private String lang;

    /**
     * Default constructor for ExamObject.
     */
    public Exam() {
    }

    /**
     * Constructor for ExamObject with parameters.
     * @param id The ID of the exam.
     * @param name The name of the exam.
     * @param level The level of the exam.
     */
    public Exam(int id, String name, String level, String lang) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.lang = lang;
    }

    /**
     * Sets the ID of the exam.
     * @param id The ID to set for the exam.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Appends a question to the list of questions for the exam.
     * @param q The question object to be added.
     */
    public void appendQuestion(Question q) {
        this.questions.add(q);
    }

    /**
     * Converts the ExamObject to JSON format using the Gson library.
     * @return The JSON representation of the ExamObject.
     */
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    /**
     * Converts the ExamObject to JSON format using the Gson library.
     * @return The JSON representation of the ExamObject without a field.
     */
    public String toJsonWithoutProperty(String field) {
        Gson gson =  new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().equals(field);
            }
            
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
        
        return gson.toJson(this);
    }
}

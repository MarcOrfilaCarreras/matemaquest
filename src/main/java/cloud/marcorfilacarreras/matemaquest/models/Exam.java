package cloud.marcorfilacarreras.matemaquest.models;

import com.google.gson.Gson;
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
    public Exam(int id, String name, String level) {
        this.id = id;
        this.name = name;
        this.level = level;
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
}

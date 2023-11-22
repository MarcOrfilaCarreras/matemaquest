package cloud.marcorfilacarreras.matemaquest.libsql;

import cloud.marcorfilacarreras.matemaquest.models.Exam;
import cloud.marcorfilacarreras.matemaquest.models.Question;
import java.util.ArrayList;
import java.util.List;

public class LibsqlController {
    
    private final String url = System.getenv("LIBSQL_URL");
    private final String authToken = System.getenv("LIBSQL_TOKEN");

    public LibsqlController() {
    }

    /**
     * Retrieves an exam by its ID.
     *
     * @param id The ID of the exam.
     * @return The Exam containing the response.
     */
    public Exam getExam(int id) {
        Exam exam = new Exam();
        exam.setId(id);

        // Constructing the query string
        String query = "SELECT * FROM questions where exam_id = " + id + ";";

        // Creating the client instance for SQL operations
        LibsqlClient client = LibsqlClient.builder(url).authToken(authToken).build();

        // Executing the SQL query and fetching the result set
        LibsqlResultSet rs = client.execute(query);
        
        // Check if the exam has questions
        if (rs.rows.size() <= 0){
            return null;
        }

        // Iterating over the result set to construct QuestionObject instances and adding them to the exam
        for (LibsqlResultSet.Row row : rs.rows) {
            Question q = new Question(
                    (int) row.getDouble(0),
                    (String) row.get(1),
                    (String) row.get(2),
                    (String) row.get(3),
                    (String) row.get(4),
                    (String) row.get(5),
                    (String) row.get(6),
                    (String) row.get(7)
            );
            
            q.buildUrl();
            
            exam.appendQuestion(q);
        }

        return exam;
    }
    
    /**
     * Retrieves a question by its ID.
     *
     * @param id The ID of the question.
     * @return The Question containing the response.
     */
    public Question getQuestion(int id) {
        Question question = new Question();
        
        // Constructing the query string
        String query = "SELECT * FROM questions where id = " + id + ";";

        // Creating the client instance for SQL operations
        LibsqlClient client = LibsqlClient.builder(url).authToken(authToken).build();

        // Executing the SQL query and fetching the result set
        LibsqlResultSet rs = client.execute(query);
        
        // Check if the exam has questions
        if (rs.rows.size() <= 0){
            return null;
        }

        // Iterating over the result set to construct QuestionObject instances
        for (LibsqlResultSet.Row row : rs.rows) {
            question = new Question(
                    (int) row.getDouble(0),
                    (String) row.get(1),
                    (String) row.get(2),
                    (String) row.get(3),
                    (String) row.get(4),
                    (String) row.get(5),
                    (String) row.get(6),
                    (String) row.get(7)
            );
        }

        return question;
    }
    
    /**
     * Retrieves exams by its name.
     *
     * @param page The page of the search.
     * @param lang The lang of the search.
     * @param name The name of the search.
     * @return A list with the exams.
     */
    public List<Exam> getSearch(int page, String lang, String name) {
        List<Exam> exams = new ArrayList<Exam>();
        
        // Constructing the query string
        String query = "SELECT * FROM exams where lang = '" + lang + "'";
        
        // If name is not null
        if (name != null){
            query = query + " AND name LIKE '%" + name + "%'";
        }
        
        // Limit the query
        query = query + " ORDER BY id LIMIT 10 " + " OFFSET " + ((page - 1) * 10) + ";";

        // Creating the client instance for SQL operations
        LibsqlClient client = LibsqlClient.builder(url).authToken(authToken).build();

        // Executing the SQL query and fetching the result set
        LibsqlResultSet rs = client.execute(query);
        
        // Iterating over the result set to construct ExamObject instances and adding them to the list
        for (LibsqlResultSet.Row row : rs.rows) {
            Exam e = new Exam((int) row.getDouble(0), row.getString(1), row.getString(2), row.getString(3));
            exams.add(e);
        }

        return exams;
    }
    
    /**
     * Retrieves the number of pages of a query.
     *
     * @param lang The lang of the search.
     * @param name The name of the search.
     * @return An integer with the number of pages.
     */
    public int getSearchPages(String lang, String name) {
        int totalPages = 0;
        
        // Constructing the query string
        String query = "SELECT COUNT(*) FROM exams where lang = '" + lang + "'";
        
        // If name is not null
        if (name != null){
            query = query + " AND name LIKE '%" + name + "%'";
        }
        
        // Close the query
        query = query + ";";
        
        // Creating the client instance for SQL operations
        LibsqlClient client = LibsqlClient.builder(url).authToken(authToken).build();

        // Executing the SQL query and fetching the result set
        LibsqlResultSet rs = client.execute(query);
        
        // Iterating over the result set to calculate the number of pages
        for (LibsqlResultSet.Row row : rs.rows) {
            totalPages = ((int) row.getDouble(0) + 10 - 1) / 10;
        }
        
        return totalPages;
    }
}

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
     * @return The String containing the response.
     */
    public String getExam(int id) {
        Exam exam = new Exam();
        exam.setId(id);

        // Constructing the query string
        String query = "SELECT * FROM questions where exam_id = " + id + ";";

        // Creating the client instance for SQL operations
        LibsqlClient client = LibsqlClient.builder(url).authToken(authToken).build();

        // Executing the SQL query and fetching the result set
        LibsqlResultSet rs = client.execute(query);

        // Iterating over the result set to construct QuestionObject instances and adding them to the exam
        for (LibsqlResultSet.Row row : rs.rows) {
            Question q = new Question(
                    (String) row.get(1),
                    (String) row.get(2),
                    (String) row.get(3),
                    (String) row.get(4),
                    (String) row.get(5),
                    (String) row.get(6),
                    (String) row.get(7)
            );

            exam.appendQuestion(q);
        }

        return exam.toJson();
    }
    
    /**
     * Retrieves exams by its name.
     *
     * @param page The page of the search.
     * @return The String containing the response.
     */
    public List<Exam> getSearch(int page) {
        List<Exam> exams = new ArrayList<Exam>();
        
        // Constructing the query string
        String query = "SELECT * FROM exams ORDER BY id LIMIT 10 " + " OFFSET " + ((page - 1) * 10) + ";";

        // Creating the client instance for SQL operations
        LibsqlClient client = LibsqlClient.builder(url).authToken(authToken).build();

        // Executing the SQL query and fetching the result set
        LibsqlResultSet rs = client.execute(query);
        
        // Iterating over the result set to construct QuestionObject instances and adding them to the exam
        for (LibsqlResultSet.Row row : rs.rows) {
            Exam e = new Exam((int) row.getDouble(0), row.getString(1), row.getString(2));
            exams.add(e);
        }

        return exams;
    }
    
    public int getSearchPages() {
        int totalPages = 0;
        
        // Constructing the query string
        String query = "SELECT COUNT(*) FROM exams;";
        
        // Creating the client instance for SQL operations
        LibsqlClient client = LibsqlClient.builder(url).authToken(authToken).build();

        // Executing the SQL query and fetching the result set
        LibsqlResultSet rs = client.execute(query);
        
        // Iterating over the result set to construct QuestionObject instances and adding them to the exam
        for (LibsqlResultSet.Row row : rs.rows) {
            totalPages = ((int) row.getDouble(0) + 10 - 1) / 10;
        }
        
        return totalPages;
    }
}

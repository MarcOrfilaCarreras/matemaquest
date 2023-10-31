package cloud.marcorfilacarreras.matemaquest.libsql;

import cloud.marcorfilacarreras.matemaquest.models.Exam;
import cloud.marcorfilacarreras.matemaquest.models.Question;

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
}

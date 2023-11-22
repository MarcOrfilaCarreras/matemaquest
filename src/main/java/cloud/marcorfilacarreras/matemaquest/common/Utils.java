package cloud.marcorfilacarreras.matemaquest.common;

/**
 * Utils class definition.
 */
public class Utils {

    // Default constructor
    public Utils() {
    }
    
    /**
    * Method for validating and escaping input strings.
    * 
    * @param input The string to validate.
    * @return The string validated or null.
    */
    public String validateAndEscapeInput(String input){        
        // Replace single quotes with two single quotes to prevent SQL injection
        input = input.replaceAll("'", "''");
        
        // Remove characters that could be used for SQL injection
        input = input.replaceAll("[;\\-\\#=@!$%^&*()_+{}|:\"<>?`~\\[\\]/\\\\]", "");
        
        // Ensure the input is not null or empty
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        
        // Ensure the input length is within acceptable limits
        if (input.length() > 255) {
            throw new IllegalArgumentException("Invalid input. Input length exceeds the limit.");
        }
        
        // Ensure the input is not a SQL keyword
        if (isSqlKeyword(input)) {
            throw new IllegalArgumentException("Invalid input. Input cannot be a SQL keyword.");
        }
        
        // Ensure the input does not contain special characters
        if (!isAlphaNumeric(input)) {
            throw new IllegalArgumentException("Invalid input. Input can only contain alphanumeric characters.");
        }
        
        // Ensure the input does not start or end with spaces
        if (input.trim().length() != input.length()) {
            throw new IllegalArgumentException("Invalid input. Input cannot start or end with spaces.");
        }
        
        // Return the validated and escaped input
        return input;
    }
    
    /**
    * Check if the input is a SQL keyword.
    * 
    * @param input The string to validate.
    * @return True / False.
    */
    private static boolean isSqlKeyword(String input) {
        String[] sqlKeywords = {"SELECT", "FROM", "WHERE", "AND", "OR", "INSERT", "UPDATE", "DELETE", "CREATE", "DROP", "TABLE"};
        for (String keyword : sqlKeywords) {
            if (input.equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        // If the input is not a SQL keyword
        return false;
    }
    
    /**
    * Check if the input is alphanumeric.
    * 
    * @param input The string to validate.
    * @return True / False.
    */
    private static boolean isAlphaNumeric(String input) {
        return input.matches("[a-zA-Z0-9]+");
    }
    
}

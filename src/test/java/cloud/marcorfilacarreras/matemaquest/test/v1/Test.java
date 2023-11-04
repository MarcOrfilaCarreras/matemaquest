package cloud.marcorfilacarreras.matemaquest.test.v1;

import cloud.marcorfilacarreras.matemaquest.App;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ExamHandler.class,
    QuestionHandler.class,
    SearchHandler.class
})

public class Test {
    
    @BeforeClass
    public static void setUp() {
        App.main(null); // Initialize the application before running tests
    }
}

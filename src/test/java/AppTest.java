import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test public void testAppIsCreated() {
        App app = new App();
        assertNotNull("Make sure an app can be constructed.", app);
    }
}

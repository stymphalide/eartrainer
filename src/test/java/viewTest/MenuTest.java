package viewTest;

import org.junit.Test;
import static org.junit.Assert.*;

import view.*;

import javafx.scene.Scene;

public class MenuTest {

    @Test public void testMenuIsCreatable() {
        Menu menu = new Menu();
        assertNotNull("Menu can be created", menu);
    }
    @Test public void testMenuHasRoot() {
        Menu menu = new Menu();
    }
}

import view.Menu;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        // Creates the menu from the interface.
        // @TODO Make the interface a package and import it.
        Menu menu = new Menu();
        menu.start_view();


        
    }
}

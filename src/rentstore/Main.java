package rentstore;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, ParseException {
        ItemManager itemManager = new ItemManager();
        ArrayList<Book> books = itemManager.readBooks("books.txt");
        ArrayList<Movie> movies = itemManager.readMovies("movies.txt");

        StoreManager manager = new StoreManager(movies,books);
        Menu menu = new Menu(manager);
        while (true) {
            Menu.MenuStart();
            if (!menu.getAndExecuteMainMenu())
                break;
        }


    }

}

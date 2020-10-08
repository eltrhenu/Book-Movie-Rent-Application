package rentstore;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ItemManager {

    DataAccessLayer dataAccessLayer = new DataAccessLayer();
    /**
     * To create movies with informations in file
     * @param file 
     * @return all Movie objects
     */
    public ArrayList<Movie> readMovies(String file) {
        ArrayList<String> lines = new ArrayList<String>();
        lines = dataAccessLayer.readFile(file);
        ArrayList<Movie> movies = new ArrayList<Movie>();

        for (String line : lines) {
            StringTokenizer st = new StringTokenizer(line, ",");
            Policy policy = null;
            String name = "Default Name";
            String genre = "Default Genre";
            String producer = "Default Producer";
            String actor = "Default Actor";

            int id = 0;

            if (st.hasMoreTokens()) {
                id = Integer.parseInt(st.nextToken());
            }

            if (st.hasMoreTokens()) {
                String strPolicy = st.nextToken().toLowerCase();
                policy = findPolicy(strPolicy);

            }
            if (st.hasMoreTokens()) {
                name = st.nextToken();
            }
            if (st.hasMoreTokens()) {
                genre = st.nextToken();
            }
            if (st.hasMoreTokens()) {
                producer = st.nextToken();
            }
            if (st.hasMoreTokens()) {
                actor = st.nextToken();
            }

            Movie movie = new Movie(policy, id,name, genre, producer, actor);
            movies.add(movie);
        }

        return movies;
    }
    /**
     * To create books with informations in file
     * @param file 
     * @return all Book objects
     */
    public ArrayList<Book> readBooks(String file) {
        ArrayList<String> lines = new ArrayList<String>();
        lines = dataAccessLayer.readFile(file);
        ArrayList<Book> books = new ArrayList<Book>();

        for (String line : lines) {
            StringTokenizer st = new StringTokenizer(line, ",");
            Policy policy = null;
            String name = "Default Name";
            String author = "Default Author";
            String publisher = "Default Publisher";
            int id = 0;

            if (st.hasMoreTokens()) {
                id = Integer.parseInt(st.nextToken());
            }
            if (st.hasMoreTokens()) {
                String strPolicy = st.nextToken().toLowerCase();
                policy = findPolicy(strPolicy);

            }
            if (st.hasMoreTokens()) {
                author = st.nextToken();
            }
            if (st.hasMoreTokens()) {
                name = st.nextToken();
            }
            if (st.hasMoreTokens()) {
                publisher = st.nextToken();
            }

            Book book = new Book(policy,id ,author, name, publisher);
            books.add(book);
        }

        return books;
    }
    private Policy findPolicy(String strPolicy){
        Policy policy = null;
        if (strPolicy.equals("new")) {
            NewRleasePolicy newRleasePolicy = new NewRleasePolicy();
            policy = (Policy) newRleasePolicy;
        } else if (strPolicy.equals("old")) {
            OldRleasePolicy oldRleasePolicy = new OldRleasePolicy();
            policy = (Policy) oldRleasePolicy;
        } else if (strPolicy.equals("bargain")) {
            BargainPolicy bargainPolicy = new BargainPolicy();
            policy = (Policy) bargainPolicy;
        }

        return policy;
    }

}

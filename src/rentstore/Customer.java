package rentstore;

import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int id;
    private CustomerType type;
    private double discount;
    private ArrayList<Movie> shoppingMovie;
    private ArrayList<Book> shoppingBook;

    public int getId() {
        return id;
    }

    public ArrayList<Movie> getShoppingMovie() {
        return shoppingMovie;
    }

    public ArrayList<Book> getShoppingBook() {
        return shoppingBook;
    }

    public Customer(int id, CustomerType type) {
        this.id = id;
        this.type = type;
        this.discount = findDiscount();
        this.shoppingBook = new ArrayList<Book>();
        this.shoppingMovie = new ArrayList<Movie>();

    }
    public void addMovie(Movie movie){
        shoppingMovie.add(movie);
    }
    public void addBook(Book book){
        shoppingBook.add(book);
    }
    public void removeBook(Book book){
        shoppingBook.remove(book);
    }
    public void removeMovie(Movie movie){
        shoppingMovie.remove(movie);
    }
    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
    private double findDiscount(){
        if (type == CustomerType.REGULAR){return 0;}
        else if (type == CustomerType.SILVER){return 0.10;}
        else if (type == CustomerType.GOLD){return 0.15;}
        else if (type == CustomerType.PREMIUM){return 0.20;}
        return -1;
    }
}

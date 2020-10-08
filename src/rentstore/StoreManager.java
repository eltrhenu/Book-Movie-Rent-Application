package rentstore;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;
import java.util.Date;

public class StoreManager {
        private ArrayList<Customer> customers;
        private ArrayList<Movie> movies;
        private ArrayList<Book> books;

        /**
         * create customer
         * @param id customer id
         * @param type customer type
         */
        public void newCustomer(int id,CustomerType type){
            Customer cust = new Customer(id,type);
            customers.add(cust);
        }
      
        public StoreManager(ArrayList<Movie> movies,ArrayList<Book> books){
            this.customers = new ArrayList<Customer>();
            this.books = books;
            this.movies = movies;
        }
        /**
         * To find total invoice
         * @param specificDay date to check
         * @return total invoice
         */
        public double totalInvoice(Date specificDay){
            double total = 0;
            for (Customer customer:customers ) {
                ArrayList<Movie> cusMov = customer.getShoppingMovie();
                ArrayList<Book> cusBook = customer.getShoppingBook();
                for (Movie mov:cusMov ) {
                    if(mov.isLate(specificDay)){
                        total += mov.lateInvoice(specificDay);
                    }
                    else {
                        total+= mov.getInvoice();
                    }
                }
                for (Book book:cusBook) {
                    if(book.isLate(specificDay)){
                        total += book.lateInvoice(specificDay);
                    }
                    else {
                        total+= book.getInvoice();
                    }

                }

            }
            return total*(-1);
        }
        /**
         * to rent item
         * @param cusId customer id
         * @param type item type
         * @param itemId item id
         * @param day date when rented
         */
        public void rent(int cusId,String type,int itemId,Date day) {
            for (Customer customer : customers) {
                if (customer.getId() == cusId) {
                    if (type.equals("movie")) {
                        for (Movie movie : movies) {
                            if (itemId == movie.getId()) {
                                movie.rent(day);
                                customer.addMovie(movie);
                                movie.setInvoice(customer.getDiscount() * movie.getInvoice());
                            }


                        }
                    }

                    else {
                        for (Book book : books) {
                            if (itemId == book.getId()) {
                                book.rent(day);
                                customer.addBook(book);
                                book.setInvoice(customer.getDiscount() * book.getInvoice());
                            }
                        }
                    }
                }
            }


        }
        /**
         * To return item to the store
         * @param cusId customer id
         * @param type item type
         * @param itemId item id
         * @param day date when rented
         * @return
         */
        public double returnI (int cusId,String type,int itemId,Date day){
            double late = 0.0;
            for (Customer customer : customers) {
                if (customer.getId() == cusId) {
                    if (type.equals("movie")) {
                        for (Movie movie : movies) {
                            if (itemId == movie.getId()) {

                                if(movie.isLate(day)){
                                    late = movie.getLateInvoice();

                                }
                                movie.returnI();
                                customer.removeMovie(movie);

                            }


                        }
                    }

                    else {
                        for (Book book : books) {
                            if (itemId == book.getId()) {
                                if (book.isLate(day)){
                                    late = book.getLateInvoice();
                                }
                                book.returnI();
                                customer.removeBook(book);
                            }
                        }
                    }
                }

            }
            return late;


    }
    /**
     * to find all rented item
     * @return all rented items
     */
    public String  totalRented(){
            ArrayList<Item> items = new ArrayList<Item>();
            for (Customer customer:customers) {
                ArrayList<Book> booksRented = customer.getShoppingBook();
                for (Book book:booksRented) {
                    items.add(book);

                }

                ArrayList<Movie> moviesRented = customer.getShoppingMovie();
                for (Movie movie:moviesRented) {
                    items.add(movie);

                }
            }
            return items.toString();
    }
    /**
     * to store
     * @param id item id
     * @param type item type
     * @param format JSON or XML
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public void store(int id,String type, String format) throws TransformerException, ParserConfigurationException {
         if(type.equals("movie")){
             for (Movie movie: movies ) {
                 if(id==movie.getId()){
                     movie.store(format);
                 }

             }
         }
         else{
             for (Book book: books  ) {
                 if(id==book.getId()){
                     book.store(format);
                 }
             }
         }
    }
    /**
     * to search
     * @param info to search
     * @param type item type
     * @return true if exists, false if not
     */
    public boolean search(String info,String type)  {
            int count = 0;
            if(type.equals("movie")){
                for (Movie movie: movies ) {
                    if( movie.search(info)) {

                        count++;
                    }

                }
            }
            else{
                for (Book book: books  ) {
                    if( book.search(info)) {

                        count++;
                    }

                }

            }
            return count>0;
    }

}

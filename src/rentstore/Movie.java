package rentstore;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
/**
 * inherits Item
 * @author DÝDEM
 *
 */
public class Movie extends Item{
    private String name;
    private int id;
    private String genre;
    private String producer;
    private String actor;
    private Date receiveDate;
    private double invoice;
   private double lateInvoice;
    private final static int RETURN_DATE = 2;


    public double getLateInvoice() {
        return lateInvoice;
    }

    public void setLateInvoice(double lateInvoice) {
        this.lateInvoice = lateInvoice;
    }

    public Movie(Policy policy, int id, String name, String genre, String producer, String actor) {
        super(policy);
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.producer = producer;
        this.receiveDate = null;
        this.lateInvoice = 0.0;

        this.actor = actor;
        this.invoice = 0.0;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }
    private void createInvoice(){

        double invoice = getPolicy().getCharge();
        setInvoice(invoice);
    }

    public double getInvoice() {
        return invoice;
    }

    public void setInvoice(double invoice) {
        this.invoice = invoice;
    }
    /**
     * find if item is late
     * @param today date to check
     * @return true if late, false if not
     */
    public boolean isLate(Date today){
        return receiveDate.before(today);
    }
    /**
     * to create late invoice
     * @param day date to check
     * @return total late invoice
     */
    public double lateInvoice(Date day){

        long lateDay = 0;
        if(isLate(day)){
            lateDay = day.getTime()-receiveDate.getTime();
            double late = getPolicy().getCharge()*lateDay ;
            setLateInvoice(late);

        }
        return lateInvoice ;
    }

    private void findReceiveDate(Date day){

        Date sumDate = new Date(day.getTime()+RETURN_DATE*24*60*60*1000);
        setReceiveDate(sumDate);
    }


    public String getName() {
        return name;
    }
    /**
     * to store item as JSON or XML
     */
    @Override
    public boolean store(String type) throws ParserConfigurationException, TransformerException {
        FileSaver fileSaver = new FileSaver();
        if(type.equals("xml")) {
            fileSaver.writeMovieXML(this);
            return true;
        }
        else if(type.equals("json")){
            fileSaver.writeMovieJSON(this);
            return true;
        }
        return false;

    }

    public String getGenre() {
        return genre;
    }

    public String getProducer() {
        return producer;
    }
    
    @Override
    public String toString() {
        return "Movie{" +
                "genre='" + genre + '\'' +
                ", producer='" + producer + '\'' +
                ", actor='" + actor + '\'' +
                '}';
    }

    public String getActor() {
        return actor;
    }
    /**
     * to rent item
     */
    @Override
    public Item rent(Date day) {
        if(super.getRentState() == false) {
            super.setRentState(true);
            findReceiveDate(day);
            createInvoice();
            return this;

        }
        return null;

    }

    public int getId() {
        return id;
    }
    /**
     * to return item to store
     */
    @Override
    public void returnI() {
        if(super.getRentState() == true) {
            super.setRentState(false);
            setReceiveDate(null);
            setInvoice(0);
        }

    }
    /**
     * to search item
     */
    @Override
    public boolean search(String search) {
        StringTokenizer st = new StringTokenizer(search,",");
        if(st.countTokens() == 1 ) {
            String strSearch = st.nextToken().toLowerCase();
            if (this.getActor().toLowerCase().equals(strSearch) || (this.getGenre().toLowerCase().equals(strSearch)) || (this.getProducer().toLowerCase().equals(strSearch))) {
                return true;
            }

        }

        else if (st.countTokens() == 2){
            String search1 = st.nextToken();
            String search2 = st.nextToken();

                if ((this.getActor().toLowerCase().equals(search1)) || (this.getGenre().toLowerCase().equals(search1)) || (this.getProducer().toLowerCase().equals(search1))) {
                    if ((this.getActor().toLowerCase().equals(search2)) || (this.getGenre().toLowerCase().equals(search2)) || (this.getProducer().toLowerCase().equals(search2))) {
                        return true;
                    }

                }

        }
        return false;
    }

}

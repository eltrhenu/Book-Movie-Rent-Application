package rentstore;
import javax.xml.parsers.ParserConfigurationException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
/**
 * Book inherits Item
 * @author DÝDEM
 *
 */
public class Book extends Item {
    private double invoice;
    private int id ;
    private String author;
    private String name;
    private double lateInvoice;
    private String publisher;
    private Date receiveDate;
    private static final int RETURN_DATE = 7;

    public Date getReceiveDate() {
        return receiveDate;
    }
    public int getId() {
        return id;
    }
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getInvoice() {
        return invoice;
    }

    public void setInvoice(double invoice) {
        this.invoice = invoice;
    }

    public Book(Policy policy,int id, String author, String name, String publisher) {

        super(policy);
        this.id = id;
        this.lateInvoice = 0.0;
        this.author = author;
        this.name = name;
        this.publisher = publisher;
        this.receiveDate = null;
        this.invoice = 0;

    }

    public double getLateInvoice() {
        return lateInvoice;
    }

    public void setLateInvoice(double lateInvoice) {
        this.lateInvoice = lateInvoice;
    }

    private void createInvoice(){

        double invoice =getPolicy().getCharge();
        setInvoice(invoice);
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
    /**
     * To look if item is late
     * @param today date to control
     * @return true if late, false if not
     */
    public boolean isLate(Date today){
        return receiveDate.before(today);
    }
    /**
     * To create late invoice for each late day
     * @param day date to control
     * @return lateInvoice
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
    /**
     * To rent item
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
    /**
     * To return Item to the store
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
     * To search item if exists
     * @return true if exists, false if not
     */
    @Override
    public boolean search(String search) {
        StringTokenizer st = new StringTokenizer(search,",");
        if(st.countTokens() == 1 ){
            String strSearch = st.nextToken().toLowerCase();

            if(this.getName().toLowerCase().equals(strSearch)||(this.getAuthor().toLowerCase().equals(strSearch))||(this.getPublisher().toLowerCase().equals(strSearch))){
                    return true;

            }

        }

        else if (st.countTokens() == 2){
            String search1 = st.nextToken().toLowerCase();
            String search2 = st.nextToken().toLowerCase();

            if (this.getName().toLowerCase().equals(search1)||(this.getAuthor().toLowerCase().equals(search1))||(this.getPublisher().toLowerCase().equals(search1))) {
                if (this.getName().toLowerCase().equals(search2)||(this.getAuthor().toLowerCase().equals(search2))||(this.getPublisher().toLowerCase().equals(search2))){
                    return true;
                    }

                }

        }
        return false;
    }
    /**
     * To store item in XML or JSON format
     */
    @Override
    public boolean store(String type) throws ParserConfigurationException {
        FileSaver fileSaver = new FileSaver();
        if(type.equals("xml")) {
            fileSaver.writeBookXML(this);
            return true;
        }
        else if(type.equals("json")){
            fileSaver.writeBookJSON(this);
            return true;
        }
        return false;

    }
}

package rentstore;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuExecutions {
    private StoreManager manager;

    public MenuExecutions(StoreManager manager) {
        this.manager = manager;
    }
    /**
     * to find total invoice
     * @param day specific date
     * @return total invoice as string 
     * @throws ParseException
     */
    public String totalInv(String day) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date inputDate = format.parse(day);
        double total = manager.totalInvoice(inputDate);
        String out="Total invoice : " + total;
        return out;

    }
    /**
     * to rent an item
     * @param cusId customer ID
     * @param type item type
     * @param item item ID
     * @param day date when rented item
     * @throws ParseException
     */
    public void rent(int cusId,String type,int item,String day) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date inputDate = format.parse(day);
        manager.rent(cusId,type,item,inputDate);
    }
    /**
     * To return item to the store
     * @param cusId customer ID
     * @param type item type
     * @param item item ID
     * @param day date
     * @throws ParseException
     */
    public void returnI(int cusId,String type,int item,String day) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date inputDate = format.parse(day);
        manager.returnI(cusId,type,item,inputDate);
    }
    /**
     * To get all rented item
     * @return rented items as a string
     */
    public String totalRented(){
        String out = manager.totalRented();
        return out ;
    }
    /**
     * to add new customer to the store
     * @param id customer ID
     * @param type customer type
     */
    public void add(int id,String type){
        CustomerType cusType = null;
        if (type == CustomerType.REGULAR.toString()){cusType = CustomerType.REGULAR;}
        else if (type == CustomerType.SILVER.toString()){cusType = CustomerType.SILVER;}
        else if (type == CustomerType.GOLD.toString()){cusType = CustomerType.GOLD;}
        else if (type == CustomerType.PREMIUM.toString()){cusType = CustomerType.PREMIUM;}
        manager.newCustomer(id,cusType);
    }
    /**
     * To store specific item
     * @param id item ID
     * @param type item Type
     * @param format JSON or XML
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public void store(int id,String type,String format) throws TransformerException, ParserConfigurationException {
        manager.store(id,type,format);
    }
    /**
     * To search 
     * @param info to search
     * @param type item type
     * @return true if exists, false if not
     */
    public boolean search(String info,String type){
       return manager.search(info,type);
    }


}

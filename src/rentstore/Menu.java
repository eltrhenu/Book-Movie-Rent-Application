package rentstore;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.text.ParseException;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Menu {
    private StoreManager manager;

    public Menu(StoreManager manager) {
        this.manager = manager;
    }

    private static Scanner in = new Scanner(System.in);
    /**
     * to display operation menu
     */
    public static void MenuStart() {
        System.out.println();
        System.out.println("To rent you have to add customer first.");
        System.out.println("[1] Total Amount of Invoices for a specific day.");
        System.out.println("[2] Rent item.");
        System.out.println("[3] Return item.");
        System.out.println("[4] All rented items.");
        System.out.println("[5] Add new customer.");
        System.out.println("[6] To store Item ; enter item type,item id,formt type(JSON/XML).");
        System.out.println("[7] To search Item ; enter item info and item type seperate by comma.");
        System.out.println("[8] Quit.");
        System.out.println();
        System.out.print(">> Please select a command to execute: ");
    }

    /**
     * To execute menu
     * @return true if operation continue, false if not
     * @throws ParseException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public  boolean getAndExecuteMainMenu() throws ParseException, TransformerException, ParserConfigurationException {
        MenuExecutions menu = new MenuExecutions(manager);
        int cmdId = in.nextInt();
        in.nextLine();
        switch (cmdId) {
            case 1:
                System.out.println();
                System.out.print("Please enter a date in yyyy-mm-dd format. ");

                boolean check = false;
                while (!check) {
                    String day = in.nextLine();
                    if (checkDate(day)) {
                        System.out.println(menu.totalInv(day));
                        check = true;
                    } else {
                        System.out.println();
                        System.out.print("Please enter a date in yyyy-mm-dd format. ");
                        check = false;
                    }
                }
                return true;
            case 2:
                System.out.println();
                System.out.print("Please enter info in this format : customer id,type of item, item number ,day of operation (Date must be in this format yyyy-mm-dd.)");

                check = false;
                while (!check) {
                    String info = in.nextLine();
                    StringTokenizer st = new StringTokenizer(info, ",");
                    String day = null;
                    String type = null;
                    int itemId = 0;
                    int custId = 0;

                    if (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        if (isInteger(token)) {
                            custId = Integer.parseInt(token);
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter a valid info: customer number, item type, item number, operation day seperated by comma and date in yyyy-mm-dd format. ");
                            check = false;
                            continue;
                        }

                    }
                    if (st.hasMoreTokens()) {
                        String token = st.nextToken().toLowerCase();
                        if ((token.equals("book")) || (token.equals("movie"))) {
                            type = token;
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter a valid info: customer number, item type, item number, operation day seperated by comma and date in yyyy-mm-dd format. ");
                            check = false;
                            continue;
                        }
                    }

                    if (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        if (isInteger(token)) {
                            itemId = Integer.parseInt(token);
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter a valid info: customer number, item type, item number, operation day seperated by comma and date in yyyy-mm-dd format. ");
                            check = false;
                            continue;
                        }
                    }

                    if (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        if (checkDate(token)) {
                            day = token;
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter a valid info: customer number, item type, item number, operation day seperated by comma and date in yyyy-mm-dd format. ");
                            check = false;
                            continue;
                        }
                    }
                    menu.rent(custId, type, itemId, day);
                    System.out.println("Rent operation has succeeded.");
                }

                return true;
            case 3:
                System.out.println();
                System.out.print("Please enter info in this format : customer id,type of item, item number ,day of operation (Date must be in this format yyyy-mm-dd.)");

                check = false;
                while (!check) {
                    String info = in.nextLine();
                    StringTokenizer st = new StringTokenizer(info, ",");
                    String day = null;
                    String type = null;
                    int itemId = 0;
                    int custId = 0;

                    if (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        if (isInteger(token)) {
                            custId = Integer.parseInt(token);
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter a valid info: customer number, item type, item number, operation day seperated by comma and date in yyyy-mm-dd format. ");
                            check = false;
                            continue;
                        }

                    }
                    if (st.hasMoreTokens()) {
                        String token = st.nextToken().toLowerCase();
                        if ((token.equals("book")) || (token.equals("movie"))) {
                            type = token;
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter a valid info: customer number, item type, item number, operation day seperated by comma and date in yyyy-mm-dd format. ");
                            check = false;
                            continue;
                        }
                    }

                    if (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        if (isInteger(token)) {
                            itemId = Integer.parseInt(token);
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter a valid info: customer number, item type, item number, operation day seperated by comma and date in yyyy-mm-dd format. ");
                            check = false;
                            continue;
                        }
                    }

                    if (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        if (checkDate(token)) {
                            day = token;
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter a valid info: customer number, item type, item number, operation day seperated by comma and date in yyyy-mm-dd format. ");
                            check = false;
                            continue;
                        }
                    }
                    menu.returnI(custId, type, itemId, day);
                    System.out.println("Return operation has succeeded.");
                }
                return true;
            case 4:
                System.out.println(menu.totalRented());
                return true;
            case 5:
                System.out.println();
                System.out.print("Please enter info in this format : customer id,customer type.");
                check = false;
                while (!check) {
                    String info = in.nextLine();
                    StringTokenizer st = new StringTokenizer(info, ",");
                    int id = 0;
                    String type = "";
                    if (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        if (isInteger(token)) {
                            id = Integer.parseInt(token);
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter a valid info: customer id, customer type.");
                            check = false;
                            continue;
                        }
                    }
                    if (st.hasMoreTokens()) {
                        String token = st.nextToken().toLowerCase();
                        if ((token.equals("premium")) || (token.equals("gold")) || (token.equals("regular")) || (token.equals("silver"))) {
                            type = token;
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter a valid info: customer number, customer type");
                            check = false;
                            continue;

                        }
                    }
                    menu.add(id, type);
                    System.out.println("Addition has succeeded.");
                }

                return true;
            case 6:
                System.out.println();
                System.out.print("Please enter info in this format : item id,item type, format type (JSON/XML).");
                check = false;
                while (!check) {
                    String info = in.nextLine();
                    StringTokenizer st = new StringTokenizer(info, ",");
                    int id = 0;
                    String type = "";
                    String format = "";
                    if (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        if (isInteger(token)) {
                            id = Integer.parseInt(token);
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter info in this format : item id,item type, format type (JSON/XML).");
                            check = false;
                            continue;
                        }
                    }
                    if (st.hasMoreTokens()) {
                        String token = st.nextToken().toLowerCase();
                        if ((token.equals("movie")) || (token.equals("book"))) {
                            type = token;
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter info in this format : item id,item type, format type (JSON/XML).");
                            check = false;
                            continue;

                        }
                    }
                    if (st.hasMoreTokens()) {
                        String token = st.nextToken().toLowerCase();
                        if ((token.equals("json")) || (token.equals("xml"))) {
                            format = token;
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter info in this format : item id,item type, format type (JSON/XML).");
                            check = false;
                            continue;

                        }
                    }
                menu.store(id,type,format);
                }
            return true;
            case 7:
                String info = "";

                String type = "";
                check = false;
                while(!check){
                System.out.println();
                System.out.print("Please enter info,item type to search for Movie: by genre, by producer, by\n" +
                        "actor and also by any two combinations. For Book: by author, by book name, by publisher and also by any\n" +
                        "two combinations.\n.");
                String input = in.nextLine();

                    StringTokenizer st = new StringTokenizer(input, ",");
                    if(st.hasMoreTokens()){
                        info = st.nextToken();
                    }
                    if(st.hasMoreTokens()){
                        type = st.nextToken().toLowerCase();
                        if ((type.equals("movie")) || (type.equals("book"))) {
                            check = true;
                        } else {
                            System.out.println();
                            System.out.print("Please enter info,item type,item id to search for Movie: by genre, by producer, by\n" +
                                    "actor and also by any two combinations. For Book: by author, by book name, by publisher and also by any\n" +
                                    "two combinations.\n.");
                            check = false;
                            continue;

                        }
                    }

                }
                if(menu.search(info,type)){
                    System.out.println(type+ " exists.");
                }
                else{
                    System.out.println(type+ "does not exist.");
                }
                return true;
            case 8:

                return false;
            default:
                System.err.println();
                System.err.println("I do not know what to do for command id " + cmdId);
                System.err.println("Please try again!");
                System.err.println();
                return true;
        }
    }
    private boolean checkDate (String date){
        String[] tempArray = date.split("-");
        int[] intAr = new int[tempArray.length];
        int i =0;
        int j =tempArray.length-1;
        boolean check = true;
        while (i<=j) {
            if(isInteger(tempArray[i])){
                intAr[i] = Integer.parseInt(tempArray[i]);

                i++;
                check = true;

            }

            else{

                check = false;
            }
        }
        if(!(isMonth(intAr[1])&&isValidDay(intAr[2]))){

            check = false;

        }
        return check;
    }
    private  boolean isMonth(int month){
        if((1 <= month) &&(month <= 12)){
            return true;
        }
        else{
            return false;
        }
    }
    private  boolean isValidDay(int day){
        if((1 <= day) &&(day <= 31)){
            return true;
        }
        else{
            return false;
        }
    }
    private  boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        if (str.isEmpty()) {
            return false;
        }
        if(str.charAt(0)=='-'){
            return false;

        }
        for (int i =0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }

        }
        return true;
    }

}

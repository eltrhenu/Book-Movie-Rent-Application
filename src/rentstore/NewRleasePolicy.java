package rentstore;
/**
 * inherits Policy
 * @author DÝDEM
 *
 */
public class NewRleasePolicy extends Policy {
    private static final double CHARGE = 2;
    private static final double OVERDUE_CHARGE = 1;
    public NewRleasePolicy(){
        super(CHARGE,OVERDUE_CHARGE);
    }
}

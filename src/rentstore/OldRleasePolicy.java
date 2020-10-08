package rentstore;
/**
 * inherits Policy
 * @author DÝDEM
 *
 */
public class OldRleasePolicy extends Policy {
    private static final double CHARGE = 1.5;
    private static final double OVERDUE_CHARGE = 0.75;
    public OldRleasePolicy(){
        super(CHARGE,OVERDUE_CHARGE);
    }

}

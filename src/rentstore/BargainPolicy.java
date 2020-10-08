package rentstore;
/**
 * Bargain Policy inherits Policy.
 * @author DÝDEM
 *
 */
public class BargainPolicy extends Policy{
    private static final double CHARGE = 1;
    private static final double OVERDUE_CHARGE = 0.5;
    public BargainPolicy(){
        super(CHARGE,OVERDUE_CHARGE);
    }
}

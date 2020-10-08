package rentstore;

public class Policy {
    private double charge;
    private double overdueCharge;

    public Policy(double charge, double overdueCharge) {
        this.charge = charge;
        this.overdueCharge = overdueCharge;
    }

    public double getCharge() {
        return charge;
    }


    public double getOverdueCharge() {
        return overdueCharge;
    }

}

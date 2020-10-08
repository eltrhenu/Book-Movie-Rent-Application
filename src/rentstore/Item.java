package rentstore;



public abstract class Item implements IRentable,ISearchable,IStoreable{
    private Policy policy;
    private boolean rentState;

    protected Item(Policy policy) {
        this.policy = policy;
        this.rentState = false;
    }

    public Policy getPolicy() {
        return policy;
    }

    public boolean getRentState() {
        return rentState;
    }

    public void setRentState(boolean rentState) {
        this.rentState = rentState;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }


}

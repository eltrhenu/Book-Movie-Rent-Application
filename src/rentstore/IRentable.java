package rentstore;

import java.util.Date;

public interface IRentable {
	/**
	 * To rent item
	 * @param day specific date
	 * @return rented item
	 */
    public Item rent(Date day);
    /**
     * to return item to the store
     */
    public void returnI();
}

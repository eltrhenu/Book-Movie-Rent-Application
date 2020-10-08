package rentstore;



public interface ISearchable {
	/**
	 * To search 
	 * @param search info to search
	 * @return true if item exists, false if not
	 */
    public boolean search(String search);

}

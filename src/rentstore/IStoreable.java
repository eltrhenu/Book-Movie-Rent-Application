package rentstore;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

public interface IStoreable {
	/**
	 * To store item
	 * @param type JSON or XML
	 * @return true if operation succeeded, false if not
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
    public boolean store(String type) throws ParserConfigurationException, TransformerException;
}

package rentstore;
import org.json.JSONException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.sun.org.apache.xml.internal.serialize.XML11Serializer;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FileSaver {
	/**
	 * To write the book in XML format
	 * @param book
	 * @throws ParserConfigurationException
	 */

    public void writeBookXML(Book book)throws ParserConfigurationException{
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("items");
            doc.appendChild(rootElement);

            /** Book */
            Element bookXml = doc.createElement("book");
            rootElement.appendChild(bookXml);

            /**  to create id for books */
            int i = 1;
            Attr attr = doc.createAttribute("id");
            attr.setValue(Integer.toString(i));
            i++;
            bookXml.setAttributeNode(attr);
            /** Author */
            Element author = doc.createElement("author");
            author.appendChild(doc.createTextNode(book.getAuthor()));
            bookXml.appendChild(author);
            /** BookName */
            Element bookName = doc.createElement("name");
            bookName.appendChild(doc.createTextNode(book.getName()));
            bookXml.appendChild(bookName);
            /** Publisher */
            Element publisher = doc.createElement("publisher");
            publisher.appendChild(doc.createTextNode(book.getPublisher()));
            bookXml.appendChild(publisher);

            /** To write XML. */
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(book.getName()+".xml"));

            transformer.transform(source, result);
        }
        catch (Exception e) {
        e.printStackTrace();
    }

    }
    /**
	 * To write the book in JSON format
	 * @param book
	 *
	 */
    public void writeBookJSON(Book book){
        JSONObject MyJsonObject = new JSONObject();
        try {
            MyJsonObject.put("Author", book.getAuthor());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            MyJsonObject.put("Name", book.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            MyJsonObject.put("Publisher", book.getPublisher());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(book.getName()+".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(MyJsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    /**
	 * To write the movie in XML format
	 * @param movie
	 * @throws ParserConfigurationException,TransformerException
	 */
    public void writeMovieXML(Movie movie) throws ParserConfigurationException, TransformerException {
        try {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        /** Items */
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("Movie");
        doc.appendChild(rootElement);
        Element movieXml = doc.createElement("movie");
        rootElement.appendChild(movieXml);

        /**  to create id for movies */
        int i = 1;
        Attr attr = doc.createAttribute("id");
        attr.setValue(Integer.toString(i));
        i++;
        movieXml.setAttributeNode(attr);
        /** Genre */
        Element genre = doc.createElement("genre");
        genre.appendChild(doc.createTextNode(movie.getGenre()));
        movieXml.appendChild(genre);
        /** Producer */
        Element producer = doc.createElement("producer");
        producer.appendChild(doc.createTextNode(movie.getProducer()));
        movieXml.appendChild(producer);
        /** Actor */
        Element actor = doc.createElement("actor");
        actor.appendChild(doc.createTextNode(movie.getActor()));
        movieXml.appendChild(actor);

        /** To write XML. */
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(movie.getName()+".xml"));

        transformer.transform(source, result);
    } catch (Exception e)

    {
        e.printStackTrace();
    }

    }
    /**
   	 * To write the movie in JSON format
   	 * @param movie
   	 * 
   	 */
    public void writeMovieJSON(Movie movie){
        JSONObject MyJsonObject = new JSONObject();

        try {
            MyJsonObject.put("Genre", movie.getGenre());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            MyJsonObject.put("Actor", movie.getActor());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            MyJsonObject.put("Producer", movie.getProducer());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(movie.getName()+".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(MyJsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * To write all books in XML format
     * @param books
     * @throws ParserConfigurationException
     */
    public void writeBooksXML(ArrayList<Item> books) throws ParserConfigurationException{
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            /** Items */
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("items");
            doc.appendChild(rootElement);
            for (Item item:books ) {
                Book book = (Book) item;
                /** Book */
                Element bookXml = doc.createElement("book");
                rootElement.appendChild(bookXml);

                /**  to create id for books */
                int i = 1;
                Attr attr = doc.createAttribute("id");
                attr.setValue(Integer.toString(i));
                i++;
                bookXml.setAttributeNode(attr);
                /** Author */
                Element author = doc.createElement("author");
                author.appendChild(doc.createTextNode(book.getAuthor()));
                bookXml.appendChild(author);
                /** BookName */
                Element bookName = doc.createElement("name");
                bookName.appendChild(doc.createTextNode(book.getName()));
                bookXml.appendChild(bookName);
                /** Publisher */
                Element publisher = doc.createElement("publisher");
                publisher.appendChild(doc.createTextNode(book.getPublisher()));
                bookXml.appendChild(publisher);

            }



            /** To write XML. */
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("fileBook.xml"));

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * To write all movies in XML format
     * @param movies
     * @throws ParserConfigurationException
     */
    public void writeMoviesXML(ArrayList<Item> movies) throws ParserConfigurationException{
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            /** Items */
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("items");
            doc.appendChild(rootElement);
            for (Item item:movies ) {
                Movie movie = (Movie) item;
                /** Movie */
                Element bookXml = doc.createElement("book");
                rootElement.appendChild(bookXml);

                /**  to create id for movies */
                int i = 1;
                Attr attr = doc.createAttribute("id");
                attr.setValue(Integer.toString(i));
                i++;
                bookXml.setAttributeNode(attr);
                /** Genre */
                Element genre = doc.createElement("genre");
                genre.appendChild(doc.createTextNode(movie.getGenre()));
                bookXml.appendChild(genre);
                /** Producer */
                Element producer = doc.createElement("producer");
                producer.appendChild(doc.createTextNode(movie.getProducer()));
                bookXml.appendChild(producer);
                /** Actor */
                Element actor = doc.createElement("actor");
                actor.appendChild(doc.createTextNode(movie.getActor()));
                bookXml.appendChild(actor);

            }



            /** To write XML. */
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("fileMovie.xml"));

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * To write all movies inJSON format
     * @param movies
     */
    public void writeMoviesJSON(ArrayList<Item> movies)  {
        JSONObject MyJsonObject = new JSONObject();
        JSONArray MyJsonArray=new JSONArray();
        for (Item item:movies) {
            Movie movie = (Movie) item;
            try {
                MyJsonObject.put("Genre", movie.getGenre());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                MyJsonObject.put("Actor", movie.getActor());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                MyJsonObject.put("Producer", movie.getProducer());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MyJsonArray.put(MyJsonObject);
        }


        FileWriter writer = null;
        try {
            writer = new FileWriter("movies.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(MyJsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * To write all books in JSON format
     * @param books
     */
    public void writeBooksJSON(ArrayList<Item> books)  {
        JSONObject MyJsonObject = new JSONObject();
        JSONArray MyJsonArray=new JSONArray();
        for (Item item:books) {
            Book book = (Book) item;
            try {
                MyJsonObject.put("Author", book.getAuthor());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                MyJsonObject.put("Name", book.getName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                MyJsonObject.put("Publisher", book.getPublisher());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MyJsonArray.put(MyJsonObject);
        }



        FileWriter writer = null;
        try {
            writer = new FileWriter("books.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(MyJsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}





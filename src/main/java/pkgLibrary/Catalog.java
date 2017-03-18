package pkgLibrary;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

	@XmlAttribute
	int id;

	@XmlElement(name = "book")
	ArrayList<Book> books;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	
	
	
	public static Book getBook(String id) throws BookException {
		try{
			Catalog cat = ReadXMLFile();
			for(Book bk : cat.getBooks()){
				
				if(bk.getId().equals(id))
					return bk;
			}
			throw new BookException();
		}catch(BookException e){
			System.out.println("not.");
			return null;
		}
	}
	
	
	
	
	
	
	
	

	public static Catalog addBook(int id, Book book)throws BookException {
		Catalog cat = ReadXMLFile();
		if (id == cat.getId()) {
			for (int i = 0; i < cat.getBooks().size(); i++) {
					if (book.getId().equals(cat.getBooks().get(i).getId())) {
						System.out.println("have book");
						throw new BookException(book);
					}
					

			}

			cat.getBooks().add(book);
	
		}
		return cat;

	}


	private static Catalog ReadXMLFile() {

		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "/src/main/resources/XMLFiles/Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return cat;

	}
}

package pkgEmpty;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pkgLibrary.Book;
import pkgLibrary.BookException;
import pkgLibrary.Catalog;

public class TestCatalog {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testbook(){
		Book book= new Book();
		book.setAuthor("zhuyi");
		book.setCost(20.00);
		book.setDescription("this book is mine");
		book.setGenre("stor");
		book.setId("bk000");
		Date d= new Date();
		book.setPublish_date(d);
		book.setTitle("happiness");
		book.setPrice(50.00);
		Book book2 = new Book("bk000", "zhuyi", "happiness", "stor", 20, d, "this book is mine");
	}
	
	@Test 
	public void textbook1() throws BookException {
		Catalog cat= ReadXMLFile();
		cat.getBook("bk101");
	}
	
	@Test  
	public void testbook2() throws BookException {
		Catalog cat= ReadXMLFile();
		cat.getBook("bk102");
	}
	@Test 
	public void testbook3() throws BookException {
		Book b = new Book("bk101");
		Catalog cat = new Catalog();
		cat = ReadCatalog();
		cat.addBook(1,b);
	}
	
	@Test  
	public void testaddbook4() throws BookException {
		Catalog cat = new Catalog();
		cat = ReadCatalog();
		Book b = new Book("bk000", "zhuyi", "happiness", "stor", 20, new Date(), "this book is mine");
		cat.addBook(0,b);
	}
	
	
	
	
	
	
	
	private static void WriteXMLFile(Catalog cat) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "/src/main/resources/XMLFiles/Books.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cat, file);
			jaxbMarshaller.marshal(cat, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
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

	private static Catalog ReadCatalog() {
		Catalog cat = ReadXMLFile();

		System.out.println("cat ID " + cat.getId());
		System.out.println("Book count: " + cat.getBooks().size());

		return cat;
	}


}

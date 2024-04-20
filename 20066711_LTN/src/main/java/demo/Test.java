package demo;

import java.util.List;

import dao.AuthorDAO;
import dao.BookDAO;
import dao.EntityManagerFactoryUtil;
import entities.Author;
import entities.Book;
import jakarta.persistence.EntityManager;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactoryUtil mangerFactoryUtil = new EntityManagerFactoryUtil();

		EntityManager entityManager = mangerFactoryUtil.getEnManager();

		// Create repositories
		BookDAO bookDAO = new BookDAO(entityManager);
		AuthorDAO authorDAO = new AuthorDAO(entityManager);
		
//		List<Author> authors = authorDAO.getListAuthorByBookName("Book 2");
//		authors.forEach(System.out::println);
		
		List<Book> books = bookDAO.getListBookMoreThan2AuthorsUsingCriteriaAPI();
		books.forEach(System.out::println);

//		// Create an author and add 3 books to his list of books
//		
//		Book book1 = new Book("Book 1");
//		Book book2 = new Book("Book 2");
//		Book book3 = new Book("Book 3");
//		
//		Author author1 = new Author("Author 1");
//		Author author2 = new Author("Author 2");
//		
//		// Establish relationships between objects
//		book1.getAuthors().add(author1);
//		book1.getAuthors().add(author2);
//		
//		book2.getAuthors().add(author1);
//		book2.getAuthors().add(author2);
//		
//		book3.getAuthors().add(author1);
//	
//		author1.getBooks().add(book1);
//		author1.getBooks().add(book2);
//		author1.getBooks().add(book3);
//		
//		author2.getBooks().add(book1);
//		author2.getBooks().add(book2);
//
//		entityManager.getTransaction().begin();
//
//		entityManager.persist(book1);
//		entityManager.persist(book2);
//		entityManager.persist(book3);
//		
//		entityManager.persist(author1);
//		entityManager.persist(author2);
//
//		entityManager.getTransaction().commit();

//		// Find all authors
//		List<Author> authors = authorDAO.findAll();
//		System.out.println("Authors:");
//		authors.forEach(System.out::println);
//
//		// Find all books
//		List<Book> books = bookDAO.findAll();
//		System.out.println("Books:");
//		books.forEach(System.out::println);
		
//		// Find a book by name
//		Book book = bookDAO.findByName("Book 1");
//		System.out.println("Query for book 1: " + book);
//		
//		boolean result = bookDAO.delete(book);
//		System.out.println(result);
		
//		// Update Book 2 -> Book 22
//		queryBook1.setBookName("Book 22");
//		Book updateBook1 = bookDAO.update(queryBook1);
//		System.out.println("updateBook1 -> Book 22: " + updateBook1);

//		// Find author by name
//		Author authorByName = authorDAO.findByName("Author 1");
//		System.out.println("Searching for an author by name: " + authorByName);
//		
//
//		// Search for a book by ID
//		Book foundBook = bookDAO.findById(2);
//		System.out.println("foundBook - 2: " + foundBook);
//		
//		
//		Book foundBookCriteriaAPI = bookDAO.findByIdUsingCriteriaAPI(2);
//		System.out.println("foundBookCriteriaAPI - 2: " + foundBookCriteriaAPI);
//
//		// Search for a book with an invalid ID
//		Book notFoundBook = bookDAO.findById(99);
//		System.out.println("notFoundBook - 99 : " + notFoundBook);
//
//		// List all books
//		List<Book> books = bookDAO.findAll();
//		System.out.println("Books in database:");
//		books.forEach(System.out::println);
//
//		// Find a book by name
//		Book queryBook1 = bookDAO.findByName("Book 2");
//		System.out.println("Query for book 2: " + queryBook1);
//		
//		// Update Book 2 -> Book 22
//		queryBook1.setBookName("Book 22");
//		Book updateBook1 = bookDAO.update(queryBook1);
//		System.out.println("updateBook1 -> Book 22: " + updateBook1);
//		
//		// Find a book by name
//		Book queryBook22 = bookDAO.findByName("Book 22");
//		System.out.println("Query for book 22: " + queryBook22);
//
//		// Find a book by name using a named query
//		Book queryBook2 = bookDAO.findByNameNamedQuery("Book 3");
//		System.out.println("Query for book 3: " + queryBook2);
//		
//		List<Book> listBook = bookDAO.getListBookTheSameAuthorUsingCriteriaAPI();
//		System.out.println(listBook);

		mangerFactoryUtil.closeEnManager();
		mangerFactoryUtil.closeEnManagerFactory();
	}

}

package entities;

import java.util.HashSet;
import java.util.Set;

//import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
@NamedQueries(
		{ 
			@NamedQuery(name = "Book.findByName", query = "SELECT b FROM Book b WHERE b.bookName = :bookName"),
			@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b") 
		}
)
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	private String bookName;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "bookId", referencedColumnName = "bookId"), inverseJoinColumns = @JoinColumn(name = "authorId", referencedColumnName = "authorId"))
	private Set<Author> authors = new HashSet<Author>();

	public Book() {
	}

	public Book(String bookName) {
		this.bookName = bookName;
	}

	
	/**
	 * @return the bookId
	 */
	public Long getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the authors
	 */
	public Set<Author> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + "]";
	}

}
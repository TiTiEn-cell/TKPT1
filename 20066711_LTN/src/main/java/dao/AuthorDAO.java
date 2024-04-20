package dao;

import java.util.List;

import entities.Author;
import entities.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public class AuthorDAO {

	private EntityManager entityManager;

	public AuthorDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Author save(Author author) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(author);
			entityManager.getTransaction().commit();

			return author;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Author findById(Integer id) {
		Author author = entityManager.find(Author.class, id);
		return author;
	}

	public List<Author> findAll() {
		return entityManager.createQuery("SELECT a FROM Author a").getResultList();
	}

	public Author findByName(String name) {
		Author author = entityManager.createNamedQuery("Author.findByName", Author.class)
				.setParameter("authorName", name).getSingleResult();
		return author;
	}

	// Lấy tất cả tác giả của một quyển sách cụ thể
	// Criteria API
	public List<Author> getListAuthorByBookName(String bookName) {
		
		// Step 1: create CriteriaBuilder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		// Step 2: create CriteriaQuery
		CriteriaQuery<Author> cq = cb.createQuery(Author.class);
		
		Root<Author> authorRoot = cq.from(Author.class);
		
		// Step 3: INNER JOIN books
		Join<Author, Book> bookJoin = authorRoot.join("books", JoinType.INNER);
		
		// Step 4: Select + WHERE
		cq.select(authorRoot).where(cb.equal(bookJoin.get("bookName"), bookName));

		// Step 5: Create query
		TypedQuery<Author> query = entityManager.createQuery(cq);

		return query.getResultList();
	}

}

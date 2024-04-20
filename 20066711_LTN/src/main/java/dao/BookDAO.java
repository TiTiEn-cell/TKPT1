package dao;

import java.util.List;

import entities.Author;
import entities.Author_;
import entities.Book;
import entities.Book_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public class BookDAO {

	private EntityManager entityManager;

	public BookDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Book save(Book book) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(book); // Insert
			entityManager.getTransaction().commit();
			return book;
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

	public Book findById(Integer id) {
		return entityManager.find(Book.class, id);
	}

	public List<Book> findAll() {
		return entityManager.createNamedQuery("Book.findAll", Book.class).getResultList();
	}

	public Book findByName(String name) {
		Book book = entityManager.createQuery("SELECT b FROM Book b WHERE b.bookName = :name", Book.class)
				.setParameter("name", name).getSingleResult();
		return book;
	}

	public Book findByNameNamedQuery(String name) {
		Book book = entityManager.createNamedQuery("Book.findByName", Book.class).setParameter("bookName", name)
				.getSingleResult();
		return book;
	}

	public Book update(Book book) {
		try {
			entityManager.getTransaction().begin();
			Book mergedEntity = entityManager.merge(book);
			entityManager.getTransaction().commit();
			return mergedEntity;
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(Book book) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(book);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	public Book findByIdUsingCriteriaAPI(Integer id) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);

		Root<Book> root = query.from(Book.class);
		query.select(root).where(builder.equal(root.get("id"), id));

		TypedQuery<Book> q = entityManager.createQuery(query);
		return q.getSingleResult();

	}

	public List<Book> getListAllUsingCriteriaAPI() {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);

		Root<Book> root = query.from(Book.class);
		query.select(root);

		TypedQuery<Book> q = entityManager.createQuery(query);

		return q.getResultList();

	}

	// Lấy danh sách Book có từ 2 author trở lên
	public List<Book> getListBookMoreThan2AuthorsUsingCriteriaAPI() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		
		Root<Book> bookRoot = cq.from(Book.class);
		
		Join<Book, Author> typeJoin = bookRoot.join(Book_.authors);
		
		cq.groupBy(bookRoot.get(Book_.bookId));
		
		cq.having(cb.ge(cb.count(typeJoin.get(Author_.authorId)), 2));

		TypedQuery<Book> typedQuery = entityManager.createQuery(cq);

		return typedQuery.getResultList();

	}

	// Lấy tất cả các sách cùng với tác giả
	public List<Book> getListBookTheSameAuthorUsingCriteriaAPI() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		Root<Book> bookRoot = cq.from(Book.class);
		bookRoot.join(Book_.authors, JoinType.INNER);
		cq.select(bookRoot);
		TypedQuery<Book> query = entityManager.createQuery(cq);
		return query.getResultList();
	}
}

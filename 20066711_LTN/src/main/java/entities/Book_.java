package entities;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Book.class)
public class Book_ {
  
  public static volatile SingularAttribute<Book, Long> bookId;
  public static volatile SingularAttribute<Book, String> bookName;
  public static volatile SetAttribute<Book, Author> authors;
}
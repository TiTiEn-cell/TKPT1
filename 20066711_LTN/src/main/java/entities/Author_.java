package entities;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Author.class)
public class Author_ {
  
  public static volatile SingularAttribute<Author, Long> authorId;
  public static volatile SingularAttribute<Author, String> authorName;
  public static volatile SetAttribute<Author, Book> books;
}

package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class Book {
  private String isbn;
  private String title;
  private String subTitle;
  private String author;
  private Date publish_date;
  private String publisher;
  private Integer pages;
  private String description;
  private String website;

    public Book(){}

}
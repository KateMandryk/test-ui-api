package models;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
   private String userID;
   private String username;
   private Book books;

   public User(){}
}

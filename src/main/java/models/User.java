package models;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
@Getter
@Setter
public class User {
   private String userID;
   private String username;
   @Ignore
   private Book books;

   public User(){}
}

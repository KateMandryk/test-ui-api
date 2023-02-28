package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Credentials {
   private String userName;
   private String password;

   public Credentials(String userName, String password) {
      this.userName = userName;
      this.password = password;
   }
}
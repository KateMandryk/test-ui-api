package utils;

import java.util.Random;
import java.util.UUID;

public class RandomCredentials {

    public String getLogin(){
        return  UUID.randomUUID().toString().replace("-","");
    }
    public String getPassword(){

        String rndUpChar = "ABCDEFGHIJKLFGHJMNNVCXZ";
        String rndLowChar="hfkyuuitjnaergwktsertyu";
        String rndNumber="12345678904356567789801";
        String rndDigit="@#$%$^&*()_<>?:)(+_)*#@";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 12) {
            int index = (int) (rnd.nextFloat() * rndUpChar.length());
            salt.append(rndUpChar.charAt(index));
            salt.append(rndLowChar.charAt(index));
            salt.append(rndDigit.charAt(index));
            salt.append(rndNumber.charAt(index));
        }
        return salt.toString();
}}

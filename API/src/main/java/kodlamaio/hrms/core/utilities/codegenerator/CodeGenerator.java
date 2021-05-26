package kodlamaio.hrms.core.utilities.codegenerator;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeGenerator{
    private final static String VALUE = "ABCDEFGHIKLMNOPQRSTUVWXYZ" +
            "abcdefghiklmnopqrstuvwxyz" +
            "0123456789";

    public static String generateCode() {
        Random random = new Random();
        char[] characters = new char[25];

        for(int i = 0; i < characters.length; i++) {
            characters[i] = VALUE.charAt(random.nextInt(VALUE.length()));
        }
        return String.valueOf(characters);
    }
}

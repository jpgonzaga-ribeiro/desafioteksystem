package application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
@SpringBootApplication

public class ListApplication {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        SpringApplication.run(ListApplication.class, args);
    }
}
package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import org.springframework.context.annotation.Bean;
import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import java.time.LocalTime;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Daytime daytime() {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.getHour() >= 6 && currentTime.getHour() <= 22) {
            return new Day();
        } else {
            return new Night();
        }
    }
}

package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Day implements Daytime {
    private String name = "day";

    @PostConstruct
    public void postConstruct() {
        System.out.println("Day bean has been created");
    }

    @Override
    public String getName() {
        return "day";
    }
}


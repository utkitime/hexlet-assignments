package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Night implements Daytime {
    private String name = "night";

    @PostConstruct
    public void postConstruct() {
        System.out.println("Night bean has been created");
    }

    @Override
    public String getName() {
        return "night";
    }
}

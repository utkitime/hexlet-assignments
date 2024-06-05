package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import static jakarta.persistence.GenerationType.IDENTITY;
//import lombok.Getter;
//import lombok.Setter;

@Entity
@Table(name = "products")
//@Getter
//@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String title;
    private int price;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

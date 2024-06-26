package exercise.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withTitleContaining(params.getTitleCont())
                        .and(withPriceGreaterThan(params.getPriceGt()))
                        .and(withPriceLessThan(params.getPriceLt()))
                        .and(withRatingGreaterThan(params.getRatingGt()))
                );
    }

    public static Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, cb) -> categoryId == null ? cb.conjunction() : cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> withTitleContaining(String titleCont) {
        return (root, query, cb) -> titleCont == null ? cb.conjunction() : cb.like(cb.lower(root.get("title")), "%" + titleCont.toLowerCase() + "%");
    }

    public static Specification<Product> withPriceLessThan(Integer priceLt) {
        return (root, query, cb) -> priceLt == null ? cb.conjunction() : cb.lessThan(root.get("price"), priceLt);
    }

    public static Specification<Product> withPriceGreaterThan(Integer priceGt) {
        return (root, query, cb) -> priceGt == null ? cb.conjunction() : cb.greaterThan(root.get("price"), priceGt);
    }

    public static Specification<Product> withRatingGreaterThan(Double ratingGt) {
        return (root, query, cb) -> ratingGt == null ? cb.conjunction() : cb.greaterThan(root.get("rating"), ratingGt);
    }

}

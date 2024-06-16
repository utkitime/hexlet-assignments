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
    public static Specification<Product> filterByParams(ProductParamsDTO params) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.getTitleCont() != null && !params.getTitleCont().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + params.getTitleCont().toLowerCase() + "%"));
            }

            if (params.getCategoryId() != null) {
                predicates.add(cb.equal(root.get("category").get("id"), params.getCategoryId()));
            }

            if (params.getPriceLt() != null) {
                predicates.add(cb.lessThan(root.get("price"), params.getPriceLt()));
            }

            if (params.getPriceGt() != null) {
                predicates.add(cb.greaterThan(root.get("price"), params.getPriceGt()));
            }

            if (params.getRatingGt() != null) {
                predicates.add(cb.greaterThan(root.get("rating"), params.getRatingGt()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

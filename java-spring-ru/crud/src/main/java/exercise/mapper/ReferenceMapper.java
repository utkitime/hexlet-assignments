package exercise.mapper;

import exercise.model.Category;
import exercise.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class ReferenceMapper {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    public CategoryRepository categoryRepository;

    public Category toCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}

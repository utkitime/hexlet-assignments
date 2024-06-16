package exercise.mapper;

import exercise.model.Category;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class ReferenceMapper {

    @Autowired
    public CategoryRepository categoryRepository;

    public Category toCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}

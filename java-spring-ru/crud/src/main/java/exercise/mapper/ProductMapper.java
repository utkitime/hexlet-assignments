package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mappings;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {
//    @Mappings({
//            @Mapping(source = "category.id", target = "categoryId"),
//            @Mapping(source = "category.name", target = "category.name")
//    })

    public abstract ProductDTO toDto(Product product);

    //    @Mappings({
//            @Mapping(source = "category.id", target = "category.id"),
//            @Mapping(source = "category.name", target = "category.name")
//    })
    @Mapping(source = "categoryId", target = "category")
    public abstract Product toEntity(ProductDTO productDTO);
}
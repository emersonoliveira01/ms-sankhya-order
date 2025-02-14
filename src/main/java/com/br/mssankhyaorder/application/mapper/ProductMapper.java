package com.br.mssankhyaorder.application.mapper;

import com.br.mssankhyaorder.application.dto.ProductRequest;
import com.br.mssankhyaorder.application.dto.ProductResponse;
import com.br.mssankhyaorder.domain.model.Product;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductMapper {

    Product toEntity(ProductRequest productRequest);

    @Mapping(source = "barcode", target = "barcode")
    ProductResponse toResponse(Product product);
}

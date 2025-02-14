package com.br.mssankhyaorder.application.mapper;

import com.br.mssankhyaorder.application.dto.OrderRequest;
import com.br.mssankhyaorder.application.dto.OrderResponse;
import com.br.mssankhyaorder.application.dto.ProductResponse;
import com.br.mssankhyaorder.domain.model.Order;
import com.br.mssankhyaorder.domain.model.OrderProduct;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface OrderMapper {

    Order toEntity(OrderRequest orderRequest);
    OrderResponse toResponse(Order order);

}

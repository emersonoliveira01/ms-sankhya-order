package com.br.mssankhyaorder.application.mapper;

import com.br.mssankhyaorder.application.dto.CustomerRequest;
import com.br.mssankhyaorder.application.dto.CustomerResponse;
import com.br.mssankhyaorder.domain.model.Customer;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CustomerMapper {

    Customer toEntity(CustomerRequest customerRequest);

    CustomerResponse toResponse(Customer customer);
}

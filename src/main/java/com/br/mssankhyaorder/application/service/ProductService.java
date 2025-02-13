package com.br.mssankhyaorder.application.service;

import com.br.mssankhyaorder.application.dto.ProductRequest;
import com.br.mssankhyaorder.application.dto.ProductResponse;
import com.br.mssankhyaorder.application.mapper.ProductMapper;
import com.br.mssankhyaorder.domain.model.Product;
import com.br.mssankhyaorder.domain.repository.ProductRepository;
import com.br.mssankhyaorder.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductResponse registerProduct(ProductRequest productRequest) {
        logger.info("Iniciando o cadastro do produto: {}", productRequest.getName());

        if (productRequest.getPrice() < 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "INVALID_PRICE", "O preço não pode ser negativo.");
        }

        Product product = productMapper.toEntity(productRequest);
        Product savedProduct = productRepository.save(product);
        logger.info("Produto cadastrado com sucesso: {}", savedProduct.getName());
        return productMapper.toResponse(savedProduct);
    }

    public List<ProductResponse> listProducts() {
        logger.debug("Listando todos os produtos");
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }
}

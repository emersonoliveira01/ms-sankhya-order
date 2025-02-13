package com.br.mssankhyaorder.service;

import com.br.mssankhyaorder.application.dto.ProductRequest;
import com.br.mssankhyaorder.application.dto.ProductResponse;
import com.br.mssankhyaorder.application.mapper.ProductMapper;
import com.br.mssankhyaorder.application.service.ProductService;
import com.br.mssankhyaorder.domain.model.Product;
import com.br.mssankhyaorder.domain.repository.ProductRepository;
import com.br.mssankhyaorder.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    private ProductRequest productRequest;
    private Product product;
    private ProductResponse productResponse;


    @BeforeEach
    void setUp() {

        productRequest = new ProductRequest();
        productRequest.setName("Product A");
        productRequest.setPrice(10.0);
        productRequest.setStock(100);
        productRequest.setDescription("Description A");
        productRequest.setNcm("1234.56.78");
        productRequest.setCst("000");
        productRequest.setUnitOfMeasure("UN");
        productRequest.setCfop("5101");

        product = new Product();
        product.setId(1L);
        product.setName("Product A");
        product.setPrice(10.0);
        product.setStock(100);
        product.setDescription("Description A");
        product.setNcm("1234.56.78");
        product.setCst("000");
        product.setUnitOfMeasure("UN");
        product.setCfop("5101");

        productResponse = new ProductResponse();
        productResponse.setId(1L);
        productResponse.setName("Product A");
        productResponse.setBarcode("BARCODE-1");
        productResponse.setPrice(10.0);
        productResponse.setStock(100);
        productResponse.setDescription("Description A");
        productResponse.setNcm("1234.56.78");
        productResponse.setCst("000");
        productResponse.setUnitOfMeasure("UN");
        productResponse.setCfop("5101");
    }

    @Test
    void registerProduct_ShouldReturnProductResponse_WhenProductIsValid() {
        when(productMapper.toEntity(any(ProductRequest.class))).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toResponse(any(Product.class))).thenReturn(productResponse);

        var response = productService.registerProduct(productRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Product A", response.getName());
        Assertions.assertEquals(1L, response.getId());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void registerProduct_ShouldThrowCustomException_WhenPriceIsNegative() {
        productRequest.setPrice(-5.0);

        var exception = assertThrows(CustomException.class, () -> {
            productService.registerProduct(productRequest);
        });

        Assertions.assertEquals("O preço não pode ser negativo.", exception.getMessage());
        Assertions.assertEquals("INVALID_PRICE", exception.getErrorCode());
    }

    @Test
    void listProducts_ShouldReturnListOfProductResponse() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productMapper.toResponse(any(Product.class))).thenReturn(productResponse);

        var responseList = productService.listProducts();

        Assertions.assertNotNull(responseList);
        Assertions.assertEquals(1, responseList.size());
        Assertions.assertEquals("Product A", responseList.get(0).getName());
    }

}
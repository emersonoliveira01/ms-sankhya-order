package com.br.mssankhyaorder.controller;

import com.br.mssankhyaorder.application.dto.ProductRequest;
import com.br.mssankhyaorder.application.dto.ProductResponse;
import com.br.mssankhyaorder.application.service.ProductService;
import com.br.mssankhyaorder.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private ProductRequest productRequest;
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
    void registerProduct_ShouldReturnProductResponse_WhenSuccessful() {
        when(productService.registerProduct(any(ProductRequest.class))).thenReturn(productResponse);

        var response = productController.registerProduct(productRequest);

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Product A", response.getBody().getName());
        Assertions.assertEquals(1L, response.getBody().getId());
    }

    @Test
    void registerProduct_ShouldThrowException_WhenProductIsInvalid() {
        when(productService.registerProduct(any(ProductRequest.class)))
                .thenThrow(new CustomException(HttpStatus.BAD_REQUEST, "INVALID_PRODUCT", "Produto inválido"));

        var exception = assertThrows(CustomException.class, () -> {
            productController.registerProduct(productRequest);
        });

        Assertions.assertEquals("Produto inválido", exception.getMessage());
        Assertions.assertEquals("INVALID_PRODUCT", exception.getErrorCode());
    }

    @Test
    void listProducts_ShouldReturnListOfProductResponse() {
        when(productService.listProducts()).thenReturn(List.of(productResponse));

        var responseList = productController.listProducts();

        Assertions.assertNotNull(responseList);
        Assertions.assertEquals(1, responseList.size());
        Assertions.assertEquals("Product A", responseList.get(0).getName());
    }

}
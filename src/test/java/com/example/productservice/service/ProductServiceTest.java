package com.example.productservice.service;

import com.example.productservice.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(restTemplate);
    }

    @Test
    void testGetAllProducts() {
        // Mock response from restTemplate
        ProductsResponse mockResponse = new ProductsResponse();
        List<Product> mockProducts = Collections.singletonList(new Product());
        mockResponse.setProducts(mockProducts);
        when(restTemplate.getForObject(anyString(), eq(ProductsResponse.class)))
                .thenReturn(mockResponse);

        // Call the service method
        List<Product> products = productService.getAllProducts();

        // Verify the result
        assertEquals(mockProducts, products);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(ProductsResponse.class));
    }

    // Add more tests for other methods like getProductById, searchProductsByName, etc.
}
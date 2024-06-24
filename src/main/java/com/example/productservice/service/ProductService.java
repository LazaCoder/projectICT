package com.example.productservice.service;

import com.example.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> getAllProducts() {
        String url = "https://dummyjson.com/products";
        ProductsResponse response = restTemplate.getForObject(url, ProductsResponse.class);
        return response != null ? response.getProducts() : Collections.emptyList();
    }

    public Product getProductById(Long id) {
        String url = "https://dummyjson.com/products/" + id;
        return restTemplate.getForObject(url, Product.class);
    }

    public List<Product> searchProductsByName(String name) {
        String url = "https://dummyjson.com/products/search?q=" + name;
        ProductsResponse response = restTemplate.getForObject(url, ProductsResponse.class);
        return response != null ? response.getProducts() : Collections.emptyList();
    }

    public List<Product> getProductsByCategory(String category) {
        String url = "https://dummyjson.com/products/category/"+category;
        ProductsResponse response = restTemplate.getForObject(url, ProductsResponse.class);
        return response != null ? response.getProducts() : List.of();
    }


    public List<Product> getProductsByPrice(double price) {
        String url = "https://dummyjson.com/products";
        ProductsResponse response = restTemplate.getForObject(url, ProductsResponse.class);
        return response != null ? response.getProducts().stream().filter(p->price==p.getPrice()).toList() : Collections.emptyList();
    }


}



 class ProductsResponse {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

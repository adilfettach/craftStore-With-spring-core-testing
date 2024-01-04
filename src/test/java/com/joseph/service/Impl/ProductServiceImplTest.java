package com.joseph.service.Impl;

import com.joseph.config.PersistenceJPAConfig;
import com.joseph.entity.Product;
import com.joseph.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class})
@Transactional
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    private Product productTest;

    @BeforeEach
    void setUp() {
        productTest = new Product();
        productTest.setNameproduct("Test Product");
        productTest.setPriceproduct(1200.0);
        productTest.setStockproduct(Long.valueOf(3));
        productService.saveProduct(productTest);
    }
    @AfterEach
    void tearDown() {
        productService.deleteProduct(productTest.getIdproduct());
    }

    @Test
    @DisplayName("Test get product by ID")
    void testGetProduct() {
        Product retrievedProduct = productService.getProduct(productTest.getIdproduct());
        assertNotNull(retrievedProduct);
        assertEquals(productTest.getNameproduct(), retrievedProduct.getNameproduct());
        assertEquals(productTest.getPriceproduct(), retrievedProduct.getPriceproduct());
        assertEquals(productTest.getStockproduct(), retrievedProduct.getStockproduct());
    }

    @Test
    @DisplayName("Test get products")
    void testGetProducts() {
        List<Product> productList = productService.getProducts();
        assertNotNull(productList);
        assertFalse(productList.isEmpty());
    }

    @Test
    @DisplayName("Test get product by name")
    void testGetProductByName() {
        Product retrievedProduct = productService.geProductsByName(productTest.getNameproduct());
        assertNotNull(retrievedProduct);
        assertEquals(productTest.getNameproduct(), retrievedProduct.getNameproduct());
        assertEquals(productTest.getPriceproduct(), retrievedProduct.getPriceproduct());
        assertEquals(productTest.getStockproduct(), retrievedProduct.getStockproduct());
    }
}
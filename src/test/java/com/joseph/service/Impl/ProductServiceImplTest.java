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
    @Autowired
    private ProductRepository productRepository;

    private Product productTest;

     @BeforeEach
     void setUp() {
         productRepository.deleteAll();
         productTest = new Product();
         productTest.setNameproduct("Test Product");
         productTest.setPriceproduct(1200.0);
         productTest.setStockproduct(Long.valueOf(3));
         productService.saveProduct(productTest);
     }
     @AfterEach
     void tearDown() {
         productRepository.deleteAll();
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
     @DisplayName("Test delete product")
     void testDeleteProducts() {
         productTest = productRepository.findAll().stream().findFirst().orElse(null);
         productService.deleteProduct(productTest.getIdproduct());
         assertEquals(0,productRepository.findAll().size());
     }

     @Test
     @DisplayName("Test get product by name")
     void testGetProductByName() {
         Product retrievedProduct = productService.geProductsByName(productTest.getNameproduct());
         assertNotNull(retrievedProduct);
         assertEquals(productTest.getNameproduct() + "test", retrievedProduct.getNameproduct());
         assertEquals(productTest.getPriceproduct(), retrievedProduct.getPriceproduct());
         assertEquals(productTest.getStockproduct(), retrievedProduct.getStockproduct());
     }
}

package com.cts.milestone.product.service;

import com.cts.milestone.product.entity.ProductEntity;
import com.cts.milestone.product.exceptions.RecordNotFoundException;
import com.cts.milestone.product.model.Product;
import com.cts.milestone.product.model.ProductUpdate;
import com.cts.milestone.product.repository.ProductRepository;
import com.cts.milestone.product.util.TestDataUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductTranslator productTranslator;

     private Product product;
     private ProductEntity productEntity;
     private ProductUpdate productUpdate;

    @Before
    public void setUp(){
     product= TestDataUtil.createProduct();
     productEntity=TestDataUtil.createProductEntity();
     productUpdate= TestDataUtil.createProductUpdate();
    }

    //Get
    @Test(expected = RuntimeException.class)
    public void getProductRepoException() {
        when(productRepository.findById(anyString())).thenThrow(new RuntimeException(""));
        productService.getProduct("PRODUCT774AAB4144A32B98111C06B354");
    }

    @Test(expected = RecordNotFoundException.class)
    public void getProductNotFoundException() {
        Optional<ProductEntity> optionalOrderEntity = Optional.empty();
        when(productRepository.findById(anyString())).thenReturn(optionalOrderEntity);
        productService.getProduct("PRODUCT774AAB4144A32B98111C06B354");
    }
    @Test
    public void getProductSuccess(){
        when(productRepository.findById(anyString())).thenReturn(Optional.of(productEntity));
        when(productTranslator.entityToModel(any())).thenReturn(product);
        Product product=productService.getProduct("PRODUCT774AAB4144A32B98111C06B354");
        assertTrue(product!=null);
    }
    //Create
    @Test(expected = RuntimeException.class)
    public void createProductTranslatorException() {
        when(productTranslator.modelToEntity(any())).thenThrow(new RuntimeException(""));
        productService.createProduct(product);
    }

    @Test(expected = RuntimeException.class)
    public void createProductRepoException() {
        when(productTranslator.modelToEntity(any())).thenReturn(productEntity);
        when(productRepository.save(productEntity)).thenThrow(new RuntimeException(""));
        productService.createProduct(product);
    }

    @Test
    public void createProductSuccess() {
        when(productTranslator.modelToEntity(any())).thenReturn(productEntity);
        when(productRepository.save(any())).thenReturn(productEntity);
        String productId= productService.createProduct(product);
        assertTrue(productId!=null);
        assertTrue(productId.equals(productEntity.getProductId()));
    }

    //Update
    @Test(expected = RuntimeException.class)
    public void updateProductRepoException(){
        when(productRepository.findById(anyString())).thenThrow(new RuntimeException());
        productService.updateProduct("PRODUCT774AAB4144A32B98111C06B354",productUpdate);
    }

    @Test(expected = RecordNotFoundException.class)
    public void updateProductRecordNotFoundException(){
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());
        productService.updateProduct("PRODUCT774AAB4144A32B98111C06B354",productUpdate);
    }

    @Test(expected = RuntimeException.class)
    public void updateProductTranslatorException(){
        when(productRepository.findById(anyString())).thenReturn(Optional.of(productEntity));
        when(productTranslator.mergeEntity(any(),any())).thenThrow(new RuntimeException());
        productService.updateProduct("PRODUCT774AAB4144A32B98111C06B354",productUpdate);
    }

    @Test
    public void updateProductSuccess(){
        when(productRepository.findById(anyString())).thenReturn(Optional.of(productEntity));
        when(productTranslator.mergeEntity(any(),any())).thenReturn(productEntity);
        when(productRepository.save(productEntity)).thenReturn(productEntity);
        String productId= productService.updateProduct("PRODUCT774AAB4144A32B98111C06B354",productUpdate);
        assertTrue(productId!=null);
    }


    //Delete
    @Test(expected = RuntimeException.class)
    public void deleteProductRepoException() {
        when(productRepository.findById(anyString())).thenThrow(new RuntimeException(""));
        productService.deleteProduct("PRODUCT774AAB4144A32B98111C06B354");
    }

    @Test(expected = RecordNotFoundException.class)
    public void deleteProductRecordNotFoundException() {
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());
        productService.deleteProduct("PRODUCT774AAB4144A32B98111C06B354");
    }

    @Test
    public void deleteProductFoundSuccess(){
        when(productRepository.findById(anyString())).thenReturn(Optional.of(productEntity));
        doNothing().when(productRepository).deleteById(anyString());
        productService.deleteProduct("PRODUCT774AAB4144A32B98111C06B354");
        verify(productRepository).findById(anyString());
        verify(productRepository).deleteById(anyString());
    }
}

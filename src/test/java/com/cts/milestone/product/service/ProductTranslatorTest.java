package com.cts.milestone.product.service;

import com.cts.milestone.product.entity.ProductEntity;
import com.cts.milestone.product.model.Product;
import com.cts.milestone.product.model.ProductUpdate;
import com.cts.milestone.product.util.TestDataUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ProductTranslatorTest {

    @InjectMocks
    private ProductTranslator productTranslator;

    private ProductEntity productEntity;
    private Product product;
    private ProductUpdate productUpdate;


    @Before
    public void setUp(){
        product= TestDataUtil.createProduct();
        productEntity=TestDataUtil.createProductEntity();
        productUpdate= TestDataUtil.createProductUpdate();
    }
    @Test(expected = RuntimeException.class)
    public void testModelToEntityRuntime(){
        productTranslator.modelToEntity(null);
    }

    @Test
    public void testModelToEntitySuccess(){
        ProductEntity productEntityResult = productTranslator.modelToEntity(product);
        assertNotNull(productEntityResult);
        assertEquals(product.getProductName(),productEntityResult.getProductName());
        assertEquals(product.getPrice(),productEntityResult.getPrice());
    }

    @Test(expected = RuntimeException.class)
    public void testEntityToModelRuntime(){
        productTranslator.entityToModel(null);
    }

    @Test
    public void testEntityToModelSuccess(){
        Product productResult = productTranslator.entityToModel(productEntity);
        assertNotNull(productResult);
        assertEquals(productEntity.getProductName(),productResult.getProductName());
        assertEquals(productEntity.getProductId(),productResult.getProductId());
        assertEquals(productEntity.getPrice(),productResult.getPrice());
    }

      @Test(expected = RuntimeException.class)
    public void testMergeEntityRuntime(){
        productTranslator.mergeEntity(productEntity,null);
    }

    @Test(expected = RuntimeException.class)
    public void testMergeEntityRuntimeEntityCheck(){
        productTranslator.mergeEntity(null,productUpdate);
    }

    @Test
    public void testMergeEntitySuccess(){
        ProductEntity productEntityResult= productTranslator.mergeEntity(productEntity,productUpdate);
        assertNotNull(productEntityResult);
        assertEquals(productUpdate.getProductName(),productEntityResult.getProductName());
        assertEquals(productUpdate.getPrice(),productEntityResult.getPrice());
    }
}

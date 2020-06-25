package com.cts.milestone.product.resource;

import com.cts.milestone.product.exceptions.RecordNotFoundException;
import com.cts.milestone.product.model.Product;
import com.cts.milestone.product.service.ProductService;


import com.cts.milestone.product.util.TestDataUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class ProductResourceTest {

    @InjectMocks
    private ProductResource productResource;

    @InjectMocks
    private ProductExceptionHandler productExceptionHandler;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    private Product product;

    private final String PRODUCT_CREATE ="{\n" +
            "  \"price\": 800.00,\n" +
            "   \"productName\": \"Books\"\n" +
            "}";

    private final  String PRODUCT_UPDATE ="{\n" +
            "  \"price\": 500.00,\n" +
            "  \"productName\": \"vehicle\"\n" +
            "}";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productResource)
                .setControllerAdvice(productExceptionHandler)
                .build();
        product = TestDataUtil.createProduct();
    }

    @Test
    public void testGetProductRecordNotFound() throws Exception {
        when(productService.getProduct(anyString())).thenThrow(new RecordNotFoundException(""));
        this.mockMvc.perform(get("/v1/products/PRODUCT774AAB4144A32B98111C06B354"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetProductSuccess() throws Exception {
        when(productService.getProduct(anyString())).thenReturn(product);
        this.mockMvc.perform(get("/v1/products/PRODUCT774AAB4144A32B98111C06B354"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateProductRecordNotFound() throws Exception {
        when(productService.createProduct(any())).thenThrow(new RecordNotFoundException(""));
        this.mockMvc.perform(post("/v1/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(PRODUCT_CREATE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateProductSuccess() throws Exception {
        when(productService.createProduct(any())).thenReturn("PRODUCT774AAB4144A32B98111C06B354");
        this.mockMvc.perform(post("/v1/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(PRODUCT_CREATE))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteProductRecordNotFound() throws Exception {
        doThrow(new RecordNotFoundException("")).when(productService).deleteProduct(anyString());
        this.mockMvc.perform(delete("/v1/products/PRODUCT774AAB4144A32B98111C06B354"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteProductSuccess() throws Exception {
        doNothing().when(productService).deleteProduct(anyString());
        this.mockMvc.perform(delete("/v1/products/PRODUCT774AAB4144A32B98111C06B354"))
                .andExpect(status().isNoContent());
    }
}

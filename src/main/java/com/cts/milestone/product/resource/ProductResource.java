package com.cts.milestone.product.resource;


import com.cts.milestone.product.model.Product;
import com.cts.milestone.product.model.ProductUpdate;
import com.cts.milestone.product.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiOperation("Create,Get,Update,Delete Product - Version1")
@RestController
@RequestMapping("/v1/products")
public class ProductResource {

    private static final Logger LOG = LoggerFactory.getLogger(ProductResource.class);

    @Autowired
    private ProductService productService;

    
    @ApiOperation("Creates a product from the system.")
    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@ApiParam(value = "It is for creating Product")
                                                    @RequestBody Product product){
        String productId = productService.createProduct(product);
        LOG.info("Product created successfully, productId={}",productId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productId);
    }
    @ApiOperation("Fetches a product from the system. 404 if the product's identifier is not found.")
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@ApiParam(value = "Id for the Product to Retrieve")
                                                  @PathVariable String productId){
        Product product = productService.getProduct(productId);
        LOG.info("Product fetched successfully, productId={}",productId);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }
    @ApiOperation("Updates a product from the system. 404 if the product's identifier is not found.")
    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@ApiParam(value = "Id for the Product to Update")
                                 @PathVariable String productId, @RequestBody ProductUpdate productUpdate){
        String eTag = productService.updateProduct(productId, productUpdate);
        LOG.info("Product Updated successfully, productId={}",productId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .eTag(eTag)
                .build();
    }
    @ApiOperation("Deletes a product from the system. 404 if the product's identifier is not found.")
    @DeleteMapping("/{productId}")
    public  ResponseEntity<Void> deleteProduct(@ApiParam(value = "Id for the Product to Delete")
                                            @PathVariable String productId){
        productService.deleteProduct(productId);
        LOG.info("Product deleted successfully, productId={}",productId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}

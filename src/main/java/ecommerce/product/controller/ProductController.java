package ecommerce.product.controller;

import ecommerce.product.Service.ProductService;
import ecommerce.product.dtos.GetProductDto;
import ecommerce.product.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductDto> getProductById(@PathVariable("id") Long id) throws Exception{
        return new ResponseEntity(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<GetProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        System.out.println(product.getCategory());
        System.out.println(product.getTitle());
        System.out.println(product.getPrice());
        return new ResponseEntity<>("Product created.", HttpStatus.CREATED);
    }
}

package ecommerce.product.controller;

import ecommerce.product.Service.ProductService;
import ecommerce.product.dtos.GetProductDto;
import ecommerce.product.exceptions.NotFoundException;
import ecommerce.product.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class ProductController {
    ProductService productService;
    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductDto> getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(productService.getProductUsingId(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<GetProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> create_product(@RequestBody Product product){
        System.out.println(product.getTitle());
        System.out.println(product.getPrice());
        System.out.println(product.getDescription());
        return new ResponseEntity<>("USER CREATED", HttpStatus.CREATED);
    }
}

package ecommerce.product.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Product{
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
}
package ecommerce.product.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class GetProductDto {
    private String title;
    private Double price;
    private String description;
}

package ecommerce.product.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@DiscriminatorValue("CUSTOMER")
@NoArgsConstructor
@SuperBuilder
public class Customer extends User{
    private String shippingAddress;
    private Integer loyaltyPoints;
}

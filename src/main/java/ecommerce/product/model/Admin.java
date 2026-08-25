package ecommerce.product.model;

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
@DiscriminatorValue("ADMIN")
@NoArgsConstructor
@SuperBuilder
public class Admin extends User{
    private String department;
    private String accessLevel;
}

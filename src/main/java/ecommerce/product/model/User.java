package ecommerce.product.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "users")
public class User extends BaseEntity{
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
}

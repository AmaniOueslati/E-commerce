package com.amani.ecommerce.category;

import com.amani.ecommerce.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Category {
    @GeneratedValue
    @Id
    private Integer id ;
    private  String name ;
    private String description ;
    @OneToMany(mappedBy = "category" , cascade = CascadeType.REMOVE)
    private List<Product> products ;
}

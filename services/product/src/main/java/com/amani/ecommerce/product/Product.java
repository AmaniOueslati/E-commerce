package com.amani.ecommerce.product;


import com.amani.ecommerce.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Product {

@GeneratedValue
@Id
private Integer id ;
private  String name ;
private String description ;
private double availableQuantity ;
private BigDecimal price ;
@ManyToOne
@JoinColumn(name = "category_id")
private Category category ;
}

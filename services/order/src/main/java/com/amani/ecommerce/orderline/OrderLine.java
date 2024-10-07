package com.amani.ecommerce.orderline;


import com.amani.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id ;
    @ManyToOne
    @JoinColumn(name ="order_id")
    private Order order ;
    private Integer productId ;
    private double quantity ;


}

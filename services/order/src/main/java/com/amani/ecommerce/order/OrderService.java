package com.amani.ecommerce.order;
import com.amani.ecommerce.kafka.OrderConfirmation;
import com.amani.ecommerce.orderline.OrderLineRequest;
import com.amani.ecommerce.orderline.OrderLineService;
import com.amani.ecommerce.payment.PaymentClient;
import com.amani.ecommerce.payment.PaymentRequest;
import com.amani.ecommerce.product.ProductClient;

import com.amani.ecommerce.kafka.orderProducer;
import com.amani.ecommerce.kafka.OrderConfirmation;


import com.amani.ecommerce.customer.CustomerClient;
import com.amani.ecommerce.exception.BusinessException;
import com.amani.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final orderProducer orderProducer;
    private final PaymentClient paymentClient ;



    public Integer createOrder(OrderRequest request) {

        // check the customer--> OpenFein
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));


        //purchase the product --> product-ms(RestTemplate)
        var purchasedProducts = productClient.purchaseProducts(request.products());

        var order = this.repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        var paymentRequest = new PaymentRequest(
        request.amount(),
        request.paymentMethod(),
        order.getId(), order.getReference(),
         customer
        );

        paymentClient.requestOrderPayment(paymentRequest)






        //purchase the product --> product-ms

        //persist order

        //persist theorder lines

        //start payement process

        // send the order confirmation --> notification-ms (kafka)
      orderProducer.sendOrderConfirmation(
      new OrderConfirmation(request.reference(), request.amount(),request.paymentMethod() , customer , purchasedProducts)
      ); // we are sending an object so this pbject should be serialized in order to be able to be consumed later on so we need to congure the serializer and deserializer in config server
        return order.getId() ;
    }

    public List<OrderResponse> findAll() {
    return  repository.findAll()
            .stream()
            .map(mapper::fromOrder)
            .collect(Collectors.toList());
    }

    public OrderResponse finById(Integer orderId) {
      return repository.findById(orderId)
              .map(mapper::fromOrder)
              .orElseThrow(()-> new EntityNotFoundException(String.format("No order found with provided ID : %d ", orderId)));
    }
}

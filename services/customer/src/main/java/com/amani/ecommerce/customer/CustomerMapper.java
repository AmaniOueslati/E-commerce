package com.amani.ecommerce.customer;

public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
    if (request==null){
    return null ;
    }
    return Customer.builder().id(request.id())
            .fisrtname(request.fisrtname())
            .lastname(request.fisrtname())
            .email(request.email())
            .adress(request.adress())
            .build()
            ;
    }

    public CustomerResponse fromCustomer(Customer customer) {
    return new CustomerResponse(
            customer.getId(), customer.getFisrtname(), customer.getLastname(), customer.getEmail(), customer.getAdress()
    ) ;
    }
}

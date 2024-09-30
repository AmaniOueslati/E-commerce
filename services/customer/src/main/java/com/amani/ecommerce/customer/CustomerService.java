package com.amani.ecommerce.customer;

import com.amani.ecommerce.exception.CustomerNotFoundException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
 private final CustomerRepository  repository ;
 private final CustomerMapper mapper ;


public String createCustomer(CustomerRequest request){
var customer = repository.save(mapper.toCustomer(request));
return customer.getId() ;
}

    public void updateCustomer(CustomerRequest request) {
    var customer = repository.findById(request.id()).orElseThrow(()-> new CustomerNotFoundException(
        String.format("can not update customer :: No customer found with provided id :: %s !" , request.id())
    ));
    mergerCustomer(customer , request); // to not all
    repository.save(customer );

    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
     if (StringUtils.isNotBlank(request.fisrtname() )){
      customer.setFisrtname((request.fisrtname()));
     }
        if (StringUtils.isNotBlank(request.lastname() )){
            customer.setLastname((request.lastname()));
        }
        if (StringUtils.isNotBlank(request.email() )){
            customer.setFisrtname((request.email()));
        }
        if (request.adress() != null){
            customer.setAdress((request.adress()));
        }
    }

    public List<CustomerResponse> findAllCustomers() {
    return repository.findAll()
            .stream()
            .map(mapper :: fromCustomer)
            .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
    return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
    return  repository.findById(customerId).map(mapper :: fromCustomer).orElseThrow(()->new CustomerNotFoundException(String.format("No customer found with the provided Id :: %s " , customerId)));


    }

    public void deleteCustomerById(String customerId) {
      repository.deleteById(customerId);
    }
}

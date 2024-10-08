package com.amani.ecommerce.payement;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payement")
@RequiredArgsConstructor


public class PayementController {

private final PaymentService service ;

 @PostMapping
 public ResponseEntity<Integer> createPayement(@RequestBody @Valid PaymentRequest request ){
return ResponseEntity.ok(service.createPayement(request));


 }
}

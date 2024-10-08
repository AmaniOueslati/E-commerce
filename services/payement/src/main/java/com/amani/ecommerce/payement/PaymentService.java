package com.amani.ecommerce.payement;


import com.amani.ecommerce.notification.NotificationProducer;
import com.amani.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper ;
    private final NotificationProducer notificationProducer;
    public Integer createPayement(PaymentRequest request) {

    var payment = repository.save(mapper.toPayement(request));
    notificationProducer.sendNotification(
    new PaymentNotificationRequest(
    request.orderReference(),
            request.amount(),
            request.paymentMethod(),
            request.customer().firstname(),
            request.customer().lastname(),
            request.customer().email()

    )
    );

    //send our notification to payement
    return payment.getId();
    }
}

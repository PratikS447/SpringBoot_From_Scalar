package ecommerce.product.controller;

import ecommerce.product.Service.RazorpayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class RazorPayController {
    private final RazorpayService razorpayService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestParam int amount){
        try{
            String order = razorpayService.createOrder(amount);
            return ResponseEntity.ok(order);
        }catch (IllegalAccessException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body("Failed to create RazorPay order");
        }
    }

    public ResponseEntity<?> verifyPayment(@RequestParam String orderId,
                                           @RequestParam String paymentId,
                                           @RequestParam String signature){
        boolean verified = razorpayService.verifyPayement(orderId, paymentId, signature);
        if (verified){
            return ResponseEntity.ok("Payment verified successfully");
        }

        return ResponseEntity.badRequest().body("Payment verification failed");
    }
}

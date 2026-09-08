package ecommerce.product.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {
   private final RazorpayClient razorpayClient;
   private final String keyId;
   private final String keySecret;

   public RazorpayService(@Value("${razorpay.key.id}") String keyId, @Value("${razorpay.key.secret}") String keySecret)
           throws Exception {
       this.keyId = keyId;
       this.keySecret = keySecret;
       this.razorpayClient = new RazorpayClient(keyId, keySecret);
   }

   public String createOrder(int amount) throws Exception{
       if (amount <= 0){
           throw new IllegalArgumentException("Amount must be greater than 0");
       }

       JSONObject orderRequest = new JSONObject();

       orderRequest.put("amount", amount*100);

       orderRequest.put("currency", "INR");

       orderRequest.put(
               "receipt",
               "receipt_"+System.currentTimeMillis()
       );

       Order order = razorpayClient.orders.create(orderRequest);

       return order.toString();
   }

   public boolean verifyPayement(String orderId, String paymentId, String signature){
       try {
           String payload = orderId +"|" + paymentId;

           return Utils.verifySignature(payload, signature, keySecret);
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
   }

   public String getKeyId(){
       return keyId;
   }
}

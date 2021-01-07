package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.PaymentCardRequest;
import org.carly.api.rest.request.VerifyPaymentCardRequest;
import org.carly.api.rest.response.PaymentCardResponse;
import org.carly.api.rest.response.SuccessResponse;
import org.carly.core.paymentcardmanagement.service.PaymentCardFindService;
import org.carly.core.paymentcardmanagement.service.PaymentCardSaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/payment-card")
public class PaymentCardController {

    private static final String FIND_PAYMENT_CARD_BY_ID = "Find payment card by id";
    private static final String FIND_ALL_PAYMENT_CARDS_BY_USER_ID = "Find all payment cards by user id";
    private static final String ACTIVATE_PAYMENT_CARD = "Activate payment card";
    private static final String CREATE_PAYMENT_CARD = "Create payment card";
    private static final String UPDATE_PAYMENT_CARD = "Update payment card";
    private static final String DELETE_PAYMENT_CARD_BY_ID = "Delete payment card by id";
    private static final String VERIFY_PAYMENT_CARD_BY_CVV_CODE = "Verify payment card with cvv code";

    private final PaymentCardFindService paymentCardFindService;
    private final PaymentCardSaveService paymentCardSaveService;


    public PaymentCardController(PaymentCardFindService paymentCardFindService,
                                 PaymentCardSaveService paymentCardSaveService) {
        this.paymentCardFindService = paymentCardFindService;
        this.paymentCardSaveService = paymentCardSaveService;
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = FIND_PAYMENT_CARD_BY_ID)
    public PaymentCardResponse findPaymentCardById(@PathVariable("id") String id) {
        return paymentCardFindService.findPaymentCardById(new ObjectId(id));
    }

    @GetMapping("/all-payment-cards/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = FIND_ALL_PAYMENT_CARDS_BY_USER_ID)
    public Collection<PaymentCardResponse> findAllPaymentCardsByUserId(@PathVariable("userId") String userId) {
        return paymentCardFindService.findAllPaymentCardsByUserId(userId);
    }

    @PostMapping("/activate-card/{paymentCardId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = ACTIVATE_PAYMENT_CARD)
    public ResponseEntity<SuccessResponse> activatePaymentCard(@PathVariable("paymentCardId") String paymentCardId) {
        return paymentCardSaveService.activatePaymentCard(paymentCardId);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = CREATE_PAYMENT_CARD)
    public ResponseEntity<PaymentCardResponse> createPaymentCard(@RequestBody PaymentCardRequest paymentCardRequest) {
        return paymentCardSaveService.createPaymentCard(paymentCardRequest);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = UPDATE_PAYMENT_CARD)
    public ResponseEntity<PaymentCardResponse> updatePaymentCard(@RequestBody PaymentCardRequest paymentCardRequest) {
        return paymentCardSaveService.updatePaymentCard(paymentCardRequest);
    }

    @DeleteMapping("/delete/{paymentCardId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = DELETE_PAYMENT_CARD_BY_ID)
    public ResponseEntity<SuccessResponse> deletePaymentCard(@PathVariable("paymentCardId") String paymentCardId) {
        return paymentCardSaveService.deletePaymentCard(paymentCardId);
    }

    @PostMapping("/verify-payment-card")
    @PreAuthorize("hasAnyAuthority('CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = VERIFY_PAYMENT_CARD_BY_CVV_CODE)
    public ResponseEntity<?> verifyPaymentCard(@RequestBody VerifyPaymentCardRequest verifyPaymentCardRequest) {
        return paymentCardFindService.verifyPaymentCard(verifyPaymentCardRequest);
    }

}

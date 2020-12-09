package org.carly.core.paymentcardmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.response.PaymentCardResponse;
import org.carly.core.paymentcardmanagement.mapper.PaymentCardResponseMapper;
import org.carly.core.paymentcardmanagement.model.PaymentCard;
import org.carly.core.paymentcardmanagement.repository.PaymentCardMongoRepository;
import org.carly.core.paymentcardmanagement.repository.PaymentCardRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class PaymentCardFindService {

    private final PaymentCardResponseMapper paymentCardResponseMapper;
    private final PaymentCardRepository paymentCardRepository;
    private final PaymentCardMongoRepository paymentCardMongoRepository;

    public PaymentCardFindService(PaymentCardResponseMapper paymentCardResponseMapper,
                                  PaymentCardRepository paymentCardRepository,
                                  PaymentCardMongoRepository paymentCardMongoRepository) {
        this.paymentCardResponseMapper = paymentCardResponseMapper;
        this.paymentCardRepository = paymentCardRepository;
        this.paymentCardMongoRepository = paymentCardMongoRepository;
    }

    public PaymentCardResponse findPaymentCardById(ObjectId id) {
        PaymentCard paymentCard = paymentCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("PaymentCard with id: {} was found!", id);
        return paymentCardResponseMapper.simplifyRestObject(paymentCard);
    }

    public Collection<PaymentCardResponse> findAllPaymentCardsByUserId(String userId) {
        Collection<PaymentCard> allPaymentCards = paymentCardRepository.findAllByUserId(userId);
        log.info("Find ({}) - payment cards for user with id: {}", allPaymentCards.size(), userId);
        return allPaymentCards.stream().map(paymentCardResponseMapper::simplifyRestObject).collect(Collectors.toList());
    }

}

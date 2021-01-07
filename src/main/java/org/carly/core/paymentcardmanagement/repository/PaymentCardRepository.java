package org.carly.core.paymentcardmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.paymentcardmanagement.model.PaymentCard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.Optional;

public interface PaymentCardRepository extends MongoRepository<PaymentCard, ObjectId> {

    Collection<PaymentCard> findAllByUserId(String userId);

    Optional<PaymentCard> findPaymentCardByUserIdAndAndCardActive(String userId, boolean cardActive);

    Optional<Long> countByUserId(String userId);

}

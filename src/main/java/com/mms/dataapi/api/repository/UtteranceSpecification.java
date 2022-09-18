package com.mms.dataapi.api.repository;

import com.mms.dataapi.api.model.Dialog;
import com.mms.dataapi.api.model.Utterance;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.Optional;

public class UtteranceSpecification {
    public static Specification<Utterance> hasConsent(Optional<Long> customerId) {
        return (root, query, criteriaBuilder) -> {
            Join<Dialog, Utterance> messagesOfDialogs = root.join("dialog");
            Predicate consent = criteriaBuilder.isTrue(messagesOfDialogs.get("consent"));
            if (customerId.isPresent()) {
                // when customerId is given, return only utterance from this specific customer
                Predicate hasCustomerId = criteriaBuilder.equal(messagesOfDialogs.get("customerId"), customerId.get());
                consent = criteriaBuilder.and(consent, hasCustomerId);
            }
            return consent;
        };
    }

    public static Specification<Utterance> hasLanguage(Optional<String> lang) {
        return (root, query, criteriaBuilder) -> {
            if (lang.isPresent()) {
                return criteriaBuilder.equal(root.get("language"), lang.get());
            }
            return null;
        };
    }
}

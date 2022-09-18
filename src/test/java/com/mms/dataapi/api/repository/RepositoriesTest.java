package com.mms.dataapi.api.repository;

import com.mms.dataapi.api.model.Utterance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;
import static com.mms.dataapi.api.repository.UtteranceSpecification.hasConsent;
import static com.mms.dataapi.api.repository.UtteranceSpecification.hasLanguage;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RepositoriesTest {
    @Autowired
    private UtteranceRepository utteranceRepository;
    @Autowired
    private DialogRepository dialogRepository;

    @Test
    void testTotalQuantity() {
        Pageable paging = PageRequest.of(0, 10, Sort.by("creationDate").descending());
        Page<Utterance> pagedResult = utteranceRepository.findAll(paging);
        assertEquals(7, pagedResult.getContent().size());
    }

    @Test
    void testQuantityInFrench() {
        Pageable paging = PageRequest.of(0, 10, Sort.by("creationDate").descending());
        Page<Utterance> pagedResult = utteranceRepository.findAll(hasLanguage(Optional.of("FR")), paging);
        assertEquals(1, pagedResult.getContent().size());
    }

    @Test
    void testQuantityInGerman() {
        Pageable paging = PageRequest.of(0, 10, Sort.by("creationDate").descending());
        Page<Utterance> pagedResult = utteranceRepository.findAll(hasLanguage(Optional.of("DE")), paging);
        assertEquals(2, pagedResult.getContent().size());
    }

    @Test
    void testQuantityInEnglish() {
        Pageable paging = PageRequest.of(0, 10, Sort.by("creationDate").descending());
        Page<Utterance> pagedResult = utteranceRepository.findAll(hasLanguage(Optional.of("EN")), paging);
        assertEquals(4, pagedResult.getContent().size());
    }

    @Test
    void testQuantityInItalian() {
        Pageable paging = PageRequest.of(0, 10, Sort.by("creationDate").descending());
        Page<Utterance> pagedResult = utteranceRepository.findAll(hasLanguage(Optional.of("IT")), paging);
        assertEquals(0, pagedResult.getContent().size());
    }

    @Test
    void testQuantityWithConsent() {
        Pageable paging = PageRequest.of(0, 10, Sort.by("creationDate").descending());
        Page<Utterance> pagedResult = utteranceRepository.findAll(hasConsent(Optional.empty()), paging);
        assertEquals(5, pagedResult.getContent().size());
    }

    @Test
    void testQuantityWithConsentFromCustomer1() {
        Pageable paging = PageRequest.of(0, 10, Sort.by("creationDate").descending());
        Page<Utterance> pagedResult = utteranceRepository.findAll(hasConsent(Optional.of(1L)), paging);
        assertEquals(3, pagedResult.getContent().size());
    }
}
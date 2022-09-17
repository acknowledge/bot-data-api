package com.mms.dataapi.api.repository;

import com.mms.dataapi.api.model.Utterance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UtteranceRepository extends PagingAndSortingRepository<Utterance, Long> {
    Page<Utterance> findByLanguage(String language, Pageable pageable);
}

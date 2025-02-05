package com.mms.dataapi.api.repository;

import com.mms.dataapi.api.model.Utterance;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtteranceRepository extends PagingAndSortingRepository<Utterance, Long>, JpaSpecificationExecutor<Utterance> {
}

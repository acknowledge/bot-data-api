package com.mms.dataapi.api.repository;

import com.mms.dataapi.api.model.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DialogRepository extends JpaRepository<Dialog, Long> {
}

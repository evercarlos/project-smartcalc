package com.tec.smartcalc.repository;

import com.tec.smartcalc.domain.entity.CallHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallHistoryRepository extends JpaRepository<CallHistory, Long> {
}

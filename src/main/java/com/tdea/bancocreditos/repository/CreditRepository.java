package com.tdea.bancocreditos.repository;

import com.tdea.bancocreditos.entity.Credit;
import com.tdea.bancocreditos.entity.CreditType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByType(CreditType type);
}

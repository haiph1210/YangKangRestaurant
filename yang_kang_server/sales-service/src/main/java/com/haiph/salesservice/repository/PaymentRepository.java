package com.haiph.salesservice.repository;

import com.haiph.salesservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Query(value = "SELECT SUM(init_price) FROM payment WHERE status like '0' AND created_at = ?1"
            ,nativeQuery = true)
    double totalMoneyForCreatedAt(LocalDateTime createdAt);

    @Query(value = "SELECT COUNT(*) FROM yangkang_manager.payment WHERE status = '0' AND created_at = ?1",nativeQuery = true)
    int countByCreatedAt(LocalDateTime createAt);
}

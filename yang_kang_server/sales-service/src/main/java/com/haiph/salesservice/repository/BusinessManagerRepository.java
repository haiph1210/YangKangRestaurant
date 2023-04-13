package com.haiph.salesservice.repository;

import com.haiph.salesservice.entity.BusinessManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface BusinessManagerRepository extends JpaRepository<BusinessManager, LocalDate> {
    @Query(value = "SELECT * FROM business_manager WHERE `day` = ?1",nativeQuery = true)
    BusinessManager findByDay(LocalDate localDate) ;

    @Query(value =  "SELECT SUM(total_money) FROM business_manager WHERE mounth = ?1", nativeQuery = true)
    Double totalMoneyDay(Integer mounth);

    @Query(value = "SELECT SUM(total_payment) FROM business_manager WHERE mounth = ?1", nativeQuery = true)
    Integer countByMounth(Integer mounth);
}

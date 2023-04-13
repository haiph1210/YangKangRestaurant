package com.haiph.salesservice.repository;

import com.haiph.salesservice.Enum.Mouth;
import com.haiph.salesservice.entity.BusinessManagerMouth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessManagerMounthRepository extends JpaRepository<BusinessManagerMouth, Mouth> {
}

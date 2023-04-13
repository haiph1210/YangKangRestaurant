package com.haiph.restaurant_infoservice.repository.file;

import com.haiph.restaurant_infoservice.entity.manager.file.ExcelFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExcelFileRepository extends JpaRepository<ExcelFile,Integer> {
    @Query("select e from ExcelFile e where e.status = false ")
    List<ExcelFile> findAllByStatus();
}

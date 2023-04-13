package com.haiph.restaurant_infoservice.entity.manager.file;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "excel_file")
public class ExcelFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    private boolean status;
}

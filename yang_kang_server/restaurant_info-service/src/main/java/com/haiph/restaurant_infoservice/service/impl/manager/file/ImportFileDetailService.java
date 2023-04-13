package com.haiph.restaurant_infoservice.service.impl.manager.file;

import com.haiph.restaurant_infoservice.entity.manager.RestaurantDetail;
import com.haiph.restaurant_infoservice.entity.manager.RestaurantMasterial;
import com.haiph.restaurant_infoservice.entity.manager.file.ExcelFile;
import com.haiph.restaurant_infoservice.model.request.detail.RestaurantDetailCreate;
import com.haiph.restaurant_infoservice.model.response.ResponseBody;
import com.haiph.restaurant_infoservice.repository.file.ExcelFileRepository;
import com.haiph.restaurant_infoservice.repository.manager.RestaurantDetailRepository;
import com.haiph.restaurant_infoservice.repository.manager.RestaurantMasterialRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ImportFileDetailService {
    @Autowired
    private RestaurantMasterialRepository masterialRepository;
    @Autowired
    private RestaurantDetailRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Value("${dir-save-file}")
    private String dir;
    @Autowired
    private ExcelFileRepository excelFileRepository;

    public Integer findIdByRestaurantNameDetail(String name) {
        Optional<RestaurantMasterial> detail = masterialRepository.findByName(name);
        return detail.get().getId();
    }

    public List<RestaurantDetailCreate> readExcel(MultipartFile filePath) throws IOException {
        List<RestaurantDetailCreate> data = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(filePath.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        Row headerRow = rowIterator.next();
        List<String> headers = new ArrayList<>();
        for (Cell cell : headerRow) {
            headers.add(cell.getStringCellValue());
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            RestaurantDetailCreate create = new RestaurantDetailCreate();
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = row.getCell(i);
                String header = headers.get(i);
                if (header.equalsIgnoreCase("Name")) {
                    create.setName(cell.getStringCellValue());
                } else if (header.equalsIgnoreCase("Price")) {
                    create.setPrice(cell.getNumericCellValue());
                } else if (header.equalsIgnoreCase("Description")) {
                    create.setDescription(cell.getStringCellValue());
                } else if (header.equalsIgnoreCase("Amount")) {
                    create.setAmount((int) cell.getNumericCellValue());
                } else if (header.equalsIgnoreCase("InitPrice")) {
                    create.setInitPrice(cell.getNumericCellValue());
                } else if (header.equalsIgnoreCase("MasterialName")) {
                    create.setMasterialId(findIdByRestaurantNameDetail(cell.getStringCellValue()));
                }
            }
            data.add(create);
            RestaurantDetail masterial = mapper.map(create,RestaurantDetail.class);
            repository.save(masterial);
        }
        return data;
    }

    public String uploadFileToDir(MultipartFile file) throws IOException {
//        doc file excel lấy tên dự án và tháng để rename file
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

//        lay ten du an tren header
        Row row5 = sheet.getRow(4);
        String str = row5.getCell(0).getStringCellValue();
        String result = str.substring(7);

//        lay thang va nam
        Row row8 = sheet.getRow(7);
        Cell getMonthYear = row8.getCell(3);
        LocalDateTime getDate = getMonthYear.getLocalDateTimeCellValue();
        String dateFormat = getDate.toLocalDate().format(DateTimeFormatter.ofPattern("yyyyMM"));
        String newFileName = result + dateFormat;

//            copy and rename file
//        LocalDateTime getDate1 = LocalDateTime.now();
//        String dateFormat1 = getDate1.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy_MM_"));
        Random random = new Random();
        int x = random.nextInt(1000);
        String fileName = file.getOriginalFilename();
        File destFile = new File(dir + x + fileName);
//        File destFile = new File(dir + newFileName + ".xlsx");
        if(!destFile.exists()){
            System.out.println(destFile.exists());
            FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
            ExcelFile excelFile = new ExcelFile();
            excelFile.setPath(destFile.toString());
            excelFile.setStatus(false);
            excelFileRepository.save(excelFile);
        }
        return ResponseBody.OK;
    }

//    public List<RestaurantDetailCreate> readExcel(MultipartFile filePath) throws IOException {
//        List<RestaurantDetailCreate> data = new ArrayList<>();
//
//        Workbook workbook = new XSSFWorkbook(filePath.getInputStream());
//        Sheet sheet = workbook.getSheetAt(0);
//        Iterator<Row> rowIterator = sheet.iterator();
//        Row headerRow = rowIterator.next();
//        List<String> headers = new ArrayList<>();
//        for (Cell cell : headerRow) {
//            headers.add(cell.getStringCellValue());
//        }
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            RestaurantDetailCreate create = new RestaurantDetailCreate();
//            for (int i = 0; i < headers.size(); i++) {
//                Cell cell = row.getCell(i);
//                String header = headers.get(i);
//                if (header.equalsIgnoreCase("Name")) {
//                    create.setName(cell.getStringCellValue());
//                } else if (header.equalsIgnoreCase("Price")) {
//                    create.setPrice(cell.getNumericCellValue());
//                } else if (header.equalsIgnoreCase("Description")) {
//                    create.setDescription(cell.getStringCellValue());
//                } else if (header.equalsIgnoreCase("Amount")) {
//                    create.setAmount((int) cell.getNumericCellValue());
//                } else if (header.equalsIgnoreCase("InitPrice")) {
//                    create.setInitPrice(cell.getNumericCellValue());
//                } else if (header.equalsIgnoreCase("MasterialName")) {
//                    create.setMasterialId(findIdByRestaurantNameDetail(cell.getStringCellValue()));
//                }
//            }
//            data.add(create);
//            RestaurantDetail masterial = mapper.map(create,RestaurantDetail.class);
//            repository.save(masterial);
//        }
//    return data;
//    }



}

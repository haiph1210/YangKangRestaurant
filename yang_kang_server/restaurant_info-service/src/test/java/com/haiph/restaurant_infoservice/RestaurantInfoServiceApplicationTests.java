package com.haiph.restaurant_infoservice;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
class RestaurantInfoServiceApplicationTests {

    public class ExcelReader {
        public static void main(String[] args) throws IOException {
            String fileName = "F:/HUBT_Trainning/Excel_Manager/RestaurantDetail.xlsx";
            FileInputStream inputStream = new FileInputStream(fileName);
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.println(cell.toString());
                }
            }

            workbook.close();
            inputStream.close();
        }
    }

}

package com.haiph.restaurant_infoservice.controller.importFile;

import com.haiph.restaurant_infoservice.model.request.detail.RestaurantDetailCreate;
import com.haiph.restaurant_infoservice.model.response.ResponseBody;
import com.haiph.restaurant_infoservice.service.impl.manager.file.ImportFileDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class ImportFileDetailController {
    @Autowired
    private ImportFileDetailService service;

    @PostMapping("/import")
    public ResponseBody importFile(@RequestBody MultipartFile file) throws IOException {
        return new ResponseBody(ResponseBody._200,ResponseBody.OK,service.readExcel(file));
    }

    @GetMapping("/file-name/{name}")
    public ResponseBody getIdByName(@PathVariable String name) {
        return new ResponseBody(ResponseBody._200,ResponseBody.OK,service.findIdByRestaurantNameDetail( name));
    }

    @PostMapping("/upload-file")
    public ResponseBody uploadFile(@RequestBody MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            service.uploadFileToDir(file);
        }
        return new ResponseBody(ResponseBody._200,ResponseBody.OK);
    }
}

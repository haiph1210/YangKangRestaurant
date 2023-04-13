package com.haiph.menuservice.controller;

import com.haiph.menuservice.entity.Combo;
import com.haiph.menuservice.model.request.ComboCreate;
import com.haiph.menuservice.model.request.ComboUpdate;
import com.haiph.menuservice.model.response.ComboDTO;
import com.haiph.menuservice.service.impl.ComboServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/menu") //consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
public class ComboController {
    @Autowired
    private ComboServiceImpl service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/findCombo")
    public List<Combo> comboDTOS() {
        return service.findAll();
    }

    @GetMapping("/find-dto-combo")
    public Page<ComboDTO> comboDTOS(Pageable pageable) {
        Page<Combo> page = service.findAll(pageable);
        List<Combo> comboList = page.getContent();
        List<ComboDTO> comboDTOS = mapper.map(comboList, new TypeToken<List<ComboDTO>>() {
        }.getType());
        return new PageImpl<>(comboDTOS, pageable, page.getTotalElements());
    }

    @GetMapping("/findComboId/{id}")
    public ComboDTO findById(@PathVariable Integer id) {
        Combo combo = service.findById(id);
        return mapper.map(combo, ComboDTO.class);
    }

    @PostMapping("/create-combo")
    public void create(@RequestBody ComboCreate create) {
        service.create(create);
    }

    @PutMapping("/update-combo")
    public void update(@PathVariable Integer id, @RequestBody ComboUpdate update) {
        update.setId(id);
        service.update(update);
    }

    @DeleteMapping("/delete-combo/{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }
}

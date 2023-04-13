package com.haiph.orderservice.controller;

import com.haiph.orderservice.entity.Order;
import com.haiph.orderservice.model.request.OrderCreate;
import com.haiph.orderservice.model.response.order.OrderDTO;
import com.haiph.orderservice.service.impl.OrderServiceImpl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private ModelMapper mapper;
        /** Đã xóa thay vào đó là DTO*/
    @GetMapping("/findOrder")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/find-DTO-order")
    public Page<OrderDTO> findAllDTO(Pageable pageable) {
            Page<Order> page = orderService.findAllDTO(pageable);
            List<Order> list = page.getContent();
            List<OrderDTO> detailDTOS = mapper.map(list,new TypeToken<List<OrderDTO>>(){}.getType());
            return new PageImpl<>(detailDTOS,pageable,page.getTotalElements());
    }

    @GetMapping("/findOrderId/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping(value = "/create-order",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Order order) {
        orderService.create2(order);
    }

    /** create DTO lỗi nên sẽ dùng làm phương án 2*/
    @PostMapping(value = "/create-order2",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create2(@RequestBody OrderCreate create) {
        orderService.create(create);
    }
    @PutMapping(value = "/update-order")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody Order order) {
       order.setId(id);
        orderService.update(order);
       return ResponseEntity.ok("Update Successfully");
    }
}

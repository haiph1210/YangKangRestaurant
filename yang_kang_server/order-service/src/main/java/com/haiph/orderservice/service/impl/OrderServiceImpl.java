package com.haiph.orderservice.service.impl;


import com.haiph.orderservice.entity.Order;
import com.haiph.orderservice.model.request.OrderCreate;
import com.haiph.orderservice.model.request.OrderUpdate;
import com.haiph.orderservice.model.response.order.Combo;
import com.haiph.orderservice.model.response.order.Menu;
import com.haiph.orderservice.model.response.order.OrderDTO;
import com.haiph.orderservice.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${haiph.restemplate.url_findIdMenu}")
    private String urlIdMenu;
    @Value("${haiph.restemplate.url_findIdCombo}")
    private String urlIdCombo;

    /** Tính tiền*/
    private Double total(Double priceMenu, Integer amountMenu, Double priceCombo, Integer amountCombo, Integer discount) { // giá menu,giá combo,giảm giá
        Double priceDiscount = (Double) ((priceMenu * amountMenu) + (priceCombo * amountCombo) * discount) / 100;
        Double totalPrice = ((priceMenu * amountMenu) + (priceCombo * amountCombo)) - (priceDiscount);
        return totalPrice;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void create(OrderCreate create) {
        Order order = mapper.map(create, Order.class);
        orderRepository.save(order);
    }

    public void create2(Order order) {
        orderRepository.save(order);
    }


    public Page<Order> findAllDTO(Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);
        return page;
    }

    public OrderDTO findById(Integer id) {
        Order order = orderRepository.findById(id).orElse(null);
        List<Integer> menuIds = order.getMenuIds();
        List<Integer> comboIds = order.getComboIds();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
        OrderDTO orderDTO = new OrderDTO();
        List<Menu> menus = new ArrayList<>();
        for (Integer menuId : menuIds) {
            Menu menu = restTemplate.getForObject(urlIdMenu +"/"+menuId,Menu.class);
            menus.add(menu);
        }
        List<Combo> combos = new ArrayList<>();
        for (Integer comboId : comboIds) {
            Combo combo = restTemplate.getForObject(urlIdCombo+"/"+comboId,Combo.class);
            combos.add(combo);
        }

        orderDTO.setForm(order.getForm());
        orderDTO.setTotalAmount(totalAmount(menus,combos));
        orderDTO.setInitPrice(totalInitPrice(menus,combos));
        orderDTO.setCreatedAt(order.getCreatedAt());
        orderDTO.setHour(order.getHour());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setMenus(menus);
        orderDTO.setCombos(combos);
        return orderDTO;

    }
    private Double totalInitPrice(List<Menu> menus,List<Combo> combos){
        Double total = 0d;
        for (Menu menu : menus) {
            for (Combo combo : combos) {
                total += menu.getInitPrice()+ combo.getInitPrice();
            }
        }
    return total;
    }

    private Integer totalAmount(List<Menu> menus,List<Combo> combos){
        Integer total = 0;
        for (Menu menu : menus) {
            for (Combo combo : combos) {
                total += menu.getAmount()+ combo.getAmount();
            }
        }
        return total;
    }

    public void update(Order order) {
        orderRepository.save(order);
    }
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }


}

package com.haiph.salesservice.service.impl;

import com.haiph.salesservice.Enum.Status;
import com.haiph.salesservice.entity.Discount;
import com.haiph.salesservice.entity.Payment;
import com.haiph.salesservice.exception.CommonException;
import com.haiph.salesservice.exception.Response;
import com.haiph.salesservice.exception.UserNotFoundException;
import com.haiph.salesservice.model.request.PaymentCreate;
import com.haiph.salesservice.model.request.PaymentUpdate;
import com.haiph.salesservice.model.response.OrderDTO;
import com.haiph.salesservice.model.response.PaymentDTO;
import com.haiph.salesservice.model.response.PersonDTO;
import com.haiph.salesservice.model.response.RestaurantInfoDTO;
import com.haiph.salesservice.repository.DiscountRepository;
import com.haiph.salesservice.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class PaymentServiceImpl implements com.haiph.salesservice.service.PaymentService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${haiph.restemplate.url_findIdPerson}")
    private String urlIdPerson;
    @Value("${haiph.restemplate.url_findIdOrder}")
    private String urlIdOrder;
    @Value("${haiph.restemplate.url_findIdRestaurant}")
    private String urlIdRestaurant;
    @Autowired
    private DiscountRepository discountRepository;


    @Override
    public PaymentDTO findById(Integer id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        PaymentDTO paymentDTO = new PaymentDTO();
        try {
            if (payment != null) {
                OrderDTO orderDTO = findOrder(payment.getOrderId());
                PersonDTO personDTO = findPerson(payment.getPersonCode());
                RestaurantInfoDTO infoDTO = findRestaurantDTO(payment.getRestaurantInfoId());
                paymentDTO.setId(id);
                paymentDTO.setPerson(personDTO);
                paymentDTO.setCustomerPay(payment.getCustomerPay());
                paymentDTO.setInitPrice(orderDTO.getInitPrice());
                paymentDTO.setInfo(infoDTO);
                paymentDTO.setMoneyRemain(total(payment.getCustomerPay(),
                        orderDTO.getInitPrice(),
                        findPercentDiscount(payment.getDiscountCode())));
                paymentDTO.setCreatedAt(payment.getCreatedAt());
                paymentDTO.setDiscountCode(payment.getDiscountCode());
                paymentDTO.setPaymentBy(payment.getPaymentBy());
                paymentDTO.setStatus(payment.getStatus());
                paymentDTO.setOrder(orderDTO);

            }
        } catch (Exception exception) {
            throw new CommonException(Response.PARAM_INVALID,"Cannot find Id: " + id);
        }
        return paymentDTO;
    }

//    public void saveMoneyRemain() {
//        List<Payment> payments = paymentRepository.findAll();
//        for (Payment payment : payments) {
//            if (payment != null) {
//                PaymentDTO paymentDTO = findById(payment.getId());
//                if (payment.getId().equals(paymentDTO.getId()) || payment.getMoneyRemain().equals(null)) {
//                    paymentDTO.getMoneyRemain();
//                    Payment payment1 = mapper.map(paymentDTO, Payment.class);
//                    paymentRepository.save(payment1);
//                }
//            }
//        }
//    }

    @Override
    public void create(PaymentCreate create) {
        try {
            OrderDTO orderDTO = findOrder(create.getOrderId());
            Payment payment = mapper.map(create, Payment.class);
            payment.setPersonCode(create.getPersonCode());
            payment.setOrderId(create.getOrderId());
            payment.setRestaurantInfoId(create.getRestaurantInfoId());
            payment.setDiscountCode(create.getDiscountCode());
            payment.setCustomerPay(create.getCustomerPay());
            payment.setInitPrice(orderDTO.getInitPrice());
            payment.setMoneyRemain(total(create.getCustomerPay(),
                    orderDTO.getInitPrice(),
                    findPercentDiscount(create.getDiscountCode())));
            payment.setPaymentBy(create.getPaymentBy());
            payment.setCreatedAt(create.getCreatedAt());
            payment.setStatus(Status.WAIT_FOR_PAY);
            paymentRepository.save(payment);
        } catch (Exception exception) {
            throw new UserNotFoundException("Create Payment Fail,Error: " + exception.getMessage());
        }
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Page<PaymentDTO> findAllDTO(Pageable pageable) {
        try {
            Page<Payment> page = paymentRepository.findAll(pageable);
            List<Payment> payments = page.getContent();
            List<PaymentDTO> dtos = new ArrayList<>();
            for (Payment payment : payments) {
                PersonDTO personDTO = findPerson(payment.getPersonCode());
                OrderDTO orderDTO = findOrder(payment.getOrderId());
                RestaurantInfoDTO infoDTO = findRestaurantDTO(payment.getRestaurantInfoId());
                PaymentDTO paymentDTO = new PaymentDTO();
                paymentDTO.setId(payment.getId());
                paymentDTO.setInfo(infoDTO);
                paymentDTO.setPerson(personDTO);
                paymentDTO.setOrder(orderDTO);
                paymentDTO.setDiscountCode(payment.getDiscountCode());
                paymentDTO.setCustomerPay(payment.getCustomerPay());
                paymentDTO.setInitPrice(orderDTO.getInitPrice());
                paymentDTO.setMoneyRemain(total(payment.getCustomerPay(),
                        orderDTO.getInitPrice(),
                        findPercentDiscount(payment.getDiscountCode())));
                paymentDTO.setPaymentBy(payment.getPaymentBy());
                paymentDTO.setCreatedAt(payment.getCreatedAt());
                paymentDTO.setStatus(payment.getStatus());
                dtos.add(paymentDTO);

            }
            return new PageImpl<>(dtos, pageable, page.getTotalElements());
        } catch (Exception exception) {
            throw new UserNotFoundException("Cannot Find All Payment, Error: " + exception.getMessage());
        }
    }

    @Override
    public void update(PaymentUpdate update) {
        try {
            OrderDTO orderDTO = findOrder(update.getOrderId());
            Payment payment2 = paymentRepository.findById(update.getId()).orElse(null);
            if (payment2 != null) {
                Payment payment = mapper.map(update, Payment.class);
                payment.setPersonCode(update.getPersonCode());
                payment.setOrderId(update.getOrderId());
                payment.setRestaurantInfoId(update.getRestaurantInfoId());
                payment.setDiscountCode(update.getDiscountCode());
                payment.setCustomerPay(update.getCustomerPay());
                payment.setInitPrice(orderDTO.getInitPrice());
                payment.setMoneyRemain(total(update.getCustomerPay(),
                        orderDTO.getInitPrice(),
                        findPercentDiscount(update.getDiscountCode())));
                payment.setPaymentBy(update.getPaymentBy());
                payment.setCreatedAt(update.getCreatedAt());
                payment.setStatus(update.getStatus());
                paymentRepository.save(payment);
            }
        } catch (Exception exception) {
            throw new CommonException(Response.NOT_EXITS_REQUIRED,"Update Fail");
        }
    }

    private OrderDTO findOrder(Integer id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<OrderDTO> response = restTemplate.exchange(urlIdOrder + id, HttpMethod.GET, entity, OrderDTO.class);
            return response.getBody();
        } catch (Exception exception) {
            throw new UserNotFoundException("Fail, Cannot Find Order with ID: " + id);
        }
    }

    private PersonDTO findPerson(String personCode) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<PersonDTO> findPerson = restTemplate.exchange(urlIdPerson + personCode, HttpMethod.GET, entity, PersonDTO.class);
            return findPerson.getBody();
        } catch (Exception exception) {
            throw new UserNotFoundException("Fail, Cannot Find Person with personcode : " + personCode);
        }
    }

    private RestaurantInfoDTO findRestaurantDTO(Integer id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<RestaurantInfoDTO> response = restTemplate.exchange(urlIdRestaurant + id, HttpMethod.GET, entity, RestaurantInfoDTO.class);
            return response.getBody();
        } catch (Exception exception) {
            throw new UserNotFoundException("Fail, Cannot Find Restaurant with Id: " + id);
        }
    }

    private Discount findDiscountByCode(String discountCode) {
        try {
            return discountRepository.findByDiscountCode(discountCode);
        } catch (Exception exception) {
            throw new UserNotFoundException("Fail, Cannot Find DiscountCode: " + discountCode);
        }
    }

    private Integer findPercentDiscount(String discountCode) {
        try {
            Discount discount = findDiscountByCode(discountCode);
            return discount.getPercentDiscount().getPercentDiscount();
        } catch (Exception exception) {
            throw new UserNotFoundException("Cannot find Discount with discountCode: " +discountCode);
        }
    }

    private Double total(Double customer, Double initPrice, Integer discountCode) {
        try {
            Double percentDiscount = initPrice - ((initPrice * 1.0 * discountCode) / 100); // sau khi giảm giá theo %
            Double remain = customer - percentDiscount;  // tổng tiền sau khi trừ giảm giá
            return remain;
        } catch (Exception exception) {
            throw new CommonException(Response.NOT_EXITS_REQUIRED,"Không thể tính tổng tiền");
        }
    }

    @Override
    public Double totalMoneyForCreatedAt(LocalDateTime localDateTime) {
        return paymentRepository.totalMoneyForCreatedAt(localDateTime);
    }
    @Override
    public Integer countForCreatedAt(LocalDateTime localDateTime) {
        return paymentRepository.countByCreatedAt(localDateTime);
    }
}

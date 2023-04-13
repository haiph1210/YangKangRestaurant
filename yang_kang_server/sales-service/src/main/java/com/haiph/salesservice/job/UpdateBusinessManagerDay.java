package com.haiph.salesservice.job;

import com.haiph.salesservice.service.impl.BusinessManagerServiceImpl;
import com.haiph.salesservice.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@EnableAsync
@Slf4j
public class UpdateBusinessManagerDay {
    @Autowired
    public BusinessManagerServiceImpl businessManagerService;

    @Scheduled(cron = "0 0 23 * * ?")  // chạy vào 23h một lần
//    @Scheduled(cron = "0 35 11 * * ?") // 11h35 một lần (test)
    public void updateMoneyRemain() {
        businessManagerService.saveAllBMDay();
    }
}

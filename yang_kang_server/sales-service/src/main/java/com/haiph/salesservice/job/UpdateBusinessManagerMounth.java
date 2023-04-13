package com.haiph.salesservice.job;

import com.haiph.salesservice.entity.BusinessManagerMouth;
import com.haiph.salesservice.service.impl.BusinessManagerMounthServiceImpl;
import com.haiph.salesservice.service.impl.BusinessManagerServiceImpl;
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
public class UpdateBusinessManagerMounth {
    @Autowired
    private BusinessManagerMounthServiceImpl managerMounthService;
    @Scheduled(cron = "0 0 1 * * ?") // Chạy vào 0h00 ngày mùng 1 hàng tháng
//    @Scheduled(cron = "0 56 13 * * ?") // 13h43 một lần (test)
    public void updateBMMounth() {
        managerMounthService.saveAllBMMounth();
    }
}

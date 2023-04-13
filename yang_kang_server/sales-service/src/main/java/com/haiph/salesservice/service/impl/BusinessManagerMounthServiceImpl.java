package com.haiph.salesservice.service.impl;

import com.haiph.salesservice.Enum.Mouth;
import com.haiph.salesservice.entity.BusinessManager;
import com.haiph.salesservice.entity.BusinessManagerMouth;
import com.haiph.salesservice.repository.BusinessManagerMounthRepository;
import com.haiph.salesservice.repository.BusinessManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessManagerMounthServiceImpl implements com.haiph.salesservice.service.BusinessManagerMounthService {
    @Autowired
    private BusinessManagerMounthRepository managerMounthRepository;
    @Autowired
    private BusinessManagerRepository managerRepository;

    @Override
    public void saveAllBMMounth() {
        List<BusinessManager> businessManagers = managerRepository.findAll();
        List<BusinessManagerMouth> managerMouths = new ArrayList<>();
        for (BusinessManager businessManager : businessManagers) {
            if (businessManager != null) {
                if (!(managerMounthRepository.findById(resultMounth(businessManager.getMounth())).isPresent())) {
                    BusinessManagerMouth mouth = new BusinessManagerMouth();
                    mouth.setMouth(resultMounth(businessManager.getMounth()));
                    mouth.setYear(businessManager.getYear());
                    mouth.setTotalMoney(managerRepository.totalMoneyDay(businessManager.getMounth()));
                    mouth.setTotalPayment(managerRepository.countByMounth(businessManager.getMounth()));
                    managerMouths.add(mouth);
                    managerMounthRepository.saveAll(managerMouths);
                }
            }
        }
    }

    @Override
    public Page<BusinessManagerMouth> findAll(Pageable pageable) {
        Page<BusinessManagerMouth> page = managerMounthRepository.findAll(pageable);
        List<BusinessManagerMouth> managerMouths = page.getContent();
        return new PageImpl<>(managerMouths, pageable, page.getTotalElements());
    }

    private Mouth resultMounth(Integer mounth) {
        Mouth mouth = null;
        switch (mounth) {
            case 1:
                mouth = Mouth.January;
                break;
            case 2:
                mouth = (Mouth.February);
                break;
            case 3:
                mouth = (Mouth.March);
                break;
            case 4:
                mouth = (Mouth.April);
                break;
            case 5:
                mouth = (Mouth.May);
                break;
            case 6:
                mouth = (Mouth.June);
                break;
            case 7:
                mouth = (Mouth.July);
                break;
            case 8:
                mouth = (Mouth.August);
                break;
            case 9:
                mouth = (Mouth.September);
                break;
            case 10:
                mouth = (Mouth.October);
                break;
            case 11:
                mouth = (Mouth.November);
                break;
            case 12:
                mouth = (Mouth.December);
                break;
        }
        return mouth;
    }

}

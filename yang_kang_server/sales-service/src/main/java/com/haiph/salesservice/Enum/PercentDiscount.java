package com.haiph.salesservice.Enum;

import lombok.Getter;

@Getter
public enum PercentDiscount {
    ZERO(0,"0%"),
    ONE(1,"1%"),
    TWO(2,"2%"),
    THREE(3,"3%"),
    FIVE(5,"5%"),
    SEVENT(7,"7%"),
    TEN(10,"10%"),
    FIFTEEN(15,"15%")




    ;
    PercentDiscount(Integer percentDiscount, String description) {
        this.percentDiscount = percentDiscount;
        this.description = description;
    }

    private Integer percentDiscount;
    private String description;
}

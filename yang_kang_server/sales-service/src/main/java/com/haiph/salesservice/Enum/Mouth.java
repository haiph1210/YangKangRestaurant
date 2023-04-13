package com.haiph.salesservice.Enum;

import lombok.Getter;

@Getter
public enum Mouth {
    January(1),
    February(2),
    March(3),
    April(4),
    May(5),
    June(6),
    July(7),
    August(8),
    September(9),
    October(10),
    November(11),
    December(12);
    private Integer description;

    Mouth(Integer description) {
        this.description = description;
    }
}

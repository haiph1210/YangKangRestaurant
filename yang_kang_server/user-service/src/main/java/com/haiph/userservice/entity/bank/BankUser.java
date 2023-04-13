package com.haiph.userservice.entity.bank;

import com.haiph.userservice.entity.person.Person;
import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String personCode;
    private String bankUserNumber; // số tài khoản
    private String userOwenrName; // tên chủ tài khoản
    @Enumerated(EnumType.STRING)
    private BankName bankName; // tên ngân hàng
    @DefaultValue("0")
    private Double countPrice;


    @Getter
    public enum BankName {
        BIDV("BIDV","Ngân hàng TMCP Đầu Tư và Phát Triển BIDV"),
        TECH("TECHCOMBANK","Ngân hàng TMCP Kỹ Thương Techcombank"),
        ARG("AGRIBANK","Ngân hàng Nông Nghiệp và Phát Triển Nông Thôn Việt Agribank"),
        VIETCOMBANK("VIETCOMBANK","Ngân hàng Ngoại Thương Việt Nam");

        private final String code;
        private final String codeName;

        BankName(String code, String codeName) {
            this.code = code;
            this.codeName = codeName;
        }
    }
}

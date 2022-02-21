package com.example.restapiexample.entity;

import com.example.restapiexample.entity.enums.CardType;
import com.example.restapiexample.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card extends AbsEntity {

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Bank bank;

    @Column(unique = true)
    private String cardNumber;

    private String password;

    private String cv;

    private Double balance;

    @Enumerated(EnumType.STRING)
    private CardType type;

    private Date expiredDate;

}

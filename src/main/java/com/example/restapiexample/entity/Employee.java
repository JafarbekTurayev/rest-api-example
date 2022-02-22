package com.example.restapiexample.entity;

import com.example.restapiexample.entity.template.AbsNameEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"serial", "number"})
})
public class Employee extends AbsNameEntity {

    @ManyToOne(optional = false) //majburiy bo'ldi
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Card> cardList;

    @Column(unique = true)
//    @Pattern(regexp = "[+998]{1}[0-9]{9}")
    private String phone;
    @Pattern(regexp = "[A-Z]{2}")
    private String serial; //AA
    @Pattern(regexp = "[0-9]{7}")
    private String number; //4444206
    private Double salary;
}

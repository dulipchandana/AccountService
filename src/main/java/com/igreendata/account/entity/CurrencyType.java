package com.igreendata.account.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "currency_type")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_type_id")
    private Long id;

    private String currency;
}

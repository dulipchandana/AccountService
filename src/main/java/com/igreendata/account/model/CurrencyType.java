package com.igreendata.account.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "currency_type")
@Data
@Builder
@AllArgsConstructor
public class CurrencyType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_type_id")
    private Long id;

    private String currency;
}

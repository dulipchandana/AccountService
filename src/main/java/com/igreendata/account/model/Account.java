package com.igreendata.account.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "account")
@AllArgsConstructor
@Builder
@Data
public class Account extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    private String accountName;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_type_id")
    private CurrencyType currencyType;

    private Date balanceDate;

    private Double availableBalance;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    private Set<Transaction> transactions;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;
}

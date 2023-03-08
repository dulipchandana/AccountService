package com.igreendata.account.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
@AllArgsConstructor
@Data
@Builder
public class Transaction extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    private Date valueDate;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Double creditAmount;

    private Double debitAmount;

    private String transactionNarrative;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}

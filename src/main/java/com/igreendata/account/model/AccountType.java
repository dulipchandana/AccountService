package com.igreendata.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account_type")
@AllArgsConstructor
@Builder
@Data
public class AccountType  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id")
    private Long id;

    private String accountType;

}

package com.igreendata.account.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@Data
@Builder
public class User extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userName;
}

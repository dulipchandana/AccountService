package com.igreendata.account.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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

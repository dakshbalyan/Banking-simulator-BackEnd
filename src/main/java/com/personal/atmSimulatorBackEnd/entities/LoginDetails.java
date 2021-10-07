package com.personal.atmSimulatorBackEnd.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "login_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDetails {
    @Id
    @Column(name = "login_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loginIndex;

    @Column(name = "pin", nullable = false)
    private int loginPIN;

    @OneToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "account_id",
            referencedColumnName = "account_index"
    )
    private Account account;

}

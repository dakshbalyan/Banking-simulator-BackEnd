package com.personal.atmSimulatorBackEnd.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @Column(name = "account_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountIndex;

    @Column(name = "id", unique = true, nullable = false)
    private int accountID;

    @Column(name = "amount",nullable = false)
    private int amount;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "bank_id",
            referencedColumnName ="id"
    )
    private Bank bank;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_index"
    )
    private User user;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "account"
    )
    @ToString.Exclude
    @Transient
    private List<Transaction> transactionList;

    @OneToOne(
            mappedBy = "account"
    )
    @Transient
    private LoginDetails loginDetails;

}


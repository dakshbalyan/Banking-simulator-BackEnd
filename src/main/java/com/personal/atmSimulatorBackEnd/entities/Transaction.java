package com.personal.atmSimulatorBackEnd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @Column(name = "transaction_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionIndex;

    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

    @Column(name = "remaining_amount", nullable = false)
    private int remainingAmt;

    @Column(name = "debit", nullable = true)
    private int debitAmt;

    @Column(name = "credit", nullable = true)
    private int creditAmt;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "account_id",                    // column name in current table
            referencedColumnName = "account_index"  // reference column of foreignKey of another table
    )
//    @Transient
    @ToString.Exclude
    @JsonIgnore
    private Account account;
}

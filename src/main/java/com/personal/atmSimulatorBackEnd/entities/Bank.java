package com.personal.atmSimulatorBackEnd.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(
        name = "bank",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name","branch"})
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bank {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankID;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Please add bank name !")
    @Size(min = 3 , message = "Bank name should be atleast of length 3!")
    private String bankName;

    @Column(name = "branch", nullable = false)
    @NotBlank(message = "Please add branch name !")
    @Size(min = 3 , message = "branch name should be atleast of length 3!")
    private String branchName;

    @OneToMany(
            mappedBy = "bank"
    )
    @ToString.Exclude
    private List<Account> accountList;

}

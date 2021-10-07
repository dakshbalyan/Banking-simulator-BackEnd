package com.personal.atmSimulatorBackEnd.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "user_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-Increment
    private int userKey;

    @Column(name = "name",nullable = false)
    @NotBlank(message = "User name cannot be blank !")
    @Size(min = 3 , message = "User name should be atleast of length 3!")
    private String userName;

    @Column(name = "mobile_no", unique = true, nullable = false)
    @NotBlank(message = "Mobile number cannot be blank !")
    @Size(min = 10 ,max = 10, message = "Mobile number should be of 10 digits only")
    private String userMobileNo;

    @Column(name = "gender", nullable = false)
    @NotBlank(message = "Gender cannot be blank !")
    private String gender;

    @Column(name = "email", unique = true, nullable = true)
    @NotBlank(message = "Email cannot be blank !")
    @Email
    private String email;

    @Column(name = "DOB", nullable = false)
    private Date dob;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Address cannot be Blank !")
    @Size(min = 5, message = "Address length must be greater than 5")
    private String address;

    @Column(name = "aadhar_id", unique = true, nullable = false)
    @NotBlank(message = "Aadhar ID cannot be blank !")
    @Size(min = 12 ,max = 12, message = "Aadhar ID should be of 12 digits only")
    private String aadharID;

    @OneToMany(
            mappedBy = "user"
    )
    @ToString.Exclude
    private List<Account> accountList;

}

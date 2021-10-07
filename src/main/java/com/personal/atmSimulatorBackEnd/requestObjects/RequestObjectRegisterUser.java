package com.personal.atmSimulatorBackEnd.requestObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestObjectRegisterUser {
    // all parameters to be taken from user
    private String userName;
    private String userMobileNo;
    private String gender;
    private String userEmail;
    private Date userDOB;
    private int userAge;
    private String userAddress;
    private String userAadharId;
    private RequestObjectRegisterBank registerBank;

}

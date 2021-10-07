package com.personal.atmSimulatorBackEnd.requestObjects;

import com.personal.atmSimulatorBackEnd.entities.Bank;
import com.personal.atmSimulatorBackEnd.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestObjectCreateAccount {
    private User user;
    private Bank bank;
}

package com.personal.atmSimulatorBackEnd.utilityClasses;

import com.personal.atmSimulatorBackEnd.enums.CashAmount;
import com.personal.atmSimulatorBackEnd.enums.Gender;

public class ValidateEnums {

    public static boolean isFastCashAmountValid(int cashAmount) {
        CashAmount[] validAmount = CashAmount.values();

        for(CashAmount amt : validAmount){
            if(amt.getCashAmount() == cashAmount)
                return true;
        }

        return false;
    }

    public static boolean isGenderValid(String gender){
        Gender[] validGenders = Gender.values();

        for(Gender validGender : validGenders){
            if(validGender.toString().equalsIgnoreCase(gender))
                return true;
        }

        return false;
    }
}

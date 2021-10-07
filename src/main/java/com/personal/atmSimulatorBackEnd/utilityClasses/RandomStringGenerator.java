package com.personal.atmSimulatorBackEnd.utilityClasses;

import org.springframework.stereotype.Component;

@Component
public class RandomStringGenerator {
    public static int getRandomIntegerString(int stringLength){
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i <stringLength; i++){
            int randomIndex = (int) (Math.random()*10);
            sb.append(randomIndex);
        }

        return Integer.parseInt(sb.toString());
    }
}

package com.personal.atmSimulatorBackEnd.serviceClasses.readService;

import com.personal.atmSimulatorBackEnd.customExceptions.AccountDoesNotExistException;
import com.personal.atmSimulatorBackEnd.customExceptions.WrongCredentialsException;
import com.personal.atmSimulatorBackEnd.repositories.AccountRepository;
import com.personal.atmSimulatorBackEnd.repositories.LoginRepository;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectLoginCredentials;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserService {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticateUserService.class);

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LoginRepository loginRepository;

    public ResponseObjectStatus getAuthentication(
            RequestObjectLoginCredentials requestAuthentication
    ){
        ResponseObjectStatus response;
        try{
            response = ResponseObjectStatus.builder().build();
            int accountIndexForLogin;

            if(accountRepository.findByAccountID(requestAuthentication.getUserEnteredAccountID()) != null)
                accountIndexForLogin = accountRepository.findByAccountID(
                        requestAuthentication.getUserEnteredAccountID()).getAccountIndex();
            else
                throw new AccountDoesNotExistException("Account does not exist !");

            int fetchedPin = loginRepository.getLoginPinByAccountId(accountIndexForLogin);

            if(fetchedPin == requestAuthentication.getUserEnteredPIN()){
//                System.out.println("Authentication successful !");
                LOGGER.info("entered pin matches with fetched pin ! ");
            }else{
                throw new WrongCredentialsException("Entered pin is wrong !");
            }

            response.setStatus("200");
            response.setMessage("Authentication successful ! ");
            response.setException("");

        } catch (AccountDoesNotExistException e) {
            response = ResponseObjectStatus.builder().build();

            response.setStatus("400");
            response.setException(e.toString());
            e.printStackTrace();
        } catch (WrongCredentialsException e) {
            response = ResponseObjectStatus.builder().build();

            response.setStatus("400");
            response.setException(e.toString());
            e.printStackTrace();
        } catch (Exception e){
            response = ResponseObjectStatus.builder().build();

            response.setStatus("500");
            response.setException(e.toString());
            e.printStackTrace();
        }

        return response;
    }

}

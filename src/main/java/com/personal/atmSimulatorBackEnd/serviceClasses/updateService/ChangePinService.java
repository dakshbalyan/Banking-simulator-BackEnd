package com.personal.atmSimulatorBackEnd.serviceClasses.updateService;

import com.personal.atmSimulatorBackEnd.customExceptions.AccountDoesNotExistException;
import com.personal.atmSimulatorBackEnd.customExceptions.WrongCredentialsException;
import com.personal.atmSimulatorBackEnd.entities.LoginDetails;
import com.personal.atmSimulatorBackEnd.repositories.AccountRepository;
import com.personal.atmSimulatorBackEnd.repositories.LoginRepository;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectPinChange;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangePinService {
    private final Logger LOGGER = LoggerFactory.getLogger(ChangePinService.class);

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LoginRepository loginRepository;

    @Transactional
    public ResponseObjectStatus changeAccountPin(
            RequestObjectPinChange requestPinChange){
        ResponseObjectStatus response;
        try{
            response = ResponseObjectStatus.builder().build();

            if(accountRepository.findByAccountID(requestPinChange.getAccountID()) == null)
                throw new AccountDoesNotExistException("Account does not exist !");
            int account_id = accountRepository.findByAccountID(
                    requestPinChange.getAccountID()).getAccountIndex();
            LoginDetails loginDetails = loginRepository.getLoginByAccountId(account_id);

            LOGGER.warn(String.valueOf(loginDetails.getLoginPIN()));
            LOGGER.warn(String.valueOf(requestPinChange.getOldPin()));


            if(requestPinChange.getOldPin() == loginDetails.getLoginPIN()){
                loginDetails.setLoginPIN(requestPinChange.getNewPin());
                loginRepository.save(loginDetails);
                LOGGER.info("new Pin saved in login tbl successfully !");
            }else{
                throw new WrongCredentialsException("Entered pin does not match with existing PIN !!!!");
            }

            response.setStatus("200");
            response.setMessage("Pin changed successfully !");
        } catch (WrongCredentialsException e) {
            response = ResponseObjectStatus.builder()
                    .status("400")
                    .exception(e.toString())
                    .build();

            e.printStackTrace();
        } catch (AccountDoesNotExistException e) {
            response = ResponseObjectStatus.builder()
                    .status("400")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        } catch (Exception e) {
            response = ResponseObjectStatus.builder()
                    .status("500")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        }

        return response;
    }
}

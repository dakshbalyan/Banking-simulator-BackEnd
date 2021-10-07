package com.personal.atmSimulatorBackEnd.serviceClasses.createService;

import com.personal.atmSimulatorBackEnd.customExceptions.InvalidInputException;
import com.personal.atmSimulatorBackEnd.entities.Account;
import com.personal.atmSimulatorBackEnd.entities.LoginDetails;
import com.personal.atmSimulatorBackEnd.entities.User;
import com.personal.atmSimulatorBackEnd.repositories.AccountRepository;
import com.personal.atmSimulatorBackEnd.repositories.LoginRepository;
import com.personal.atmSimulatorBackEnd.repositories.UserRepository;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectCreateAccount;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectRegisterUser;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectCreateEntity;
import com.personal.atmSimulatorBackEnd.serviceClasses.CreateService;
import com.personal.atmSimulatorBackEnd.utilityClasses.RandomStringGenerator;
import com.personal.atmSimulatorBackEnd.utilityClasses.ValidateEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateAccountService implements CreateService<Account> {
    private final Logger LOGGER = LoggerFactory.getLogger(CreateAccountService.class);

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User create(RequestObjectRegisterUser registerUser) {
        User user = User.builder().build();
        try{
            if(registerUser == null)
                throw new InvalidInputException("User details missing !");
            if(!ValidateEnums.isGenderValid(registerUser.getGender()))
                throw new InvalidInputException("Selected gender not available !!");

            if( userRepository.findByUserMobileNo(registerUser.getUserMobileNo()) != null){
                user = userRepository.findByUserMobileNo(registerUser.getUserMobileNo());

                LOGGER.info("User fetched !!!!");
            }else {
                user.setUserName(registerUser.getUserName());
                user.setUserMobileNo(registerUser.getUserMobileNo());
                user.setGender(registerUser.getGender().toUpperCase());
                user.setEmail(registerUser.getUserEmail());
                user.setDob(registerUser.getUserDOB());
                user.setAge(registerUser.getUserAge());
                user.setAddress(registerUser.getUserAddress());
                user.setAadharID(registerUser.getUserAadharId());

                userRepository.save(user);

                LOGGER.info("User saved in DB !!");
            }
        } catch (InvalidInputException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }

    @Transactional
    public ResponseObjectCreateEntity create(RequestObjectCreateAccount createAccount) {
        ResponseObjectCreateEntity response;
        try{
            response = ResponseObjectCreateEntity.builder().build();

            if(createAccount == null)
                throw new InvalidInputException("Details not entered properly !");
//            LOGGER.warn(String.valueOf(createAccount.getUser()));
//            LOGGER.warn(String.valueOf(createAccount.getBank()));
            Account account = Account.builder()
                    .accountID(RandomStringGenerator.getRandomIntegerString(5))
                    .amount(0)
                    .bank(createAccount.getBank())
                    .user(createAccount.getUser())
                    .build();
            accountRepository.save(account);
            LoginDetails loginDetails = LoginDetails.builder()
                    .account(account)
                    .loginPIN(RandomStringGenerator.getRandomIntegerString(4))
                    .build();

            loginRepository.save(loginDetails);
            response.setStatus("200");
            response.setMessage("Account created successfully !");
            response.setAccountId(account.getAccountID());
            response.setAccountPin(loginDetails.getLoginPIN());

            LOGGER.info("Account created successfully !");

        } catch (InvalidInputException e) {
            response = ResponseObjectCreateEntity.builder()
                            .status("400")
                            .exception(e.toString())
                            .build();
            e.printStackTrace();
        } catch (Exception e){
            response = ResponseObjectCreateEntity.builder()
                            .status("400")
                            .exception(e.toString())
                            .build();
            e.printStackTrace();
        }

        return response;
    }
}

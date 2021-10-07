package com.personal.atmSimulatorBackEnd.repositories;

import com.personal.atmSimulatorBackEnd.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserMobileNo(String userMobileNo);

    User findByEmail(String email);

    User findByAadharID(String aadharID);
    // Only save user query comes to mind here which can be automatically saved
}

package mykyta.anyshchenko.diploma.model;

import mykyta.anyshchenko.diploma.model.enums.Gender;
import mykyta.anyshchenko.diploma.model.enums.Role;

import java.time.LocalDate;

public interface User {

    String getId();
    String getName();
    String getLastName();
    Role getRole();
    String getEmail();
    String getPassword();
    String getPhoneNumber();
    LocalDate getDateOfBirth();
    Gender getGender();
    String getHomeCountry();
}

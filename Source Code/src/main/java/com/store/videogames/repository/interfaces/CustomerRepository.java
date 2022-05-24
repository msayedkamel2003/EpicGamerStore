package com.store.videogames.repository.interfaces;

import com.store.videogames.repository.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{

    /**
     * This function is special because the spring seucrity uses it for user login form
     */
    @Query("SELECT u FROM Customer u WHERE u.email = :email")
    Customer getConfigUsername(@Param("email") String email);

    //These functions gets a Customer Object
    Customer getCustomerByEmail(String email);
    Customer getCustomerByUsername(String username);
    Customer getCustomerByResetPasswordToken(String token);

    @Query("SELECT u FROM Customer u WHERE u.emailVerificationCode = ?1")
    Customer getCustomerByEmailVerificationCode(String emailVerificationCode);

    //These functions get a List of Customer Objects
    List<Customer> getCustomerByCountryName(String countryName);
    List<Customer> getCustomerByCityName(String cityName);
    List<Customer> getCustomerByStreetName(String streetName);
    List<Customer> getCustomerByZipCode(int zipCode);
    List<Customer> getCustomerByRegistrationDate(LocalDate registrationDate);
//    List<Customer> getCustomerByRoles(List<Roles> roles);
    List<Customer> getCustomerByEnabled(Boolean isEnabled);
    List<Customer> getCustomerByRegistrationTime(LocalTime registrationTime);


}

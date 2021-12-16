package com.favisan84.customer.service;

import com.favisan84.customer.model.dto.CustomerDTO;
import com.favisan84.customer.model.entity.CustomerEntity;
import com.favisan84.customer.model.repository.CustomerRepository;
import com.favisan84.customer.service.bo.exceptions.CustomerNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public CustomerDTO signup(CustomerDTO customer) {
        validate(customer);
        CustomerEntity entity = new CustomerEntity();
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());

        BeanUtils.copyProperties(repository.save(entity), customer);

        return customer;
    }

    private void validate(CustomerDTO customer) {
        validateEmail(customer.getEmail());
    }

    private void validateEmail(String email){
        checkObjectNull(email,"Email obrigatório");
    }

    private void checkObjectNull(Object object, String mensagem){
        if(object == null)
            throw new ValidationException(mensagem);
    }

    public CustomerDTO findByEmail(String email) {
        CustomerDTO customer = new CustomerDTO();
        repository.findByEmail(email).ifPresentOrElse(
                entity -> {
                    customer.setName(entity.getName());
                    customer.setEmail(entity.getEmail());
                },
               () -> {throw new CustomerNotFoundException("Email not found"); }
        );

        return customer;
    }
}

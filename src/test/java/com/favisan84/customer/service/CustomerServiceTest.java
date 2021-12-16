package com.favisan84.customer.service;

import com.favisan84.customer.model.dto.CustomerDTO;
import com.favisan84.customer.model.entity.CustomerEntity;
import com.favisan84.customer.model.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService service;

    @Mock
    private CustomerRepository repository;

    private CustomerEntity entity;

    @Before
    public void setUp(){
        entity = new CustomerEntity();
        entity.setName("Name");
        entity.setEmail("teste@teste.com");
        given(repository.save(any())).willReturn(entity);
        given(repository.findByEmail(entity.getEmail())).willReturn(Optional.of(entity));
    }

    @Test
    public void shouldReturnCustomerByEmail(){
        String email = "teste@teste.com";
        CustomerDTO customer = service.findByEmail(email);
        Assert.assertEquals(customer.getEmail(), email);
    }

    @Test
    public void shouldPersistCustomer(){
        CustomerDTO customer = new CustomerDTO();
        customer.setName("Customer Name");
        customer.setEmail("email@email.com");

        CustomerDTO readCustomer = service.signup(customer);
        Assert.assertEquals(readCustomer.getName(), entity.getName());
    }

    @Test(expected = ValidationException.class)
    public void shouldNotAcceptCustomerWithoutEmail(){
        CustomerDTO customer = new CustomerDTO();
        customer.setName("Customer Name");
        service.signup(customer);
    }

}

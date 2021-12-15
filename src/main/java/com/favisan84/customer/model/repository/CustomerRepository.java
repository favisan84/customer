package com.favisan84.customer.model.repository;

import com.favisan84.customer.model.entity.CustomerEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, ObjectId> {

    Optional<CustomerEntity> findByEmail(String email);
}

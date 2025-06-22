package com.springboot.StoreApplicationApi.repositories;

import com.springboot.StoreApplicationApi.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
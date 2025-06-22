package com.springboot.StoreApplicationApi.repositories;

import com.springboot.StoreApplicationApi.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}
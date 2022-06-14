package org.generation.WebProject.repository;

//The ItemRepository created is to extend the CRUDRepository provided by Spring Data
// JPA package

import org.springframework.data.repository.*;
import org.generation.WebProject.repository.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>
{
    //Not only the ItemRepository inherit all the methods from the CrudRepository
    // Interface, ItemRepository and also have its own methods (do not need)

    //So now I can use the ItemRepository interface to perform basic CRUD operation
}



package org.generation.WebProject.service;

import org.generation.WebProject.repository.ItemRepository;
import org.generation.WebProject.repository.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceMySQL implements ItemService {

    /*
        Dependency Injection
        - transferring the task of creating the object to someone else

        Normally how we create an instance object of another class
        ItemServiceMySQL depends on the CRUDRepository Class to perform the CRUD operation

        //We are creating the instance object inside the ItemServiceMySQL Class
        ItemServiceMySQL myService = new CrudRepository();  //Cannot

        //ItemServiceMySQL is dependent on CrudRepository

        //1) Adhere to the abstraction (hiding details) principle, where we only have access to the interface of the Class object.
        //We have no direct access to the Class Object itself.

        //2) Dependency Injection - Inversion of Control (DI - IOC) - The creation of the instance object is done by another object instead of in the ItemService SQL Class
     */

    //The dependency instance object will be injected through the constructor

    private final ItemRepository itemRepository;

    public ItemServiceMySQL(@Autowired ItemRepository itemRepository)
    {
        //Injecting an instance object of the CrudRepository object
        //We are able to make use of this.itemRepository to access the properties and
        // methods from the CrudRepository object

        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {  // you can use 'save all' method as well, but you are just saving one item now.
        return itemRepository.save(item); // CrudRepository object
    }

    @Override
    public void delete(int itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<Item> all()
    {
        List<Item> result = new ArrayList<>();
        itemRepository.findAll().forEach(result :: add);
        return result;
    }

    @Override
    public Item findById(int itemId) {

        // item is an Object
        Optional<Item> item = itemRepository.findById(itemId); // cannot be a null
        Item itemResponse = item.get();
        return itemResponse;
    }

}

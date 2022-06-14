package org.generation.WebProject.service;

//Interface as a guide on what are the methods that are available that the controller
//can call and perform action.

//Service layer is to developed by developer A, controller layer is to be developed by developer B
//Developer B will be accesssing the interface document to know what are the methods available for
//him/her to call

import org.generation.WebProject.repository.entity.Item;
import java.util.List;

public interface ItemService {

    //will show what are the methods for this Item Service
    //e.g. 1) provides list all items from the database
    List<Item> all();

    //this method is used for both add item and edit item
    Item save (Item item);

    void delete(int itemId);

    Item findById(int itemId);

}

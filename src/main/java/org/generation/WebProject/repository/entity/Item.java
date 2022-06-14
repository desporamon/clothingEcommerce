package org.generation.WebProject.repository.entity;

//This is in the Model component (MVC Design Pattern)
//Item Class object will be used to map up with the Item Table from the database

//We are using the same name for Class Object and Table name, as well as the name
// naming convention for the attributes

import org.generation.WebProject.controller.dto.ItemDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity    //To inform Hibernate to map to a table with this class object
public class Item {

    //using Wrapper Class Integer (Object) instead of int (Primitive Data type)

    //We need to identify which attribute is the id (Primary Key), and how the id is
    // generated

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  //need to pass the id to a class method findItemById()
    private String name;
    private String description;
    private String imageUrl;
    private String style;
    private double price;  //Primitive Data type

    public Item() {}

    public Item(ItemDto itemDto) {
        this.name = itemDto.getName();
        this.description = itemDto.getDescription();
        this.imageUrl = itemDto.getImageUrl();
        this.style = itemDto.getStyle();
        this.price = itemDto.getPrice();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", imageUrl='"
                + imageUrl + '\'' + ",style='" + style + '\'' + ", price='" + price + '}';

    }
}

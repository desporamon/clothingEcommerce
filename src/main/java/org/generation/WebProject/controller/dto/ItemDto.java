package org.generation.WebProject.controller.dto;

//Data Transfer Object for Item
//In the controller component (MVC), this class object is going to receive the info/data that is sent from
// the client (through the HTTP POST request)

//Controller will then call the require method (save method) to perform Create and pass the info/data to the
// service layer

public class ItemDto {
    //no id as generation of ID is done by backend, ID is AI (auto increment) when new record is inserted
    //cannot ask user to key in the product id, need to ensure uniqueness

    private String name;
    private String description;
    private String imageUrl;
    private String style;
    private double price;

    public ItemDto( String name, String description, String imageUrl, String style, double price )
    {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.style = style;
        this.price = price;
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
}

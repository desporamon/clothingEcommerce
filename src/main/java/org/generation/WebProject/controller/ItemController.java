package org.generation.WebProject.controller;

import org.generation.WebProject.component.FileUploadUtil;
import org.generation.WebProject.controller.dto.ItemDto;
import org.generation.WebProject.repository.entity.Item;
import org.generation.WebProject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/item")
public class ItemController {

    //ItemController is dependent on ItemService to perform the save, delete, all
    //findItemById

    @Value("${image.folder}")
    private String imageFolder; //imageFolder  variable the value = productimages

    final ItemService itemService;

    public ItemController( @Autowired ItemService itemService )
    {
        this.itemService = itemService;
    }

    //1)Create an API endpoint for GET HTTP Request from the client
    //CORS (Cross-origin resource sharing)

    @CrossOrigin
    @GetMapping( "/all" )
    public Iterable<Item> getItems()
    {
        return itemService.all();
    }

    // 2. Create an API endpoint for GET HTTP Request from the client by Id
    // Checked: ok
    @CrossOrigin
    @GetMapping("/{id}")
    public Item findItemById(@PathVariable Integer id)
    {
        return itemService.findById(id);
    }

    // 3. Create an API endpoint for DELETE HTTP Request from the client by Id
    @CrossOrigin
    @DeleteMapping("/{id}")
    public void delete( @PathVariable Integer id )
    {
        itemService.delete(id);
    }

    //4) Create an API endpoint for POST HTTP Request from the client
    @CrossOrigin
    @PostMapping("/add")
    public void save(  @RequestParam(name="name", required = true) String name,
                       @RequestParam(name="description", required = true) String description,
                       @RequestParam(name="imageUrl", required = true) String imageUrl,
                       @RequestParam(name="style", required = true) String style,
                       @RequestParam(name="price", required = true) double price,
                       @RequestParam("imagefile") MultipartFile multipartFile) throws IOException {

        //Prepare the fileName by cleaning up the path for saving the image file
        //Part 1 - upload the image file into the productImages folder in the server
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        FileUploadUtil.saveFile(imageFolder, fileName, multipartFile);

        //Part 2 - save all the records into MySQL database
        //productImages/t-shirt1.jpg
        String fullPath = imageFolder + "/" + imageUrl;
        ItemDto itemDto = new ItemDto (name, description, fullPath, style, price);
        itemService.save(new Item(itemDto));
    }
}

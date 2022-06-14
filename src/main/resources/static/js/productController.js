//Doing a Product web app, in product page to 
//display the name, description, imageUrl, style, price, ..., ...,.....,....


const createHTMLList = (index, name, description, imageURL) =>
`
<div class="col-lg-4">
<div class="card" style="width: 18rem;">
    <img src=${imageURL} class="card-img-top"
        alt="image">
    <div class="card-body">
        <h5 class="card-title">${name}</h5>
        <p class="card-text">${description}</p>
        <a id="${index}" href="#" class="btn btn-primary" data-toggle="modal" data-target="#productModal">More</a>
    </div>
</div>
</div>

`;


function displayProductDetails(item)
{
    document.querySelector("#modalName").innerText = item.name;
    document.querySelector("#modalImg").src = item.imageUrl;
    document.querySelector("#modalStyle").innerText = item.style;
    document.querySelector("#modalPrice").innerText = item.price;

}


class ProductsController 
{
    constructor()
    {
        //Configuration  of dev and prod URL - usually we will fetch a json file with all
        //the API in the dev or prod environment
        this.domainURL_Dev = "http://localhost:8080/";
        this.domainURL_Prod = "https://desmondwebproject.herokuapp.com/";

        this.addItemAPI = this.domainURL_Prod + "item/add";
        this.allItemAPI = this.domainURL_Prod + "item/all";

        this._items = [];       //create an array to store the details of product items
    }

    //method to add the items into the database
    addItem(name, description, imageUrl, style, price, imageObject)
    {
         var productController = this;
                const formData = new FormData();
                formData.append('name', name);
                formData.append('description', description);
                formData.append('imageUrl', imageUrl);
                formData.append('style', style);
                formData.append('price', price);
                formData.append('imagefile',imageObject);

               fetch(this.addItemAPI, {
                     method: 'POST',
                     body: formData
                     })
                     .then(function(response) {
                         console.log(response.status); // Will show you the status
                         if (response.ok) {
                             alert("Successfully Added Product!")
                         }
                         else {
                            throw Error(response.statusText);
                         }
                     })
                     .catch((error) => {
                         console.error('Error:', error);
                         alert("Error adding item to Product")
                     });

    }

    //This method will do the fetch method to fetch data from database using the REST API endpoint from Spring Boot
    displayItem()
    {
        let productController = this;
        productController._items = [];

        fetch(this.allItemAPI)
                    .then((resp) => resp.json())
                    .then(function(data) {
                        console.log("2. receive data")
                        console.log(data);
                        data.forEach(function (item, index) {

                            const itemObj = {
                                id: item.id,        //1
                                name: item.name,    //Cat Tee Black T-shirt
                                description: item.description,   //4 MSL
                                imageUrl: item.imageUrl,
                                style: item.style,
                                price: item.price
                           };
                            productController._items.push(itemObj);
                      });

                      productController.renderProductPage();

                    })
                    .catch(function(error) {
                        console.log(error);
                    });
    }



    //Based on the item fetched from the displayItem() method and show the products in the product page
    renderProductPage()
    {
        let productHTMLList = [];
        
        for (let i=0; i<this._items.length; i++)
        {
            const item = this._items[i];            //assign the individual item to the variable

            const productHTML = createHTMLList(i, item.name, item.description, item.imageUrl);

            productHTMLList.push(productHTML);
        }

        //Join all the elements/items in my productHTMLList array into one string, and seperate by a break
        const pHTML = productHTMLList.join('\n');
        document.querySelector('#row').innerHTML = pHTML;


        //addEventListener - click 
        for (var i=0; i<this._items.length; i++)
        {
            const item = this._items[i];
            document.getElementById(i).addEventListener("click", function() { displayProductDetails(item);} );
        }


    }


}   //End of ProductsController class

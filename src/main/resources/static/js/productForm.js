const productsControl = new ProductsController();
let storeImage = ""

//When user clicks on 'Save Item', calls API to add items to the database
//Add an 'onsubmit' event listener for productform to add a product
newItemForm.addEventListener('submit', (event) => {
    // Prevent default action, do not need the form to submit first (1) Form Validation
    //(2) We are using our own fetch method to send the data over to the backend (Rest API)
    event.preventDefault();

    // Select the inputs
    const newItemNameInput = document.querySelector('#newItemNameInput');
    const newItemDescription = document.querySelector('#newItemDescription');
    const newItemImageUrl = document.querySelector('#newItemImageFile');
    const newItemStyle = document.querySelector('#newItemStyle');
    const newItemPrice = document.querySelector('#newItemPrice');

    /*
        Do the Validation code here
    */

    // Get the values of the inputs - variable names to be same as MySQL columns
    const name = newItemNameInput.value;
    const description = newItemDescription.value;
    const imageUrl = newItemImageUrl.value.replace("C:\\fakepath\\", "");
    //C:/desktop/t-shirt_new.jpg -> C:/fakepath/t-shirt_new.jpg
    //We just need to send the image filename (t-shirt_new.jpg) to the backend
    //imageUrl will only contain the image filename (t-shirt_new.jpg)

    const style = newItemStyle.value;
    const price = newItemPrice.value;

    // Clear the form
    newItemNameInput.value = '';
    newItemDescription.value = '';
    newItemImageUrl.value = '';
    newItemStyle.value = '';
    newItemPrice.value = '';

    // Add the task to the task manager
    //After we get all the values and object from the form, we will call the addItem
    //method in the ProductsController class to peform the POST HTTP Request by calling the
    //REST API provided by the backend
    productsControl.addItem(name, description, imageUrl, style, price, storeImage);

});

// select file input
const input = document.querySelector('#newItemImageFile');

// add event listener
input.addEventListener('change', () => {
    storeImage = input.files[0];
    //storing the first file element to the variable
    //file[0] object can be image, audio, video, pdf, word doc

});

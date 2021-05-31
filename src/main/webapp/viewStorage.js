document.addEventListener("DOMContentLoaded", () =>{
    let IDdata = sessionStorage.getItem('userID')
    let ProductData = sessionStorage.getItem('products')

    console.log("Session Storage Items: \n User ID: " + IDdata + "\n Products(ID + Quantity): " +  ProductData)

    // sendUserID(data)
    sendProducts(IDdata,ProductData)
    reload()
})

window.onload = function() {
    //considering there aren't any hashes in the urls already
    if(!window.location.hash) {
        //setting window location
        window.location = window.location + '#loaded';
        //using reload() method to reload web page
        window.location.reload();
    }
}

function sendUserID(id){
    const userID = {
        userID: id,
    }

    fetch('http://localhost:8080/viewStorage/getProducts', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userID),
    })
}


function sendProducts(id, data2){
    const products = {
        id: id,
        products: data2,
    }

    fetch('http://localhost:8080/viewStorage/getProducts', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(products),
    })
}


function saveButtonFunc(id){
    // var inputValue= document.getElementById('amountInput').value
    // console.log("inputValue: " + inputValue)
    console.log("SAVE BUTTON INITIAL ID: " + id)
    var id = ""+id
    id = id.replace("BUTTON", "")
    console.log("received ID: " + id)
    if(id){
        console.log("IF STATEMENT")
        var id2 = 'INPUT'+id
        console.log("VAR id2 " + id2)
    }
    var inputValue= document.getElementById(id2).value
    console.log("Value of :" + id2 + " is: "+ inputValue)
    const amount = {
        id: id,
        quantity: inputValue,
        list_inventory_id: sessionStorage.getItem('userID'),
    }

    fetch('http://localhost:8080/viewStorage/updateAmounts', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(amount),
    })



}

function deleteButtonFunc(id){
    console.log("DELETE BUTTON INITIAL ID: " + id)
    var id = ""+id
    var id = id.replace("DELBUTTON", "")
    console.log("received ID: " + id)


    const delinfo = {
        productID: id,
        amount: 0,
    }
    fetch('http://localhost:8080/viewStorage', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(delinfo),
    })
}



document.addEventListener("DOMContentLoaded", () =>{
    getAllItems()
})

function goToStoragePage(){
    window.location="viewStorage.html"
}

function postExistingItem(){
    var id = sessionStorage.getItem('userID')
    var itemID = document.getElementById('productid').value
    const item = {
        inventoryid: id,
        id: itemID
    }
    fetch('/createItem', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        },
        body: JSON.stringify(item),
    })

    window.location = "viewStorage.html"
}

function getAllItems(){
    fetch('/createItem/getAllItems', {
        method: 'GET', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        },
    })
        .then(response => response.json())
        .then(function(data){
            var text = "";
            for (x in data){
                text += "ID: " + JSON.stringify(data[x]['id']) +
                    ", NAME: " + JSON.stringify(data[x]['name']) +
                    ", DESCRIPTION: " + JSON.stringify(data[x]['description']) +
                    ", PRICE: " + JSON.stringify(data[x]['price']) +
                    "<br><br>"
            }
            document.getElementById('allItemsDiv').innerHTML = text
        })
}

function postItem(){
    var id = sessionStorage.getItem('userID')
    var name= document.getElementById('name').value
    var description= document.getElementById('description').value
    var price= document.getElementById('price').value

    const item = {
        id: id,
        name: name,
        description: description,
        price: price
    }
    fetch('/createItem/postItem', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        },
        body: JSON.stringify(item),
    })

    window.location = "viewStorage.html"
}
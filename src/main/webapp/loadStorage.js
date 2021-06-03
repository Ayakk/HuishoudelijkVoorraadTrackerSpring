document.addEventListener("DOMContentLoaded", () =>{

})

function gotoCreateItem(){
    window.location ="createItem.html"
}

function gotoUpdateItem(){
    window.location ="updateItem.html"
}

function shoppingListFunc(){
    fetch('http://localhost:8080/htmlStorage/getItemData', {
        method: 'GET', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(function(data){
            console.log(data)
            var text = "";
            let contacts = new Map()
            for (x in data) {
                text = "ID: " + data[x]['id'] + "NAAM: " + data[x]['name']
                var input = prompt(text);
                if (input != null && input != "") {
                    var berekening = input - data[x]['quantity']
                    contacts.set(data[x]['name'], berekening)
                }
            }
            var text3= "";

            for (const entry of contacts.entries()) {
                if(entry[1] < 0){
                    text3 += "Product: " + entry[0]+ " " + "Hoeveelheid: 0, u heeft genoeg van dit product \n"
                } else{
                    text3 += "Product: " + entry[0]+ " " + "Hoeveelheid: "+ entry[1] +" \n"
                }
            }
            alert(text3)
        })
}

window.onload = function() {
    getInventory()
    giveID()
    //considering there aren't any hashes in the urls already
    if(!window.location.hash) {
        //setting window location
        window.location = window.location + '#loaded';
        //using reload() method to reload web page
        window.location.reload();
    }
}

function logOutFunc(){
    sessionStorage.clear()
    fetch('http://localhost:8080/htmlStorage/logout', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: "LOGOUT",
    })
    window.location="http://localhost:8080/index.html"
}


function giveID(){
    var x = sessionStorage.getItem('userID')
    const id = {
        id: x,
    }
    fetch('http://localhost:8080/htmlStorage/giveID', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(id),
    })
}

//getinventory in -> 5,20000;6,3;7,2; format
function getInventory(){
    fetch('http://localhost:8080/htmlStorage/getInventory', {
        method: 'GET', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(function(getinvData){
            console.log(getinvData[sessionStorage.getItem('userID')])

            //call getItems function
            let id = sessionStorage.getItem('userID')
            getItems(id, getinvData[sessionStorage.getItem('userID')])
        })
}

//getdata from items (name, description etc.)
function getItems(id, products){
    const inventory = {
        id: id,
        products: products
    }
    fetch('http://localhost:8080/htmlStorage/giveInventoryData', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(inventory),
    })

    fetch('http://localhost:8080/htmlStorage/getItemData', {
        method: 'GET', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(function(data){
            console.log(data)
            var text = "";
            for (x in data) {
                console.log(x)
                text +="<div id=\"itemDiv" + data[x]['id'] + "\">"
                text +="ID:             "+data[x]['id'] + "<br>"
                text +="NAAM:           "+data[x]['name'] + "<br>"
                text +="OMSCHRIJVING:   "+data[x]['description'] + "<br>"
                text +="PRIJS:          "+data[x]['price'] + "<br>"
                text +="HOEVEELHEID:    <input id=\"INPUT"+data[x]['id'] +"\" placeholder="+ data[x]['quantity'] +" type=\"number\">"
                text += "<button id=\"saveButton" + data[x]['id'] +"\" onClick=saveButtonFunc(this.id) id=BUTTON>Opslaan</button>"
                text += "<button id=\"deleteButton" + data[x]['id'] +"\" onClick=deleteButtonFunc(this.id) id=BUTTON>Verwijderen</button>"
                text +="<br>"
                text +="<hr>"
                text +="</div>"
            }
            document.getElementById('itemName').innerHTML = text
            console.log(data[6]['price'])
        })
}

function saveButtonFunc(id){
    console.log("SAVE BUTTON INITIAL ID: " + id)
    var id = ""+id
    id = id.replace("saveButton", "")
    console.log("received ID: " + id)
    if(id){
        console.log("IF STATEMENT")
        var id2 = 'INPUT'+id
        console.log("VAR id2 " + id2)
    }
    var inputValue= document.getElementById(id2).value
    console.log("Value of :" + id2 + " is: "+ inputValue)
    var test = sessionStorage.getItem('userID');

    const amount = {
        id: id,
        quantity: inputValue,
        inventoryid: test,
    }

    fetch('http://localhost:8080/htmlStorage/updateAmounts', {
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
    var id = id.replace("deleteButton", "")
    console.log("received ID: " + id)

    var quantityValue= document.getElementById('INPUT'+id).getAttribute('placeholder')


    const item = {
        id: id,
        quantity: quantityValue,
        inventoryid: sessionStorage.getItem('userID')
    }
    fetch('htmlStorage/deleteItem', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(item),
    })

    var el = document.getElementById('itemDiv' + id);
    el.remove(); // Removes the div with the 'div-02' id
}
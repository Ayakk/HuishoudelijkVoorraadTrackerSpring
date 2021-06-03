document.addEventListener("DOMContentLoaded", () =>{
    getInventory()
    giveID()
})

function gotoCreateItem(){
    window.location ="createItem.html"
}

function gotoUpdateItem(){
    window.location ="updateItem.html"
}

window.onload = function() {
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
                text +="ID: "+data[x]['id'] + "<br>"
                text +="NAAM: "+data[x]['name'] + "<br>"
                text +="OMSCHRIJVING: "+data[x]['description'] + "<br>"
                text +="PRIJS: "+data[x]['price'] + "<br>"
                text +="<input id=\"INPUT"+data[x]['id'] +"\" placeholder="+ data[x]['quantity'] +" type=\"number\">"
                text += "<button id=\"saveButton" + data[x]['id'] +"\" onClick=saveButtonFunc(this.id) id=BUTTON>Opslaan</button>"
                text += "<button id=\"deleteButton" + data[x]['id'] +"\" onClick=deleteButtonFunc(this.id) id=BUTTON>Verwijderen</button>"
                text +="<br>"
                text +="<hr>"
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
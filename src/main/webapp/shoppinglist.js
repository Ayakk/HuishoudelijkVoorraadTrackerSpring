document.addEventListener("DOMContentLoaded", () =>{

})

function gotoUpdateItem(){
    window.location ="updateItem.html"
}

function shoppingListFunc(){
    fetch('/htmlStorage/getItemData', {
        method: 'GET', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        },
    })
        .then(response => response.json())
        .then(function(data){
            console.log(data)
            var text = "";
            let contacts = new Map()
            for (x in data) {
                text = "Hoeveel producten wilt u minimaal hebben voor: \n ID: " + data[x]['id'] + ", NAAM: " + data[x]['name']
                var input = prompt(text);
                if (input != null && input != "") {
                    var berekening = input - data[x]['quantity']
                    contacts.set(data[x]['name'], berekening)
                }
            }
            var text3= "";

            for (const entry of contacts.entries()) {
                console.log(entry[1])
                if(entry[1] < 0){
                    text3 += "Product: " + entry[0]+ " " + ", Hoeveelheid: 0, u heeft genoeg van dit product \n"
                } else if(entry[1] >= 0){
                    text3 += "Product: " + entry[0]+ " " + ", Hoeveelheid: "+ entry[1] +" \n"
                } else{
                    text3 += "Product: " + entry[0]+ " " + ", U heeft niks ingevuld voor dit product! \n"
                }
            }
            alert(text3)
        })
}

window.onload = function() {
    getInventory()
    giveID()
    //considering there aren't any hashes in the urls already
    // if(!window.location.hash) {
    //     //setting window location
    //     window.location = window.location + '#loaded';
    //     //using reload() method to reload web page
    //     window.location.reload();
    // }
}

function logOutFunc(){
    sessionStorage.clear()
    fetch('/htmlStorage/logout', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        },
        body: "LOGOUT",
    })

    fetch('/auth/signout', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        },
        body: "LOGOUT",
    })
    window.location="/index.html"
}


function giveID(){
    var x = sessionStorage.getItem('userID')
    const id = {
        id: x,
    }
    fetch('/htmlStorage/giveID', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        },
        body: JSON.stringify(id),
    })
}

//getinventory in -> 5,20000;6,3;7,2; format
function getInventory(){
    fetch('/htmlStorage/getInventory', {
        method: 'GET', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
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
    console.log(products)
    fetch('/htmlStorage/giveInventoryData', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        },
        body: JSON.stringify(inventory),
    })

    fetch('/htmlStorage/getItemData', {
        method: 'GET', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        },
    })
        .then(response => response.json())
        .then(function(data){
            console.log(data)
            var text = "";
            for (x in data) {
                console.log(x)
                text +="<div class='itemDivClass' id=\"itemDiv" + data[x]['id'] + "\">"
                text +="<label aria-label=\"ID\">ID:              </label>"+data[x]['id'] + "<br>"
                text +="<label aria-label=\"Name\">NAAM:            </label>"+data[x]['name'] + "<br>"
                text +="<label aria-label=\"Description\">OMSCHRIJVING:    </label>"+data[x]['description'] + "<br>"
                text +="<label aria-label=\"Price\">PRIJS:           </label>"+data[x]['price'] + "<br>"
                text +="<label aria-label=\"Amount\" for=\'INPUT"+data[x]['id']+ "\'>HOEVEELHEID:" +  data[x]['quantity'] +"   </label> <br>"
                text +="<input aria-label=\"Input amount\" id=\"INPUT"+data[x]['id'] +"\" placeholder=\"Hoeveel wilt u hebben?\" type=\"number\">"
                text +="<br>"
                text +="</div>"
                text +="<br>"
            }
            document.getElementById('itemName').innerHTML = text
            if(!window.location.hash) {
                //setting window location
                window.location = window.location + '#loaded';
                //using reload() method to reload web page
                window.location.reload();
            }
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

    fetch('/htmlStorage/updateAmounts', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
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
    fetch('/htmlStorage/deleteItem', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        },
        body: JSON.stringify(item),
    })

    var el = document.getElementById('itemDiv' + id);
    el.remove(); // Removes the div with the 'div-02' id
}


var emailText = "";
function generateList(){
    fetch('/htmlStorage/getItemData', {
        method: 'GET', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        },
    })
        .then(response => response.json())
        .then(function(data){
            console.log(data)
            var text = "";
            let contacts = new Map()
            for (x in data){
                console.log()
                var input= document.getElementById('INPUT'+data[x]['id']).value
                if (input != null && input != "") {
                    var berekening = input - data[x]['quantity']
                    contacts.set(data[x]['name'], berekening)
                }
            }
            var text3= "";
            text3 += "<h1>Boodschappenlijst: </h1>"
            for (const entry of contacts.entries()) {
                console.log(entry[1])
                if(entry[1] < 0){
                    text3 += "Product: " + entry[0]+ " " + ", Hoeveelheid: 0, u heeft genoeg van dit product <br>"
                    emailText += "Product: " + entry[0]+ " " + ", Hoeveelheid: 0, u heeft genoeg van dit product \n"
                } else if(entry[1] >= 0){
                    text3 += "Product: " + entry[0]+ " " + ", Hoeveelheid: "+ entry[1] +" <br>"
                    emailText += "Product: " + entry[0]+ " " + ", Hoeveelheid: "+ entry[1] +" \n"
                } else{
                    text3 += "Product: " + entry[0]+ " " + ", U heeft niks ingevuld voor dit product! <br>"
                    emailText += "Product: " + entry[0]+ " " + ", Hoeveelheid: "+ entry[1] +" \n"
                }
            }
            text3 += "<h3>Voer uw email in als u dit lijstje naar u verzonden wilt krijgen: </h3>"

            document.getElementById('shoppinglistInputEmail').style.display = "block";
            document.getElementById('sendEmailButton').style.display = "block";

            document.getElementById('shoppinglist').innerHTML = text3

        })
}

function sendEmail() {
    let email = document.getElementById('shoppinglistInputEmail').value
    Email.send({
        Host: "smtp.gmail.com",
        Username: "hvt.ipass@gmail.com",
        Password: "hvtipass2021",
        To: email,
        From: "hvt.ipass@gmail.com",
        Subject: "Boodschappenlijstje",
        Body: emailText,
    })
        .then(function (message) {
            alert("mail sent successfully")
        });
}
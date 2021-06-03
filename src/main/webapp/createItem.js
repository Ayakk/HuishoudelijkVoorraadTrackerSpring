document.addEventListener("DOMContentLoaded", () =>{
    getAllItems()
})


function getAllItems(){
    fetch('http://localhost:8080/createItem/getAllItems', {
        method: 'GET', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(function(data){
            var text = "";
            for (x in data){
                text += JSON.stringify(data[x]) + "<br>"
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
    fetch('http://localhost:8080/createItem/postItem', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(item),
    })
}
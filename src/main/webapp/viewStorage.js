// document.addEventListener("DOMContentLoaded", () =>{
//     let saveButton = document.querySelector('#saveButton')
//     saveButton.addEventListener('click', saveButtonFunc)
//     let deleteButton = document.querySelector('#deleteButton')
//     deleteButton.addEventListener('click', deleteButtonFunc)
//
// })



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
        productID: id,
        amount: inputValue,
    }

    fetch('http://localhost:8080/viewStorage', {
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



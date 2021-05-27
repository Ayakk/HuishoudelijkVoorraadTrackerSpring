document.addEventListener("DOMContentLoaded", () =>{
    let saveButton = document.querySelector('#saveButton')
    saveButton.addEventListener('click', saveButtonFunc)
    let deleteButton = document.querySelector('#deleteButton')
    deleteButton.addEventListener('click', deleteButtonFunc)

})



function saveButtonFunc(){
    var inputValue= document.getElementById('amountInput').value

    const amount = {
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

function deleteButtonFunc(){

}



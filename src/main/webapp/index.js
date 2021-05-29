document.addEventListener("DOMContentLoaded", () =>{
    let submitButton = document.querySelector('#submitButton')
    //luistert naar kliks
    submitButton.addEventListener('click', loginSubmit)

    let registerButton = document.querySelector('#registerButton')
    //luistert naar kliks
    registerButton.addEventListener('click', registerButtonClick)
})


function registerButtonClick(){
    window.location = "http://localhost:8080/register"
}

function loginSubmit() {
    console.log("test")
    const queryString = window.location.search;
    console.log(queryString);
    const urlParams = new URLSearchParams(queryString);
    const usernameVal = urlParams.get('username')
    const passwordVal = urlParams.get('password')

    const user = {
        username: usernameVal,
        password: passwordVal
    }

    fetch('http://localhost:8080/index.html', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
    })
}
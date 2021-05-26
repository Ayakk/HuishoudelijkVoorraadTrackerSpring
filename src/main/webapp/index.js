document.addEventListener("DOMContentLoaded", () =>{
    let submitButton = document.querySelector('#submitButton')
    //luistert naar kliks
    submitButton.addEventListener('click', loginSubmit)
})

function loginSubmit() {
    const queryString = window.location.search;
    console.log(queryString);
    const urlParams = new URLSearchParams(queryString);
    const usernameVal = urlParams.get('username')
    const passwordVal = urlParams.get('password')

    const user = {
        username: usernameVal,
        password: passwordVal
    }

    fetch('http://localhost:8080/login/'+usernameVal+"/"+passwordVal, {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
    })
}
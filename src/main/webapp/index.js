function loginSubmit() {
    console.log("loginsubmit() FUNCTION CALL")
    var usernameVal= document.getElementById('username').value
    var passwordVal= document.getElementById('password').value
    console.log(usernameVal)
    console.log(passwordVal)


    const user = {
        username: usernameVal,
        password: passwordVal
    }

    fetch('http://localhost:8080/loginHTML', {
        method: 'POST', // or 'PUT'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
    })
}
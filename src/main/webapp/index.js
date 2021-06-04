function goToRegisterPage(){
    window.location = "register.html"
}

// function login(){
//     var usernameVal= document.getElementById('username').value
//     var passwordVal= document.getElementById('password').value
//
//     const user = {
//         username: usernameVal,
//         password: passwordVal
//     }
//
//     fetch('/authentication', {
//         method: 'POST', // or 'PUT'
//         headers: {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify(user),
//     }).then(function (response){
//         if (response.ok) return response.json();
//         else throw "Wrong username/password"
//     })
//         .then(
//             myJson => window.sessionStorage.setItem("myJWT", myJson.JWT)
//         )
//         .catch(
//             error => console.log(error)
//         )
//
//     fetch('/loginHTML', {
//         method: 'POST', // or 'PUT'
//         headers: {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify(user),
//     })
// }
//
// function loginSubmit() {
//     console.log("loginsubmit() FUNCTION CALL")
//     var usernameVal= document.getElementById('username').value
//     var passwordVal= document.getElementById('password').value
//     console.log(usernameVal)
//     console.log(passwordVal)
//
//
//     const user = {
//         username: usernameVal,
//         password: passwordVal
//     }
//
//     fetch('/loginHTML', {
//         method: 'POST', // or 'PUT'
//         headers: {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify(user),
//     })
// }
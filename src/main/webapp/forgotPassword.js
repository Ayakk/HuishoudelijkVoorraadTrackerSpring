function changePasswordFunc(){
    const usernameVal = document.getElementById('username').value
    const passwordVal = document.getElementById('password').value

    if (!usernameVal || !passwordVal){
        alert("Vul de velden in!")
    }else{
        const user = {
            username: usernameVal,
            password: passwordVal
        }

        console.log(JSON.stringify(user))

        fetch('/auth/changepwd', {
            method: 'POST', // or 'PUT'
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        })
            .then(response => response.json())
            .then(function(data){
                console.log(data)
                window.location = "index.html"
            }).catch(error => console.log(error))
    }
}
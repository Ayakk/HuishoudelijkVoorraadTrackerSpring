function login() {
    const usernameVal = document.getElementById('username').value
    const passwordVal = document.getElementById('password').value

    const user = {
        username: usernameVal,
        password: passwordVal
    }

    console.log(JSON.stringify(user))

    if(usernameVal && passwordVal){
        fetch('/auth/signin', {
            method: 'POST', // or 'PUT'
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        })
            .then(response => response.json())
            .then(function(data){
                console.log(data.status)
                if(data.status == undefined){
                    sessionStorage.setItem('myJWT', data['accessToken'])
                    sessionStorage.setItem('products', data['products'])
                    sessionStorage.setItem('userID', data['id'])
                    window.location = "viewStorage.html"
                } else {
                    console.log('Er ging iets fout')
                }
            }).catch(error => console.log(error))
    }
}

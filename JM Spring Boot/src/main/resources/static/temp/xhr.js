
function sendUser(firstName, lastName, age, email, password, role) {
    let xhr = new XMLHttpRequest();
    let body = {
        "firstName": firstName,
        "lastName": lastName,
        "age": age,
        "email": email,
        "password": password,
        "role": role
    };
    xhr.open('POST', '/admin/save');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function () {
        let response = JSON.parse(xhr.response);
        let usersListHtml = document.getElementById('users-list');
        let newUsersList = '<thead><tr><th>Id</th>\n' +
            '<th>FirstName</th>\n' +
            '<th>LastName</th>\n' +
            '<th>Age</th>\n' +
            '<th>Email</th>\n' +
            '<th>Password</th>\n' +
            '<th>Role</th>\n' +
            '<th>Edit</th>\n' +
            '<th>Delete</th>\n' +
            '</tr></thead>';
        for (let i = 0; i < response.length; i++) {
            let user = response[i];
            newUsersList += '<tr>';
            newUsersList += '<td>' + user["id"] + '</td>'
            newUsersList += '<td>' + user["firstName"] + '</td>';
            newUsersList += '<td>' + user["lastName"] + '</td>';
            newUsersList += '<td>' + user["age"] + '</td>';
            newUsersList += '<td>' + user["email"] + '</td>';
            newUsersList += '<td>' + user["password"] + '</td>';
            newUsersList += '<td>' + user["role"] + '</td>';
            newUsersList += '</tr>';
        }
        usersListHtml.innerHTML = newUsersList
    }
    xhr.send(JSON.stringify(body));
}
let toHtml = ((user)=> {
    let newUsersList = ''
    newUsersList += '<tr>';
    newUsersList += '<td>' + user["id"] + '</td>'
    newUsersList += '<td>' + user["firstName"] + '</td>';
    newUsersList += '<td>' + user["lastName"] + '</td>';
    newUsersList += '<td>' + user["age"] + '</td>';
    newUsersList += '<td>' + user["email"] + '</td>';
    newUsersList += '<td>' + user["password"] + '</td>';
    let roles = ''
    for (let j = 0; j<user.roles.length; j++) {
        roles += (user.roles[j].role + ', ').slice(5)
    }
    roles = roles.slice(0, -2)
    newUsersList += '<td>' + roles + '</td>';
    newUsersList += '<td><div class="text-left">\n' +
        '<button class="btn btn-primary"\n' +
        'data-btn="edit" data-id="'+user.id+'"\n' +
        'type="button">Edit</button>\n' +
        '</div></td>';
    newUsersList += '<td><div class="text-left">\n' +
        '<button class="btn btn-danger"\n' +
        'data-btn="delete" data-id="'+user.id+'"\n' +
        'type="button">Delete</button>\n' +
        '</div></td>';
    newUsersList += '</tr>';
    return newUsersList
})

function sendUser(firstName, lastName, age, email, password) {
    const headers = {
        'Content-Type': 'application/json'
    }
    let body = {
        "firstName": firstName,
        "lastName": lastName,
        "age": age,
        "email": email,
        "password": password,
        // "roles": roles
    };
    return fetch('/admin/save', {
        method: 'POST',
        body: JSON.stringify(body),
        headers: headers
    }).then(response=>response.json()).then(data=>{
        let newUsersList = ''
        for (let i = 0; i < data.length; i++) {
            newUsersList += toHtml(data[i])

        }
        // usersListHtml.innerHTML = newUsersList
        document.querySelector('#users-list tbody').innerHTML = newUsersList

        //Включает или отключает класс
        // const elem = document.querySelector("#nav-tabContent");
        // console.log(elem.classList)
        // elem.element.classList.toggle('active')
        //
        // $('#nav-tabContent a[href="#nav-usersTable"]').tab('show')
    })
}



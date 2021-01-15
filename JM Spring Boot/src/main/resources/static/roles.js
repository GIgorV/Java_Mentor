
function loadRoles() {
    fetch('admin/findRoles').then(response=>response.json()).then(data=>{
        const select = document.getElementById('roleSelect');
        for(let i=0; i<data.length; i++){
            let options = data[i].role
            let element = document.createElement('option')
            element.textContent = options
            element.value = options
            element.id = 'i'
            select.appendChild(element)
        }
    })

}
loadRoles()



function loadRolesFromUser(roles) {

        console.log(roles)
        const select = document.getElementById('roleEditSelect');
        for(let i=0; i<roles.length; i++){
            let options = roles[i].role
            let element = document.createElement('option')
            element.textContent = options
            element.value = options
            element.id = 'i'
            select.appendChild(element)
        }


}
$('#editModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var id = button.data('id')
    fetch("http://localhost:8080/admin/users/" + id)
        .then(response => response.json())
        .then(user => {
            let myModalBody = ""
            myModalBody += ('<div class="form-group">' +
                '<label for="idEdit">ID</label>' +
                '<input type="text" class="form-control" id="idEdit"name="id" value="'+ user.id +'" disabled/>' +
                '</div>')
            myModalBody += ('<div class="form-group">' +
                '<label for="firstNameEdit">' + "First name" + '</label>' +
                '<input type="text" class="form-control" id="firstNameEdit" value="'+ user.firstName +'" name="firstName"/>' +
                '</div>')
            myModalBody +=('<div class="form-group">' +
                '<label for="lastNameEdit">' + "Last name" + '</label>' +
                '<input type="text" class="form-control" id="lastNameEdit" value="'+ user.lastName +'" name="lastName"/>' +
                '</div>')
            myModalBody +=('<div class="form-group">' +
                '<label for="ageEdit">' + "Age" + '</label>' +
                '<input type="text" class="form-control" id="ageEdit" value="'+ user.age +'" name="age"/>' +
                '</div>')
            myModalBody += ('<div class="form-group">' +
                '<label for="emailEdit">' + "Email" + '</label>' +
                '<input type="text" class="form-control" id="emailEdit" value="'+ user.email +'" name="email"/>' +
                '</div>')
            myModalBody += ('<div class="form-group">' +
                '<label for="passwordEdit">' + "Password" + '</label>' +
                '<input type="text" class="form-control" id="passwordEdit" value="'+ user.password +'" name="password" value=""/>' +
                '</div>')
            myModalBody += ('<div class="form-group">' +
                '<select size="2" multiple required class="form-control"id="roleSelectEdit" name="roles">' +
                '<option>' +
                '</option>' +
                '</select>' +
                '</div>')
            myModalBody +=('<div class="modal-footer">' +
                '<button type="button" class="btn btn-secondary"data-dismiss="modal">' + "Close" +
                '</button>' +
                '<button type="submit" class="btn btn-primary">' + "Edit" + '</button>' +
                '</div>')
            $('#editModalBody').append(myModalBody)
        })
})
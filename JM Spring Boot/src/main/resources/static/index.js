const $ = {}

Element.prototype.appendAfter = function (element) {
    element.parentNode.insertBefore(this, element.nextSibling);
}

function noop() {
}

function _createModalFooter(buttons = []) {
    if (buttons.length === 0) {
        return document.createElement('div')
    }
    const wrap = document.createElement('div')
    wrap.classList.add('modal-footer')

    buttons.forEach(btn => {
        const $btn = document.createElement('button')
        $btn.textContent = btn.text
        $btn.classList.add('btn')
        $btn.classList.add(`btn-${btn.type || 'secondary'}`)
        $btn.onclick = btn.handler || noop
        wrap.appendChild($btn)
    })
    return wrap
}

function _createModal(options) {
    const DEFAULT_WIDTH = '600px'
    const modal = document.createElement('div')
    modal.classList.add('vmodal')
    modal.insertAdjacentHTML('afterbegin', `
    <div class="modal-overlay" data-close="true">
        <div class="modal-window" style="width: ${options.width || DEFAULT_WIDTH}">
            <div class="modal-header">
                <span class="modal-title">${options.title || 'Окно'}</span>
                 ${options.closable ? `<span class="modal-close" data-close="true">&times;</span>` : ''}
            </div>
            <div class="modal-body" data-content>
                ${options.content || ''}
            </div>
        </div>
    </div>
    `)

    const footer = _createModalFooter(options.footerButtons)
    footer.appendAfter(modal.querySelector('[data-content]'))

    document.body.appendChild(modal)
    return modal
}

$.modal = function (options) {
    const ANIMATION_SPEED = 200
    const $modal = _createModal(options)
    let closing = false
    let destroyed = false
    const modal = {
        open() {
            if (destroyed) {
                return console.log('Modal is destroyed')
            }
            !closing && $modal.classList.add('open')
        },
        close() {
            closing = true
            $modal.classList.remove('open')
            $modal.classList.add('hide')
            setTimeout(() => {
                $modal.classList.remove('hide')
                closing = false
                if (typeof options.onClose === 'function') {
                    options.onClose()
                }
            }, ANIMATION_SPEED)
        }
    }
    const listener = event => {
        if (event.target.dataset.close) {
            modal.close()
        }
    }
    $modal.addEventListener('click', listener)

    return Object.assign(modal, {
        destroy() {
            $modal.parentNode.removeChild($modal)
            $modal.removeEventListener('click', listener)
            destroyed = true
        },
        setContent(html) {
            $modal.querySelector('[data-content]').innerHTML = html
        }
    })
}

let editBody = {}
let delBody

document.addEventListener('click', event => {
    event.preventDefault()
    const btnType = event.target.dataset.btn
    const id = +event.target.dataset.id
    if (btnType === 'edit') {
        console.log(id)
        fetch('admin/findOne/' + id).then(response => response.json()).then(user => {
            // loadRolesFromUser(user.roles)

            function loadEditRoles() {
                fetch('admin/findRoles').then(response=>response.json()).then(data=>{
                    const select = document.getElementById('roleEditSelect');
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
            loadEditRoles()

            editUser.setContent(`
<div class="form-group text-center"><strong>ID</strong><br>
    <div class="text center">
    <input type="text" class="form-control" id="eId" name="id" value=${user.id}>
</div></div>
<div class="form-group text-center"><strong>FirstName</strong><br>
    <div class="text center">
    <input type="text" class="form-control" id="eFirstName" name="firstName" value=${user.firstName}>
</div></div>
<div class="form-group text-center"><strong>LastName</strong><br>
    <div class="text center">
    <input type="text" class="form-control" id="eLastName" name="lastName" value=${user.lastName}>
</div></div>
<div class="form-group text-center"><strong>Age</strong><br>
    <div class="text center">
    <input type="text" class="form-control" id="eAge" name="age" value=${user.age}>
</div></div>
<div class="form-group text-center"><strong>Email</strong><br>
    <div class="text center">
    <input type="text" class="form-control" id="eEmail" name="email" value=${user.email}>
</div></div>
<div class="form-group text-center"><strong>Password</strong><br>
    <div class="text center">
    <input type="text" class="form-control" id="ePassword" name="password" value=${user.password}>
</div></div>
</div class="form-group text-center col-sm-10 offset-sm-1"><strong>Role</strong>
    <div>
        <label for="roleEditSelect"></label>
        <select multiple class="form-control" id="roleEditSelect" aria-multiselectable="true">
        </select>
    </div>
</div>                             
        `)
        })
        editUser.open()

    } else if (btnType === 'delete') {
        console.log(id)
        fetch('admin/findOne/' + id).then(response => response.json()).then(user => {
            console.log(user)
            delUser.setContent(`
<div class="form-group text-center"><strong>ID</strong><br>
    <div class="text center">
    <input type="text" class="form-control" id="dId" name="id" value=${user.id}>
</div></div>
<div class="form-group text-center"><strong>FirstName</strong><br>
    <div class="text center">
    <input type="text" class="form-control" id="dFirstName" name="firstName" value=${user.firstName}>
</div></div>
<div class="form-group text-center"><strong>LastName</strong><br>
    <div class="text center">
    <input type="text" class="form-control" id="dLastName" name="lastName" value=${user.lastName}>
</div></div>
<div class="form-group text-center"><strong>Age</strong><br>
    <div class="text center">
    <input type="text" class="form-control" id="dAge" name="age" value=${user.age}>
</div></div>
<div class="form-group text-center"><strong>Email</strong><br>
    <div class="text center">
    <input type="text" class="form-control" id="dEmail" name="email" value=${user.email}>
</div></div>
<div class="form-group text-center"><strong>Password</strong><br>
    <div class="text center">
    <input type="text" class="form-control" id="dPassword" name="password" value=${user.password}>
</div></div>
        `)
        })
        delUser.open()
        delBody = id
    }
})

const editUser = $.modal({
    title: 'Edit user',
    closable: true,
    width: '350px',
    footerButtons: [
        {
            text: 'Обновить', type: 'primary', handler() {
                const select = document.getElementById("roleEditSelect")
                let count=0
                let name
                let rolesEdit = []
                for (let j = 0; j < document.getElementById("roleEditSelect").options.length; j++) {
                    if (select.options[j].selected===true) {
                        name = select.options[j].value
                        if (name === 'ROLE_USER') {
                            rolesEdit = [{
                                id: 2,
                                role: 'ROLE_USER',
                                users: null,
                                authority: 'ROLE_USER'
                            }]
                            count++
                        } else if (name === 'ROLE_ADMIN') {
                            rolesEdit = [{
                                id: 1,
                                role: 'ROLE_ADMIN',
                                users: null,
                                authority: 'ROLE_ADMIN'
                            }]
                            count++
                        }
                    }
                }
                if(count===2){
                    rolesEdit=[
                        {
                            id: 1,
                            role: 'ROLE_ADMIN',
                            users: null,
                            authority: 'ROLE_ADMIN'
                        },
                        {
                            id: 2,
                            role: 'ROLE_USER',
                            users: null,
                            authority: 'ROLE_USER'
                        },
                    ]
                }
                editBody =
                    {
                        id: document.getElementById('eId').value,
                        firstName: document.getElementById('eFirstName').value,
                        lastName: document.getElementById('eLastName').value,
                        age: document.getElementById('eAge').value,
                        email: document.getElementById('eEmail').value,
                        password: document.getElementById('ePassword').value,
                        roles: rolesEdit,
                    }

                console.log(editBody)
                const headers = {
                    'Content-Type': 'application/json'
                }
                fetch('/admin/update', {
                    method: 'POST',
                    body: JSON.stringify(editBody),
                    headers: headers
                }).then(response => response.json()).then(data => {
                    console.log(data)
                    let newUsersList = ''
                    for (let i = 0; i < data.length; i++) {
                        newUsersList += toViewAllUsers(data[i])
                    }
                    document.querySelector('#users-list tbody').innerHTML = newUsersList
                })
                editUser.close()
            }
        },
        {
            text: 'Отменить', type: 'secondary', handler() {
                editUser.close()
            }
        }
    ]
})

const delUser = $.modal({
    title: 'Delete user',
    closable: true,
    width: '350px',
    footerButtons: [
        {
            text: 'Удалить', type: 'danger', handler() {
                console.log(delBody)
                fetch('/admin/delete/' + delBody).then(response => response.json()).then(data => {
                    let newUsersList = ''
                    for (let i = 0; i < data.length; i++) {
                        newUsersList += toViewAllUsers(data[i])
                    }
                    document.querySelector('#users-list tbody').innerHTML = newUsersList
                })
                delUser.close()
            }
        },
        {
            text: 'Отменить', type: 'secondary', handler() {
                delUser.close()
            }
        }
    ]
})


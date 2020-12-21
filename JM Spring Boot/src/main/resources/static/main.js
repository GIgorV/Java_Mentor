// window.onload = function () {
$(document).ready(function () {
    $('.table .eBtn').on('click', function (event) {
        event.preventDefault()
        const href = $(this).attr('href')
        $.isFunction(href, function (user, status) {
        // $.get(href, function (user, status) {

            $('.myForm #firstName').val(user.firstName)
            $('.myForm #lastName').val(user.lastName)
            $('.myForm #age').val(user.age)
            $('.myForm #email').val(user.age)
            $('.myForm #password').val(user.age)

        })
        $('#exampleModal').modal()
    })

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault()
        const href = $(this).attr('href')
        $('#myModal #delRef').attr('href', href)
        $('#myModal').modal()
    })
})
// const xhr = new XMLHttpRequest()
// xhr.open('GET', '/admin', true)
// xhr.responseType='json'
// xhr.onload=()=>{
//     console.log(xhr.response)
// }
// xhr.onerror=()=>{
//     console.log(xhr.response)
// }
// xhr.send()

function sendRequest(method, url, body=null) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest()
        xhr.open(method, url)
        xhr.responseType = 'json'
        xhr.setRequestHeader('Content-Type', 'application/json')
        xhr.onload = () => {
            if (xhr.status >= 400) {
                reject(xhr.response)
            } else {
                resolve(xhr.response)
            }
        }
        xhr.onerror = () => {
            reject(xhr.response)
        }
        xhr.send(JSON.stringify(body))
    })
}

sendRequest('GET', '/admin')
    .then(data => console.log(data))
    .catch(err => console.log(err))

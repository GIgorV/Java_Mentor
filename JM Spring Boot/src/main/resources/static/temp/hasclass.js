//
// function hasClass(elem, className) {
//     return new RegExp(' ' + className + ' ').test(' ' + elem.className + ' ');
// }
//
// // https://ultimatecourses.com/blog/javascript-hasclass-addclass-removeclass-toggleclass
// // if (hasClass(document.documentElement, 'ie6')) {
// //     // Do something crazy
// // } else {
// //     // Phew
// // }
//
// function addClass(elem, className) {
//     if (!hasClass(elem, className)) {
//         elem.className += ' ' + className;
//     }
// }
//
// // document.getElementById('myButton').onclick = function() {
// //     addClass(document.documentElement, 'some-class');
// // }
//
// function removeClass(elem, className) {
//     var newClass = ' ' + elem.className.replace( /[\t\r\n]/g, ' ') + ' ';
//     if (hasClass(elem, className)) {
//         while (newClass.indexOf(' ' + className + ' ') >= 0 ) {
//             newClass = newClass.replace(' ' + className + ' ', ' ');
//         }
//         elem.className = newClass.replace(/^\s+|\s+$/g, '');
//     }
// }
//
// // document.getElementById('myButton').onclick = function() {
// //     removeClass(document.documentElement, 'some-class');
// // }
//
// function toggleClass(elem, className) {
//     var newClass = ' ' + elem.className.replace( /[\t\r\n]/g, ' ' ) + ' ';
//     if (hasClass(elem, className)) {
//         while (newClass.indexOf(' ' + className + ' ') >= 0 ) {
//             newClass = newClass.replace( ' ' + className + ' ' , ' ' );
//         }
//         elem.className = newClass.replace(/^\s+|\s+$/g, '');
//     } else {
//         elem.className += ' ' + className;
//     }
// }

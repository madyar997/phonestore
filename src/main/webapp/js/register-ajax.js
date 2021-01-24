var check = function() {
    if (document.getElementById('password').value ==
        document.getElementById('confirm_password').value) {
        document.getElementById('error').style.color = 'green';
        document.getElementById('error').innerHTML = '';
        document.getElementById("submit").disabled = false;
    } else {
        document.getElementById('error').style.color = 'red';
        document.getElementById('error').innerHTML = '\u041f\u0430\u0440\u043e\u043b\u0438\u0020\u043d\u0435\u0020\u0441\u043e\u0432\u043f\u0430\u0434\u0430\u044e\u0442';
        document.getElementById("submit").disabled = true;
    }
}
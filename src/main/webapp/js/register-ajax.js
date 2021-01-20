    var value_input1 = $(".password").val(); // Получаем содержимое 1-го поля
    var value_input2 = $(".confirm_password").val(); // Получаем содержимое 2-го поля

    if(value_input1 !== value_input2) { // Условие, если поля не совпадают

        $(".error").html("Пароли не совпадают!"); // Выводим сообщение
        $("#submit").attr("disabled", "disabled"); // Запрещаем отправку формы

    } else { // Условие, если поля совпадают

        $("#submit").removeAttr("disabled");  // Разрешаем отправку формы
        $(".error").html(""); // Скрываем сообщение

    }

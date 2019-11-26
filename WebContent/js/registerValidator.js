window.onload = function () {
    document.getElementById("name").addEventListener("blur", function telfVal() {

        var regex = /^[a-z0-9_-]{8,15}$/;

        if (document.getElementById("name").value == " " || document.getElementById("name").value.length == 0 || regex.test(document.getElementById("name").value) == false) {
            document.getElementById("name").style = "background-color:red"

        } else {

            document.getElementById("name").style = "background-color:green";
        }
    });

    document.getElementById("pass").addEventListener("blur", function telfVal() {

        var regex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$*%^&+=])(?=\\S+$).{8,}$/;

        if (document.getElementById("pass").value == " " || document.getElementById("pass").value.length == 0 || regex.test(document.getElementById("pass").value) == false) {
            document.getElementById("pass").style = "background-color:red"

        } else {

            document.getElementById("pass").style = "background-color:green";
        }
    });

    document.getElementById("fullname").addEventListener("blur", function telfVal() {

        var regex = /^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\']+[\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\'])+[\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\'])?$/;

        if (document.getElementById("fullname").value == " " || document.getElementById("fullname").value.length == 0 || regex.test(document.getElementById("fullname").value) == false) {
            document.getElementById("fullname").style = "background-color:red"

        } else {

            document.getElementById("fullname").style = "background-color:green";
        }
    });

    document.getElementById("address").addEventListener("blur", function telfVal() {

        var regex = /^[a-zA-Z0-9\s,'-]*$/;

        if (document.getElementById("address").value == " " || document.getElementById("address").value.length == 0 || regex.test(document.getElementById("address").value) == false) {
            document.getElementById("address").style = "background-color:red"

        } else {

            document.getElementById("address").style = "background-color:green";
        }
    });

    document.getElementById("email").addEventListener("blur", function telfVal() {

        var regex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$/;

        if (document.getElementById("email").value == " " || document.getElementById("email").value.length == 0 || regex.test(document.getElementById("email").value) == false) {
            document.getElementById("email").style = "background-color:red"

        } else {

            document.getElementById("email").style = "background-color:green";
        }
    });

}
var status = document.getElementById("status").value;
var message = document.getElementById("message").value;

if (status === "failed") {
    swal("Something went wrong!", message, "error")
        .then(function () {
            history.replaceState(null, null, window.location.pathname);
        });
}
if (status === "success") {
    swal("Congratulations!", message, "success")
        .then(function () {
            history.replaceState(null, null, window.location.pathname);
            location.reload();
        });
}


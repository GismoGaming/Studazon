let oldPassword = document.getElementById("old_password")
let newPassword = document.getElementById("new_password")
let submitButton = document.getElementById("changesSubmit")
oldPassword.addEventListener("input", function()
{
    updateButton();

});

newPassword.addEventListener("input", function()
{
    updateButton();
});

function updateButton()
{
    if (oldPassword.value != "")
    {
        if (newPassword.value == "")
        {
            submitButton.disabled = true;
        }
        else
        {
            submitButton.disabled = false;
        }
    }
    else
    {
        submitButton.disabled = false;
    }
}

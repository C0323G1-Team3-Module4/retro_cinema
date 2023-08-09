//Username and pass must be input:
signInButton.addEventListener("click", () => {
    const username = document.getElementById("form2Example8").value;
    console.log(username)
    const password = document.getElementById("form2Example7").value;
    console.log(password)
    const errorMessage = document.querySelector(".error-message");

    if (!username || !password) {
        errorMessage.textContent = "Username and password are required.";
        return;
    }

    errorMessage.textContent = ""; // Clear any previous error message
    container.classList.remove("right-panel-active");
});
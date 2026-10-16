const form = document.getElementById("login-form");

form.addEventListener("submit", function (event) {
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    if (email === "" && password === "") {
        event.preventDefault();
        alert("Please enter your email and password.");
    }
    else if (email === "") {
        event.preventDefault();
        alert("Please enter your email.");
    }
    else if (password === "") {
        event.preventDefault();
        alert("Please enter your password.");
    }

    // If both fields are filled,
    // DO NOT call event.preventDefault().
    // The form will be submitted to LoginServlet.
});
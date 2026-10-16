document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("register-form");

    form.addEventListener("submit", (event) => {

        const name = form.name.value.trim();
        const email = form.email.value.trim();
        const password = form.password.value.trim();

        if (name === "") {
            alert("Please enter your name.");
            event.preventDefault();
            return;
        }

        if (email === "") {
            alert("Please enter your email.");
            event.preventDefault();
            return;
        }

        if (!email.includes("@")) {
            alert("Please enter a valid email.");
            event.preventDefault();
            return;
        }

        if (password.length < 6) {
            alert("Password must be at least 6 characters.");
            event.preventDefault();
            return;
        }

    });

});
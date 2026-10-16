console.log("dashboard.js loaded");
fetch("/backend/user")
    .then(response => {

        if (!response.ok) {
            window.location.href = "login.html";
            return;
        }

        return response.json();
    })
    .then(user => {

        document.getElementById("username").textContent = user.name;
        document.getElementById("email").textContent = user.email;
        document.getElementById("xp").textContent = user.xp;
document.getElementById("level").textContent = user.level;
document.getElementById("streak").textContent = user.streak + " Days";
document.getElementById("progress").textContent = user.progress + "%";

    })
    .catch(error => {
        console.error("Error:", error);
    });
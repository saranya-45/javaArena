async function loadNotes() {

    try {

        const response = await fetch("/backend/learn");

        if (!response.ok) {
            throw new Error("Failed to load notes");
        }

        const topics = await response.json();

        const container = document.getElementById("notesContainer");

        container.innerHTML = "";

        topics.forEach(topic => {

            const card = document.createElement("div");

            card.className = "note-card";

            card.innerHTML = `
                <h2>${topic.title}</h2>

                <p>${topic.description}</p>

                <span class="difficulty">
                    ${topic.difficulty}
                </span>

                <button class="resource-btn"
                        onclick="openResource('${topic.externalUrl}')">
                    Read Resource →
                </button>
            `;

            container.appendChild(card);

        });

    } catch (error) {

        console.error(error);

        document.getElementById("notesContainer").innerHTML =
            "<h2>Unable to load notes.</h2>";

    }

}

function openResource(url) {

    window.open(url, "_blank");

}

loadNotes();
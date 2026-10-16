async function loadTopics() {

    try {

        const response = await fetch("/backend/learn");

        if (!response.ok) {
            throw new Error("Failed to load topics");
        }

        const topics = await response.json();

        const container = document.getElementById("topicsContainer");

        container.innerHTML = "";

        topics.forEach(topic => {

            const card = document.createElement("div");

            card.className = "topic-card";

            card.innerHTML = `
                <h2>${topic.title}</h2>

                <p>${topic.description}</p>

                <div class="badges">

                    <span class="badge">${topic.difficulty}</span>

                    <span class="badge">${topic.xp} XP</span>

                </div>

                <button class="start-btn" onclick="startLearning(${topic.id})">
                    Start Learning
                </button>
            `;

            container.appendChild(card);

        });

    } catch (error) {

        console.error(error);

        document.getElementById("topicsContainer").innerHTML =
            "<h2>Unable to load topics.</h2>";

    }

}

loadTopics();
function startLearning(topicId) {
    window.location.href = `topic.html?id=${topicId}`;
}
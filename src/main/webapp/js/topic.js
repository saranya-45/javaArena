async function loadTopic() {

    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    try {

        const response = await fetch(`/backend/topic?id=${id}`);

        if (!response.ok) {
            throw new Error("Failed to load topic");
        }

        const topic = await response.json();

        document.getElementById("title").textContent = topic.title;
        document.getElementById("description").textContent = topic.description;

        document.getElementById("introduction").textContent = topic.introduction;
        document.getElementById("example").textContent = topic.example;
        document.getElementById("rules").textContent = topic.rules;
        document.getElementById("practice").textContent = topic.practice;

        const completeBtn = document.getElementById("completeBtn");

        console.log("Complete button found:", completeBtn);

        completeBtn.addEventListener("click", async function () {

            console.log("MARK AS COMPLETED CLICKED");

            try {

                const response = await fetch(
                    `/backend/complete-topic?topicId=${id}`,
                    {
                        method: "POST"
                    }
                );

                const result = await response.json();

                if (result.success) {

                    completeBtn.textContent = "✓ Completed";
                    completeBtn.disabled = true;

                    alert("Topic completed successfully!");

                } else {

                    completeBtn.textContent = "✓ Already Completed";
                    completeBtn.disabled = true;

                    alert("You have already completed this topic.");

                }

            } catch (error) {

                console.error(error);

                alert("Unable to complete topic.");

            }

        });

    } catch (error) {

        console.error(error);

        document.getElementById("title").textContent = "Error";
        document.getElementById("description").textContent = "";

        document.getElementById("introduction").textContent =
            "Unable to load lesson.";

        document.getElementById("example").textContent = "";
        document.getElementById("rules").textContent = "";
        document.getElementById("practice").textContent = "";

    }

}

loadTopic();
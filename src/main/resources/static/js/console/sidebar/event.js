document.addEventListener("DOMContentLoaded", function () {
    const expButton = document.getElementById("list-exp-button");
    const expSection = document.getElementById("list-exp");

    if (expButton && expSection) {
        expButton.addEventListener("click", function () {
            if (expSection.style.display === "none") {
                expSection.style.display = "";
            } else {
                expSection.style.display = "none";
            }
        });
    }

    const internButton = document.getElementById("list-intern-button");
    const internSection = document.getElementById("list-intern");

    if (internButton && internSection) {
        internButton.addEventListener("click", function () {
            if (internSection.style.display === "none") {
                internSection.style.display = "";
            } else {
                internSection.style.display = "none";
            }
        });
    }
    const serChButton = document.getElementById("ser-ch-button");
    const serChDiv = document.getElementById("ser-ch-div");

    if (serChButton && serChDiv) {
        serChButton.addEventListener("click", function (e) {
            if (serChDiv.style.display === "block") {
                serChDiv.style.display = "none";
            } else {
                serChDiv.style.display = "block";
            }
            e.stopPropagation();
        });

        document.addEventListener("click", function (e) {
            if (
                serChDiv.style.display === "block" &&
                !serChDiv.contains(e.target) &&
                e.target !== serChButton
            ) {
                serChDiv.style.display = "none";
            }
        });

        serChDiv.addEventListener("click", function (e) {
            e.stopPropagation();
        });
    }
});

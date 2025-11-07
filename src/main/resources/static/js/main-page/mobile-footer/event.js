document.querySelectorAll(".footer-list-section").forEach((section) => {

    if (window.location.href.includes(section.id)) {
        section.querySelector(
            ".footer-list-icon"
        )?.classList.add("active");
        section.querySelector(
            ".footer-list-text"
        )?.classList.add("active");
    }

});


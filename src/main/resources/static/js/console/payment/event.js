document.addEventListener("DOMContentLoaded", () => {
    const priceAll = document.querySelectorAll(".span-number");
    priceAll.forEach((price)=>{
        price.textContent = Number(price.textContent).toLocaleString();
    })
});
window.onload = function() {
    const expiredDiv = document.querySelectorAll(".expiredDiv");
    const hiddenBoolExpired = document.querySelectorAll('.isExpiredValue');

    hiddenBoolExpired.forEach((input, index) => {
        const shouldChangeColor = input.value === 'true';
        if (shouldChangeColor) {
            expiredDiv[index].style.border = "1.5px solid red";
        }
    });
}

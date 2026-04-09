const tbody = document.querySelector("tbody");
tbody.addEventListener("click", e=>{
	e.tartget.closest("tr");
});
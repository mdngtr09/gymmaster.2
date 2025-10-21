document.addEventListener("DOMContentLoaded", () => {
  const links = document.querySelectorAll("nav a");

  links.forEach(link => {
    link.addEventListener("mouseover", () => {
      link.style.transform = "scale(1.1)";
    });

    link.addEventListener("mouseout", () => {
      link.style.transform = "scale(1)";
    });
  });

  console.log("Página inicial carregada com sucesso!");
});

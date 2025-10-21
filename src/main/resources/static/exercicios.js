document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("#formExercicio");
  const tabela = document.querySelector("#tabelaExercicios tbody");
  const API_URL = "http://localhost:8081/api/exercicios";

  // Listar exercícios
  const listarExercicios = async () => {
    tabela.innerHTML = "";
    try {
      const res = await fetch(API_URL);
      if (!res.ok) throw new Error("Erro ao buscar exercícios");
      const exercicios = await res.json();

      exercicios.forEach(exercicio => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
          <td>${exercicio.id}</td>
          <td>${exercicio.nome}</td>
          <td>${exercicio.grupoMuscular}</td>
          <td>
            <button class="editar" data-id="${exercicio.id}">Editar</button>
            <button class="deletar" data-id="${exercicio.id}">Excluir</button>
          </td>
        `;
        tabela.appendChild(tr);
      });
    } catch (err) {
      console.error(err);
    }
  };

  // Salvar ou atualizar exercício
  const salvarExercicio = async (exercicio) => {
    try {
      const metodo = exercicio.id ? "PUT" : "POST";
      const url = exercicio.id ? `${API_URL}/${exercicio.id}` : API_URL;

      const res = await fetch(url, {
        method: metodo,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(exercicio)
      });

      if (!res.ok) throw new Error("Erro ao salvar exercício");

      form.reset();
      listarExercicios();
    } catch (err) {
      console.error(err);
    }
  };

  // Editar exercício
  tabela.addEventListener("click", async (e) => {
    if (e.target.classList.contains("editar")) {
      const id = e.target.dataset.id;
      try {
        const res = await fetch(`${API_URL}/${id}`);
        if (!res.ok) throw new Error("Erro ao buscar exercício");
        const ex = await res.json();

        document.querySelector("#exercicioId").value = ex.id;
        document.querySelector("#nomeExercicio").value = ex.nome;
        document.querySelector("#grupoMuscular").value = ex.grupoMuscular;
        document.querySelector("#descricaoExercicio").value = ex.descricao || "";
      } catch (err) {
        console.error(err);
      }
    }
  });

  // Deletar exercício
  tabela.addEventListener("click", async (e) => {
    if (e.target.classList.contains("deletar")) {
      const id = e.target.dataset.id;
      if (!confirm("Deseja realmente excluir este exercício?")) return;

      try {
        const res = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        if (!res.ok) throw new Error("Erro ao deletar exercício");
        listarExercicios();
      } catch (err) {
        console.error(err);
      }
    }
  });

  // Submissão do formulário
  form.addEventListener("submit", (e) => {
    e.preventDefault();
    const ex = {
      id: document.querySelector("#exercicioId").value || null,
      nome: document.querySelector("#nomeExercicio").value,
      grupoMuscular: document.querySelector("#grupoMuscular").value,
      descricao: document.querySelector("#descricaoExercicio").value
    };
    salvarExercicio(ex);
  });

  // Inicial
  listarExercicios();
});

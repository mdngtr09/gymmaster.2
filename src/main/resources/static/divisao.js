document.addEventListener("DOMContentLoaded", () => {
    const API_TREINOS = "http://localhost:8081/api/treinos";
    const API_ALUNOS = "http://localhost:8081/api/alunos";
    const API_EXERCICIOS = "http://localhost:8081/api/exercicios";

    const form = document.getElementById("formDivisao");
    const tabela = document.querySelector("#tabelaDivisoes tbody");
    const selectAluno = document.getElementById("alunoId");
    const selectExercicios = document.getElementById("exercicios");

    // Carregar alunos
    async function carregarAlunos() {
        try {
            const res = await fetch(API_ALUNOS);
            const alunos = await res.json();
            selectAluno.innerHTML = "<option value=''>Selecione um aluno</option>";
            alunos.forEach(a => {
                const opt = document.createElement("option");
                opt.value = a.id;
                opt.textContent = a.nome;
                selectAluno.appendChild(opt);
            });
        } catch (err) {
            console.error("Erro ao carregar alunos:", err);
        }
    }

    // Carregar exercícios
    async function carregarExercicios() {
        try {
            const res = await fetch(API_EXERCICIOS);
            const exercicios = await res.json();
            selectExercicios.innerHTML = "";
            exercicios.forEach(ex => {
                const opt = document.createElement("option");
                opt.value = ex.id;
                opt.textContent = ex.nome;
                selectExercicios.appendChild(opt);
            });
        } catch (err) {
            console.error("Erro ao carregar exercícios:", err);
        }
    }

    // Carregar divisões
    async function carregarDivisoes() {
        tabela.innerHTML = "";
        try {
            const res = await fetch(API_TREINOS);
            if (!res.ok) throw new Error("Resposta inválida");
            const divisoes = await res.json();

            divisoes.forEach(div => {
                const tr = document.createElement("tr");
                const nomesExercicios = div.exercicios?.map(e => e.nome).join(", ") || "-";
                tr.innerHTML = `
                    <td>${div.id}</td>
                    <td>${div.nome}</td>
                    <td>${div.aluno?.nome || "-"}</td>
                    <td>${nomesExercicios}</td>
                    <td>
                        <button class="editar" data-id="${div.id}">Editar</button>
                        <button class="excluir" data-id="${div.id}">Excluir</button>
                    </td>
                `;
                tabela.appendChild(tr);
            });
        } catch (err) {
            console.error("Erro ao carregar divisões:", err);
        }
    }

    // Salvar ou atualizar divisão
    async function salvarDivisao(id, nome, alunoId, exerciciosIds) {
        try {
            const metodo = id ? "PUT" : "POST";
            const url = id ? `${API_TREINOS}/${id}` : API_TREINOS;

            const res = await fetch(url, {
                method: metodo,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    nome,
                    alunoId: Number(alunoId),
                    exerciciosIds: exerciciosIds.map(Number)
                })
            });

            if (!res.ok) throw new Error("Erro ao salvar divisão");

            form.reset();
            carregarDivisoes();
        } catch (err) {
            console.error(err);
        }
    }

    // Clique em editar/excluir na tabela
    tabela.addEventListener("click", async (e) => {
        const id = e.target.dataset.id;
        if (e.target.classList.contains("editar")) {
            try {
                const res = await fetch(`${API_TREINOS}/${id}`);
                if (!res.ok) throw new Error("Divisão não encontrada");
                const div = await res.json();

                document.getElementById("divisaoId").value = div.id;
                document.getElementById("nomeDivisao").value = div.nome;
                selectAluno.value = div.aluno?.id || "";

                Array.from(selectExercicios.options).forEach(opt => {
                    opt.selected = div.exercicios?.some(e => e.id == opt.value) || false;
                });
            } catch (err) {
                console.error("Erro ao carregar divisão:", err);
            }
        } else if (e.target.classList.contains("excluir")) {
            if (!confirm("Deseja realmente excluir esta divisão?")) return;
            try {
                const res = await fetch(`${API_TREINOS}/${id}`, { method: "DELETE" });
                if (!res.ok) throw new Error("Erro ao deletar divisão");
                carregarDivisoes();
            } catch (err) {
                console.error(err);
            }
        }
    });

    // Submissão do formulário
    form.addEventListener("submit", (e) => {
        e.preventDefault();
        const id = document.getElementById("divisaoId").value || null;
        const nome = document.getElementById("nomeDivisao").value;
        const alunoId = selectAluno.value;
        const exerciciosIds = Array.from(selectExercicios.selectedOptions).map(opt => opt.value);

        salvarDivisao(id, nome, alunoId, exerciciosIds);
    });

    // Botão de limpar
    document.getElementById("resetDivisao").addEventListener("click", () => {
        form.reset();
        Array.from(selectExercicios.options).forEach(opt => opt.selected = false);
    });

    // Inicial
    carregarAlunos();
    carregarExercicios();
    carregarDivisoes();
});

document.addEventListener("DOMContentLoaded", () => {
    const API_PLANOS = "http://localhost:8081/api/planos";
    const API_ALUNOS = "http://localhost:8081/api/alunos";

    const form = document.getElementById("formPlano");
    const tabela = document.getElementById("tabelaPlanos").querySelector("tbody");
    const selectAluno = document.getElementById("alunoId");

    // Carregar alunos no select
    function carregarAlunos() {
        fetch(API_ALUNOS)
            .then(res => res.json())
            .then(alunos => {
                selectAluno.innerHTML = "<option value=''>Selecione um aluno</option>";
                alunos.forEach(a => {
                    const opt = document.createElement("option");
                    opt.value = a.id;
                    opt.textContent = a.nome;
                    selectAluno.appendChild(opt);
                });
            })
            .catch(err => console.error("Erro ao carregar alunos:", err));
    }

    // Carregar planos na tabela
    function carregarPlanos() {
        fetch(API_PLANOS)
            .then(res => res.json())
            .then(planos => {
                tabela.innerHTML = "";
                planos.forEach(plano => {
                    const tr = document.createElement("tr");
                    tr.innerHTML = `
                        <td>${plano.id}</td>
                        <td>${plano.nome}</td>
                        <td>${plano.preco}</td>
                        <td>${plano.aluno ? plano.aluno.nome : "-"}</td>
                        <td>
                            <button class="editar" data-id="${plano.id}">Editar</button>
                            <button class="excluir" data-id="${plano.id}">Excluir</button>
                        </td>
                    `;
                    tabela.appendChild(tr);
                });
            })
            .catch(err => console.error("Erro ao carregar planos:", err));
    }

    // Salvar ou atualizar plano
    form.addEventListener("submit", e => {
        e.preventDefault();

        const id = document.getElementById("planoId").value;
        const nome = document.getElementById("tipo").value;
        const preco = parseFloat(document.getElementById("valor").value);
        const alunoId = selectAluno.value;

        const plano = {
            nome,
            preco,
            aluno: alunoId ? { id: parseInt(alunoId) } : null
        };

        const metodo = id ? "PUT" : "POST";
        const url = id ? `${API_PLANOS}/${id}` : API_PLANOS;

        fetch(url, {
            method: metodo,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(plano)
        })
            .then(res => {
                if (!res.ok) throw new Error("Erro ao salvar plano");
                return res.json();
            })
            .then(() => {
                form.reset();
                document.getElementById("planoId").value = '';
                carregarPlanos();
            })
            .catch(err => console.error(err));
    });

    // Editar plano
    tabela.addEventListener("click", e => {
        if (e.target.classList.contains("editar")) {
            const id = e.target.dataset.id;
            fetch(`${API_PLANOS}/${id}`)
                .then(res => res.json())
                .then(plano => {
                    document.getElementById("planoId").value = plano.id;
                    document.getElementById("tipo").value = plano.nome;
                    document.getElementById("valor").value = plano.preco;
                    selectAluno.value = plano.aluno ? plano.aluno.id : "";
                })
                .catch(err => console.error("Erro ao carregar plano:", err));
        }
    });

    // Excluir plano
    tabela.addEventListener("click", e => {
        if (e.target.classList.contains("excluir")) {
            const id = e.target.dataset.id;
            if (confirm("Deseja realmente excluir este plano?")) {
                fetch(`${API_PLANOS}/${id}`, { method: "DELETE" })
                    .then(() => carregarPlanos())
                    .catch(err => console.error("Erro ao excluir plano:", err));
            }
        }
    });

    // Inicialização
    carregarAlunos();
    carregarPlanos();
});

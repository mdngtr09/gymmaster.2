$(document).ready(function() {
    const $form = $("#formAluno");
    const $tabela = $("#tabelaAlunos tbody");
    const API = "http://localhost:8081/api/alunos"; // URL base do backend

    // Carrega alunos
    function carregarAlunos() {
        $.ajax({
            url: API,
            type: "GET",
            dataType: "json",
            success: function(alunos) {
                $tabela.empty();
                alunos.forEach(aluno => {
                    $tabela.append(`
                        <tr data-id="${aluno.id}">
                            <td>${aluno.id}</td>
                            <td>${aluno.nome}</td>
                            <td>${aluno.email}</td>
                            <td>${aluno.idade || ''}</td>
                            <td>
                                <button class="editar">Editar</button>
                                <button class="deletar">Deletar</button>
                            </td>
                        </tr>
                    `);
                });
            },
            error: function(err) {
                alert("Erro ao carregar alunos");
                console.error(err);
            }
        });
    }

    // Limpar formulário
    $("#resetAluno").click(function() {
        $form[0].reset();
        $("#alunoId").val('');
    });

    // Salvar ou atualizar aluno
    $form.submit(function(e) {
        e.preventDefault();
        const aluno = {
            nome: $("#nome").val(),
            email: $("#email").val(),
            idade: $("#idade").val() ? parseInt($("#idade").val()) : null
        };

        const alunoId = $("#alunoId").val();
        if (alunoId) {
            // Atualizar aluno (PUT)
            $.ajax({
                url: `http://localhost:8081/api/alunos/${alunoId}`,
                type: "PUT",
                contentType: "application/json",
                data: JSON.stringify(aluno),
                success: function() {
                    carregarAlunos();
                    $form[0].reset();
                    $("#alunoId").val('');
                },
                error: function(err) {
                    alert("Erro ao atualizar aluno");
                    console.error(err);
                }
            });
        } else {
            // Criar novo aluno (POST)
            $.ajax({
                url: API,
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(aluno),
                success: function() {
                    carregarAlunos();
                    $form[0].reset();
                },
                error: function(err) {
                    alert("Erro ao salvar aluno");
                    console.error(err);
                }
            });
        }
    });

    // Editar aluno
    $tabela.on("click", ".editar", function() {
        const $tr = $(this).closest("tr");
        const id = $tr.data("id");
        $("#alunoId").val(id);
        $("#nome").val($tr.find("td:eq(1)").text());
        $("#email").val($tr.find("td:eq(2)").text());
        $("#idade").val($tr.find("td:eq(3)").text());
    });

    // Deletar aluno
    $tabela.on("click", ".deletar", function() {
        if (!confirm("Deseja realmente deletar este aluno?")) return;
        const id = $(this).closest("tr").data("id");
        $.ajax({
            url: `${API}/${id}`,
            type: "DELETE",
            success: function() {
                carregarAlunos();
            },
            error: function(err) {
                alert("Erro ao deletar aluno");
                console.error(err);
            }
        });
    });

    // Inicial
    carregarAlunos();
});

$(document).ready(function () {
    $('#cpf').change(obterNomePaciente);
    $('#especialidade').change(habilitarData);
    $('#data').change(obterHorariosDisponiveis);

    setMinData();

    function setMinData() {
        var today = new Date();

        var dd = today.getDate();
        var mm = today.getMonth() + 1;
        var yyyy = today.getFullYear();

        if (dd < 10) {
            dd = '0' + dd
        }
        if (mm < 10) {
            mm = '0' + mm
        }
        today = yyyy + '-' + mm + '-' + dd;
        $('#data').attr("min", today);
    }

    function habilitarData() {
        $('#data').prop('disabled', false);
        $('#horario').empty();
    }

    function obterHorariosDisponiveis() {
        $('#horario').prop('disabled', true);

        var data = $('#data').val();
        var especialidade = $('#especialidade').val();
        var funcao = window.location.pathname.split("/")[1];
        var url = `/${funcao}/novaConsulta/horariosDisponiveis?data=${data}&especialidadeId=${especialidade}`;

        $('#horario').load(url, function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success") {
                $('#horario').prop('disabled', false);
            }
            if (statusTxt == "error") {
                $('#horario').empty();
                alert(responseTxt);
            }
        });
    }

    function obterNomePaciente() {
        var cpf = $('#cpf').val();

        if (cpf) {
            var funcao = window.location.pathname.split("/")[1];
            var url = `/${funcao}/paciente/${cpf}`;

            $('#nomePacientePlaceholder').load(url, function (responseTxt, statusTxt, xhr) {
                if (statusTxt == "success") {
                }
                if (statusTxt == "error") {
                    $('#pacienteNome').val("");
                    alert(responseTxt);
                }
            });
        }else{
            $('#pacienteNome').val("");
        }
    }
})
$(document).ready(function () {
    url = new URL(window.location.href);
    cpf = url.searchParams.get('cpf');
    console.log(cpf);
    if (cpf !== "null") {
        $("#cpf").val(cpf);
    }
})
$(document).ready(function () {
    $('#cid').change(obterCidDescricao);

    function obterCidDescricao() {
        var cid = $('#cid').val();

        if (cid) {
            var url = `/medico/cid/${cid}`;

            $('#cidPlaceholder').load(url, function (responseTxt, statusTxt, xhr) {
                if (statusTxt == "success") {
                }
                if (statusTxt == "error") {
                    $('#cidDescricao').val("");
                    alert(responseTxt);
                }
            });
        }else{
            $('#cidDescricao').val("");
        }
    }

})
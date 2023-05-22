<script>
    function pesquisaFilme() {
        var tituloFilme = $("#nome").val();

        $.ajax({
            url: "https://www.omdbapi.com/",
            type: "GET",
            data: {
                apikey: "78a6e5ed",
                t: tituloFilme
            },
            success: function(data) {
                $("#duracao").text(data.Runtime);
                $("#ano").text(data.Year);
            }
        });
    }
</script>
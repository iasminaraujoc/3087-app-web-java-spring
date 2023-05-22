<script>
    function searchMovie() {
        var movieTitle = $("#nome").val();

        $.ajax({
            url: "https://www.omdbapi.com/",
            type: "GET",
            data: {
                apikey: "suaChave",
                t: movieTitle
            },
            success: function(data) {
                $("#duracao").text(data.Runtime);
                $("#ano").text(data.Year);
            }
        });
    }
</script>
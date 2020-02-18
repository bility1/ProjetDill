<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample08" aria-controls="navbarsExample08" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample08">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <s:a action="propositionSujet" class="nav-link"><s:text name="entreprise.sujet" ></s:text></s:a>

            </li>
            <li class="nav-item">
                <s:a action="mesPropositions" class="nav-link"><s:text name="entreprise.voir.sujet"></s:text></s:a>
            </li>
            <li class="nav-item">
                <s:a action="deconnexion" class=" nav-link"><s:text name="deconnexion"></s:text></s:a>
            </li>
            <li class="nav-item">
                <s:a action="menu" namespace="/" class="nav-link"><s:text name="menu.titre"></s:text></s:a>
            </li>
        </ul>
    </div>
</nav>
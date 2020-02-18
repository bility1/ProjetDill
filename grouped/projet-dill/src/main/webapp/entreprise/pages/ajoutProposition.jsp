<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: bazebimio
  Date: 11/01/20
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/commun/include.jsp" %>
    <title>Ajout Proposition</title>
</head>
<body>
<%@include file="/entreprise/pages/menuInclude.jsp" %>

<div class="container text-center">
    <br>
    <div class="jumbotron jumbotron-fluid">
        <div class="container">
            <h2 class="display-4">Faire une proposition de sujet</h2>
            <p class="lead">Vous pouvez soumettre un sujet , -après la validation par notre Admin ,
                les étudiants pourront s'y inscrire</p>
        </div>
    </div>
    <div class="col-12">
        <s:form action="ajoutProposition">

            <s:textfield name="nom" key="entreprise.nom" class="form-control"></s:textfield>

            <s:textfield name="ville" key="entreprise.ville" class="form-control"></s:textfield>

            <s:textarea name="description" key="entreprise.description" class="form-control"></s:textarea>

            <s:textfield name="tuteurEntreprise" key="entreprise.tuteurEntreprise" class="form-control"></s:textfield>

            <s:textfield name="logo" key="entreprise.logo" class="form-control"></s:textfield>

            <s:textfield name="fichier" key="entreprise.ficher" class="form-control"></s:textfield>

            <s:submit value="valider" class="btn btn-success btn-lg"></s:submit>
        </s:form>
    </div>
</div>



</ul>
<s:a action="menuEntreprise" namespace="/entreprise"><s:text name="menu.titre"></s:text></s:a>
</body>
</html>

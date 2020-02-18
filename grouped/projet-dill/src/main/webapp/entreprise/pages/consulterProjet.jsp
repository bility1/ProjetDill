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

    <title><s:text name="entreprise.consulterProjet"></s:text></title>
</head>
<body>

<%@include file="/entreprise/pages/menuInclude.jsp" %>

<div class="container">
    <br>

        <div class="card col-lg-12">
            <div class="card-body">
                <h1 class="card-title"><em><s:property value="findProjet.nom"></s:property></em></h1>
                <p class="card-text">
                    <s:property value="findProjet.description"></s:property>
                    <br>
                    - <s:property value="findProjet.ville"></s:property>
                    <br>
                    - <s:property value="findProjet.logo"></s:property>
                    <br>
                </p>
            </div>
            <h3 class="badge badge-primary">Livrables</h3>

            <s:iterator value="%{findProjet.livrables}" var="livrable">

                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Date finale de livraison : <s:property value="#livrable.date"></s:property></li>
                    <li class="list-group-item">livré le : <s:property value="#livrable.dateDepot"></s:property></li>
                </ul>
            </s:iterator>

            <h3 class=" badge badge-warning">Composition du groupe</h3>
            <s:iterator value="%{etudiants}" var="etudiant">

                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Nom : <s:property value="#etudiant.nom"></s:property></li>
                    <li class="list-group-item">Prénom : <s:property value="#etudiant.prenom"></s:property></li>
                    <li class="list-group-item">Numéro étudiant : <s:property value="#etudiant.numeroEtudiant"></s:property></li>
                </ul>
            </s:iterator>
        </div>

</div>
<s:a action="menuEntreprise" namespace="/entreprise"><s:text name="menu.titre"></s:text></s:a>
</body>
</html>



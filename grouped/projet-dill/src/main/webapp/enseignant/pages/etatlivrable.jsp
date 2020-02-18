<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: aminatou
  Date: 12/01/2020
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="enseignant.livrables.titre"></s:text></title>
</head>
<h1><s:text name="enseignant.livrables.t1"></s:text></h1>
<body>
<ul>

    <s:iterator value="etudiants" var="etudiant">
        <li>
            <s:text name="enseignant.livrables.label.nom"></s:text> <s:property value="#etudiant.nom"></s:property>
            <s:text name="enseignant.livrables.label.prenom"></s:text> <s:property value="prenom"></s:property>
            <s:text name="enseignant.livrables.label.numeroEtu"></s:text> <s:property value="numeroEtudiant"></s:property>
            <s:text name="enseignant.livrables.label.numeroGroupe"></s:text> <s:property value="numeroGroupe"></s:property>
            <ul>
                <s:iterator value="#etudiant.projets" var="projet">
                    <s:iterator value="#projet.livrables" var="livrables">
                        <li>
                            <s:text name="enseignant.livrables.label.dateLimit"></s:text> <s:property value="date"></s:property>
                            <s:text name="enseignant.livrables.label.dateDepot"></s:text> <s:property value="dateDepot"></s:property>
                        </li>
                    </s:iterator>
                </s:iterator>
            </ul>
        </li>
    </s:iterator>

</ul>
Il y a <s:property value="NbEtuRetard"/> élèves en retards pour la date limite recemment dépassé <s:property value="dateLimiteActuel"/>.
Envoyez a tous les groupes <s:a action="maillivrable">Envoie mail</s:a><br/>
</body>
<footer>
    <s:a action="menuEnseignant" namespace="/enseignant"><s:text name="menu.titre"></s:text></s:a>
</footer>
</html>

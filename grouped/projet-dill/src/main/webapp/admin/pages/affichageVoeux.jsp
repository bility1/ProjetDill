<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: cecile
  Date: 18/01/2020
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title><s:text name=""></s:text></title>
</head>
<body>

<h2>Liste des voeux</h2> </br>
<ul>
    <s:iterator var="etudiant" value="%{Etudiants}">

        <s:property value="#etudiant.nom"/> <s:property value="#etudiant.prenom"/>
        </br>  </br>

        <s:iterator var="voeuxEtu" value="#etudiant.voeux">
        <form method="post" action="validerVoeux">

            <p>

                Voeux <s:if test="#voeuxEtu.etat.toString() == 'EN_ATTENTE'">
                 en attente de validation :
            </s:if>

                 <s:if test="#voeuxEtu.etat.toString() == 'VALIDER'">
                validé :
            </s:if>

                 <s:if test="#voeuxEtu.etat.toString() == 'REFUSER'">
                refusé :
            </s:if>


                <s:textfield readonly="true" name="nomVoeu" value="%{#voeuxEtu.nomProjet}"/> - position n° <s:property value="#voeuxEtu.position"/> </p>
        <s:hidden name="numEtu" value="%{#etudiant.numeroEtudiant}"></s:hidden>

            <s:if test="#voeuxEtu.etat.toString() == 'EN_ATTENTE'">

            <s:submit value="Valider"/>
            </s:if>
        </form>

        </s:iterator>

        </br>
        </br>
    </s:iterator>

</ul>
<s:a action="menuAdmin" namespace="/admin"><s:text name="menu.titre"></s:text></s:a>
</body>
</html>

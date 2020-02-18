<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: cecile
  Date: 15/01/2020
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="admin.menu.sujets"></s:text></title>
</head>
<body>
<h3><s:text name="admin.consultation.h3"></s:text></h3>
<ul>
<s:iterator var= "sujet" value="%{ConsultationSujets}">

    <s:property value="#sujet.nom"></s:property></br>
    <s:property value="#sujet.ville"></s:property></br>
    <s:property value="#sujet.description"/> </br>
    <s:property value="#sujet.fichier"/> </br>
    <s:property value="#sujet.logo"/> </br>

    <s:if test="#sujet.statut.toString() == 'EN_ATTENTE'"></br>

        <s:form action="validerSujets" namespace="/admin">
            <s:hidden name="nom" value="%{#sujet.nom}"></s:hidden>
            <s:submit value="Valider"  />
        </s:form>

        <s:form action="refuserSujets" namespace="/admin">
            <s:hidden name="nom" value="%{#sujet.nom}"></s:hidden>
            <s:submit value="Refuser"/>
        </s:form>




    </s:if>

    </s:iterator>

</ul>
</body>
<footer>
    <s:a action="menuAdmin" namespace="/admin"><s:text name="admin.retour"></s:text></s:a>
</footer>
</html>

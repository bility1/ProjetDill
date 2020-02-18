<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: osboxes
  Date: 1/11/20
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="enseignant.projets.titre"></s:text></title>
</head>
<body>
<h2><s:text name="enseignant.projets.titre1"></s:text></h2>
<table>
    <thead>
    <tr>
        <th><s:text name="enseignant.projet.nom"></s:text></th>
        <th><s:text name="enseignant.projet.ville"></s:text></th>
        <th><s:text name="enseignant.projet.description"></s:text></th>
        <th><s:text name="enseignant.projet.tutorer"></s:text></th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="projetTutore" var="projet">
        <tr>
            <td><s:property value="#projet.nom"></s:property></td>
            <td><s:property value="#projet.ville"></s:property></td>
            <td><s:property value="#projet.description"></s:property></td>
        </tr>
    </s:iterator>
    </tbody>
</table>
<h2><s:text name="enseignant.projets.titre2"></s:text></h2>
<table>
    <thead>
    <tr>
        <th><s:text name="enseignant.projet.nom"></s:text></th>
        <th><s:text name="enseignant.projet.ville"></s:text></th>
        <th><s:text name="enseignant.projet.description"></s:text></th>
        <th><s:text name="enseignant.projet.tutorer"></s:text></th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="%{sujets}" var="sujet">
        <tr>
            <td><s:property value="#sujet.nom"></s:property></td>
            <td><s:property value="#sujet.ville"></s:property></td>
            <td><s:property value="#sujet.description"></s:property></td>
            <td>
                <s:a action="projets">
                <s:param name="sujet"><s:property value="#sujet.nom"></s:property></s:param>
                <s:text name="enseignant.projet.tutorer"></s:text>
            </s:a></td>
        </tr>
    </s:iterator>
    </tbody>
</table>
<footer>
    <s:a action="menuEnseignant" namespace="/enseignant" ><s:text name="enseignant.projet.menu"></s:text></s:a>
</footer>
</body>
</html>

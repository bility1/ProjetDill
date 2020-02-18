<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: cecile
  Date: 13/01/2020
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="admin.affichageSujets.titre"></s:text></title>
</head>
<body>

<h3><s:text name="admin.affichageSujets.h3"></s:text></h3> </br>
<ul>
<s:iterator var="Sujet" value="%{Sujets}">


        <li>
        <s:property value="#Sujet.nom"/>
        </li>


    <s:url action="consultationSujets" var="url">
        <s:param name="nom" value="#Sujet.nom">

        </s:param>
    </s:url>
    <s:a href="%{url}">Consulter</s:a>


</br>
</s:iterator>
</ul>
<s:a action="menuAdmin" namespace="/admin"><s:text name="menu.titre"></s:text></s:a>
</body>
</html>

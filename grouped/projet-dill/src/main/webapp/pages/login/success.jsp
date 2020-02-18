<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: aminatou
  Date: 10/01/2020
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Bonjour  <s:property value="varSession.user.adresseEmail"/>
   vous êtes étudiants <s:property value="varSession.user.role"/>

    <s:a action="deconnexion">Deconnexion</s:a>
</h3>
</body>
</html>

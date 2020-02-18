<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: cecile
  Date: 16/01/2020
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="admin.menu.sujets"></s:text></title>

</head>
<body>


<p><s:text name="admin.validerSujet.confirm"></s:text> <s:property value="nom"/> </p>
</body>
<footer>
    <s:a action="gestionDesSujets"><s:text name="admin.retour"></s:text></s:a>
</footer>
<s:a action="menuAdmin" namespace="/admin"><s:text name="menu.titre"></s:text></s:a>
</html>

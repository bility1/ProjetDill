<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: cecile
  Date: 19/01/2020
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Vous avez validé le voeu <s:property value="nomVoeu"/> </p>
</body>
<footer>
    <s:a action="menuAdmin"><s:text name="admin.retour"></s:text></s:a>
</footer>
<s:a action="menuAdmin" namespace="/admin"><s:text name="menu.titre"></s:text></s:a>
</html>

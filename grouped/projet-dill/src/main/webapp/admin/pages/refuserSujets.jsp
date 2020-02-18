<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: cecile
  Date: 16/01/2020
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="admin.menu.sujets"></s:text></title>
</head>
<body>
<p><s:text name="admin.refuserSujet.confirm"></s:text> <s:property value="nom"/> </p>
<s:a action="menuAdmin" namespace="/admin"><s:text name="menu.titre"></s:text></s:a>
</body>
</html>

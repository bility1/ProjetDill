<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: osboxes
  Date: 1/20/20
  Time: 5:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="notAllowAccess"></s:text></title>
</head>
<body>
<s:text name="notAllowAccess"></s:text>
<s:a action="menu" namespace="/" ><s:text name="menu.titre"></s:text></s:a>
</body>
</html>

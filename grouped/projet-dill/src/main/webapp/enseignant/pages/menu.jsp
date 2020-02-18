<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="a" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: osboxes
  Date: 1/19/20
  Time: 8:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="menu.titre"></s:text></title>
</head>
<body>
<ul>
    <li><s:a action="projets" namespace="/enseignant"><s:text name="menu.projets"></s:text></s:a></li>
    <li><s:a action="etatlivrable" namespace="/enseignant"><s:text name="menu.livrables"></s:text></s:a></li>
    <li><s:a action="menu" namespace="/"><s:text name="menu.retour"></s:text></s:a></li>
</ul>
</body>
</html>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: osboxes
  Date: 1/19/20
  Time: 9:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="menu.titre"></s:text></title>
</head>
<body>
<ul>
    <li><s:a action="suiviavancement" namespace="/etudiant"><s:text name="etudiant.menu.suivitAvancement"></s:text></s:a></li>
    <li><s:a action="goToAfficherLesSujets" namespace="/etudiant"><s:text name="etudiant.menu.afficherlessujets"></s:text></s:a> </li>
    <li><s:a action="consultation_projet" namespace="/etudiant"><s:text name="etudiant.menu.consulterprojet"></s:text></s:a> </li>
</ul>
</body>
<footer>
    <s:a action="menu"  namespace="/"><s:text name="menu.retour"></s:text></s:a>
</footer>
</html>

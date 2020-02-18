<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: osboxes
  Date: 1/18/20
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="menu.titre"></s:text></title>
</head>
<body>
    <nav>
        <ul>
            <li><s:a action="deconnexion" namespace="/"><s:text name="menu.deconnexion"></s:text></s:a></li>
            <li><s:a action="menuEnseignant" namespace="/enseignant"><s:text name="menu.enseignant"></s:text></s:a> </li>
            <li><s:a action="menuEtudiant" namespace="/etudiant"><s:text name="menu.etudiant"></s:text></s:a></li>
            <li><s:a action="menuEntreprise" namespace="/entreprise"><s:text name="menu.entreprise"></s:text></s:a> </li>
            <li><s:a action="menuAdmin" namespace="/admin"><s:text name="menu.admin"></s:text></s:a></li>
        </ul>
    </nav>
</body>
</html>

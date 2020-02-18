<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: aminatou
  Date: 18/01/2020
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/commun/include.jsp" %>
    <title><s:text name="etudiant.voeux.titre"></s:text></title>
</head>
<body class="text-center">
<div class="container">
    <div class="row">
        <div class="col-lg-12">
<br>
            <div class="alert alert-info" role="alert">

                <h3><s:text name="etudiant.voeux.t3"></s:text></h3>
            </div>
            <s:iterator value="voeux">
              <s:text name="etudiant.voeux.label.projet"></s:text> <s:property value="nomProjet" ></s:property>,
               <s:text name="etudiant.voeux.label.position"></s:text> <s:property value="position" ></s:property>,
               <s:text name="etudiant.voeux.label.etat"></s:text> <s:property value="etat" ></s:property><br>
            </s:iterator><br>
        </div>
    </div>
</div>
</body>
<footer>
    <s:a action="menuEtudiant" namespace="/etudiant"><s:text name="menu.titre"></s:text></s:a>
</footer>
</html>

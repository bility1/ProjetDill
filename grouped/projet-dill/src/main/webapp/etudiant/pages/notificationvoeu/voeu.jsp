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
    <title>Voeu</title>
</head>
<body class="text-center">
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <br>
            <div class="alert alert-info" role="alert">

                <h3>Suivi d'avancement de vos voeux </h3>
            </div>
            <s:iterator value="%{voeux}" var="myobj" >
                Projet : <s:property value="#myobj.nomProjet" ></s:property>,
                Position : <s:property value="#myobj.position" ></s:property>,
                Etat : <s:property value="#myobj.etat" ></s:property><br>
            </s:iterator><br>
            <s:a action="validerenvoie">Valider voeu en attente</s:a><br>

        </div>
    </div>
</div>
</body>
</body>
</html>
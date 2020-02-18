<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: bazebimio
  Date: 11/01/20
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/commun/include.jsp" %>

    <title><s:text name="entreprise.mesPropositions"></s:text></title>
</head>
<body>

<%@include file="/entreprise/pages/menuInclude.jsp" %>
<div class="container">
    <div class="row">


        <s:iterator value="%{mesSujets}" var="sujet">
            <s:set var="statut" value="#sujet.statut" />



            <s:if test="%{ #statut.toString() == 'EN_ATTENTE' }">
                <div class="card col-lg-4 text-white bg-warning" style="width:18rem; margin: 3px;">

            </s:if>
            <s:elseif test="%{ #statut.toString() == 'VALIDER' }">
                <div class="card col-lg-4 text-white bg-success" style="width:18rem; margin: 3px;">
            </s:elseif>
            <s:else>
                <div class="card col-lg-4 text-white bg-danger" style="width:18rem; margin: 3px;">
            </s:else>


            <div class="card-header">
                <h2><s:property value="#sujet.nom"></s:property></h2>
            </div>
            <s:url var="url" action="voirSujet"> <s:param name="nomSujet" value="#sujet.nom"></s:param></s:url>


            <ul class="list-group list-group-flush">
                <li class="list-group-item" style="color: black;"><s:property value="#sujet.description"></s:property></li>
                <li class="list-group-item" style="color: black;"><s:property value="#sujet.ville"></s:property></li>
                <li class="list-group-item" style="color: black;">

                    <s:if test="%{ #statut.toString() == 'EN_ATTENTE' }">
                        <s:text name="sujet.statut.attente"></s:text>

                    </s:if>
                    <s:elseif test="%{ #statut.toString() == 'VALIDER' }">
                        <s:text name="sujet.statut"></s:text>
                    </s:elseif>
                    <s:else>
                        <s:text name="sujet.statut.refuse"></s:text>
                    </s:else>

                </li>
                <li class="list-group-item">
                        <s:if test="%{#statut.toString() =='VALIDER'}">
                    <s:a href="%{url}">voir le projet creé </s:a>
                        </s:if>
                </li>

            </ul>

        </div>
        </s:iterator>

    </div>
    </div>
<s:a action="menuEntreprise" namespace="/entreprise"><s:text name="menu.titre"></s:text></s:a>
</body>
</html>



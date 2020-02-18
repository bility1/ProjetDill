<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: aminatou
  Date: 11/01/2020
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <%@include file="/pages/commun/include.jsp" %>
    <title><s:text name="connexion.inscription"></s:text></title>
</head>
<body>
<div class="container">
    <div class="col-lg-8 text-center">

        <h1 class="h3 mb-3 font-weight-normal">Inscription</h1>
        <s:form action="inscriptionValider" class="col-lg-12">
            <div class="center-div">
                <div class="col-lg-12">
                    <span style="color:darkred"> <s:property value="messageErreur"/> <br></span>


                    <s:textfield name="email" key="connexion.email" placeholder="email"  class="form-control"/>

                    <s:textfield name="password" key="connexion.password" placeholder="mot de passe" type="password" class="form-control"/>

                    <s:select name="role" list="%{roles}" key="inscription.role" class="form-control"/>
                    <s:submit class="btn btn-success"/>
                </div>
            </div>
        </s:form>
    </div>
</div>
</body>
</html>

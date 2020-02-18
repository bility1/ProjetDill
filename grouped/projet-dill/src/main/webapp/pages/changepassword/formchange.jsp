<%@ taglib prefix="s" uri="/struts-tags" %>

<%--
  Created by IntelliJ IDEA.
  User: aminatou
  Date: 11/01/2020
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>

    <title><s:text name="connexion.changePassword.titre"></s:text></title>
</head>
<body>

<s:form action="confirmChange">
    <span style="color:darkred"> <s:property value="messageErreur"/> <br></span>
    <s:textfield name="email" key="connexion.email" class="form-control" cssStyle="color:black"/>
    <s:textfield name="newPassword" key="connexion.newpassword" type="password" cssStyle="color:black" class="form-control"/>
    <s:submit class="btn btn-success"/>
</s:form>
</body>
</html>

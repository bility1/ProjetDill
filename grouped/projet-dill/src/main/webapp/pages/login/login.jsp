<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: aminatou
  Date: 10/01/2020
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>


    <title><s:text name="connexion.titre"></s:text></title>
</head>
<body class="text-center">
<%@include file="/pages/commun/include.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-lg-12">

        <br><div class="alert alert-info" role="alert">
        Si vous n'êtes pas encore inscrit -> <s:a action="inscription"> Inscrivez Vous</s:a>
        </div>


            <s:form action="authentification" class="col-lg-12" namespace="/" method="POST">
                <div class="center-div">
                <h1 class="h3 mb-3 font-weight-normal">Connexion</h1>
                <span style="color:darkred"> <s:property value="messageErreur"/> <br></span>

                <%--<input type="email" name="email" id="authentification_email" class="form-control" placeholder="email" required="true">--%>
                <s:textfield name="email" type="email"  class="form-control mb-3 " placeholder="email" required="true" ></s:textfield>

                <br> <%--<input type="password" name="password" id="authentification_password" class="form-control" placeholder="Password">--%>
                <s:textfield type="password" name="password" placeholder="Password"  class="form-control mb-3"></s:textfield>


                <s:submit value="Valider" class="btn btn-lg btn-success btn-block mb-3"></s:submit>

            </s:form>
                    <label>
                        <s:a action="changepassword"> mot de passe oublié </s:a>
                    </label>
                </div>
        </div>

    </div>
</div>
<%--<s:textfield type="password" name="password" placeholder="Password"  key="connexion.password" class="form-control"></s:textfield>--%>

<%--<s:textfield name="email" type="email" key="connexion.email" class="form-control" placeholder="email" required="true" ></s:textfield>--%>
<%--<s:submit  value="Valider" class="btn btn-lg btn-primary btn-block" ></s:submit>--%>

<footer>
    <p class="mt-5 mb-3 text-muted">© 2020</p>
</footer>
</body>
</html>

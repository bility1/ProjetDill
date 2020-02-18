<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: amina
  Date: 19/01/2020
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.2.css">
    <link rel="stylesheet" href="css/custom.css">

    <title>Mon projet</title>
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
    <div class="container">
        <!-- Logo and responsive toggle -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Projet DiLL</a>
        </div>
        <!-- Navbar links -->
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="nav navbar-nav">
                <li class="active">
                    <s:a action="goToAfficherLesSujets">Consultation des sujets</s:a>
                </li>
                <li class="active">
                    <s:a action="deconnexion">Deconnexion</s:a>
                </li>
                <li class="active">
                    <s:a action="suiviavancement" namespace="/etudiant">Suivi d'avancement</s:a>
                </li>
                <li class="active"><s:a action="menuEtudiant"  namespace="/etudiant">Menu</s:a></li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
<div class="container-fluid">
    <!-- Center Column -->
    <div class="col-sm-10">

        <s:set var="existeProjet" value="okprojet" />
        <s:if test="%{#existeProjet==false}">
            <!-- Alert -->
            <div class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong> Information:</strong><p> Auncun voeu n'a été validé pour le moment.
                Vous recevrez une notification lorsque nous vous affecterons dans un projet.</p>
            </div>
        </s:if>
        <s:set var="existeProjet" value="okprojet" />
        <s:if test="%{#existeProjet==true}">
            <!-- Alert -->
            <div class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong> Information :</strong><p> Vous avez été attribué à un projet. Félicitation !</p>
            </div>
        </s:if>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                <span class="glyphicon glyphicon-bullhorn"></span>
                Visualisation de votre projet en cours
            </h3>
        </div>
        <div class="panel-body">
            <div class="btn-group" role="group">
            <s:iterator var="nomtuteur" value="Tuteurs">
             <p><strong>Tuteur actuel</strong></p><p><s:property value="#nomtuteur"/></p>
            </s:iterator>

            <s:iterator var="projet" value="Listeprojet">
                <p><strong>Affectation projet:</strong></p><p><s:property value="#projet.nom"/></p>
                <p><strong>Description:</strong> <p><s:property value="#projet.description"/></p>
            </s:iterator>


            </div>
           </div>
    </div>
</div>
</div>
</body>
</html>

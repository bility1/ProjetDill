<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: amina
  Date: 15/01/2020
  Time: 21:21
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

    <title><s:text name="inscription.title"/></title>
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
                    <s:a action="deconnexion">Deconnexion</s:a>
                </li>
                <li class="active">
                    <s:a action="suiviavancement" namespace="/etudiant">Suivi d'avancement</s:a>
                </li>
                <li class="active"><s:a action="consultation_projet" namespace="/etudiant">Consulter mon projet</s:a> </li>
                <li class="active"><s:a action="menuEtudiant"  namespace="/etudiant">Menu</s:a></li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>


<div class="container-fluid">
    <!-- Left Column -->
    <div class="col-sm-3">

        <!-- Text Panel -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title"><span class="glyphicon glyphicon-cog"></span> Mon profil </h1>
            </div>

            <div class="panel-body">
                <p><s:property value="name"></s:property></p>
                <p><s:property value="prenom"></s:property></p>
                <p> <s:property value="numeroetudiant"></s:property> </p>
            </div>
        </div>


    </div><!--/Left Column-->
<!-- Center Column -->
    <div class="col-sm-8">
    <!-- Alert -->
    <div class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong> Go:</strong> Choisissez vos sujets préférés!
    </div>

    <!-- Articles -->
    <div class="row">
        <s:iterator var="sujet" value="listesujets">
            <hr><article class="col-xs-12">

                   <h2><s:property value="#sujet.nom"/></h2>
                   <p><s:property value="#sujet.description"/></p>
                </article>
            <hr>
            </s:iterator>
    </div>
        <s:set var="messageRealisationAjout" value="inscriptionfaite" />
        <s:if test="%{#messageRealisationAjout==true}">
        <p style="border:1px dashed orange;">
            <!-- Center Column -->
            <div class="col-sm-8">
                <!-- Alert -->
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times</span></button>
                    <strong> Merci, nous avons pris en compte vos choix. Vous pouvez les consulter sur la barre d'info.</strong>
                </div>

                </s:if>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <span class="glyphicon glyphicon-bullhorn"></span>
                    Inscrivez-vous aux projets
                </h3>
            </div>
            <div class="panel-body">
                <p>Vous pouvez selectionner 3 voeux différents</p>
                    <s:form action="goToSinscrire">
                        <s:select label="Voeu 1"
                                  list="nomdessujets"
                                  name="listenomSujet1"
                                  headerKey="-1" headerValue="Choix n°1"/> <br/>
                        <s:select label="Voeu 2"
                                  list="nomdessujets"
                                  name="listenomSujet2"
                                  headerKey="-1" headerValue="Choix n°2"/> <br/>
                       <s:select label="Voeu 3"
                                  list="nomdessujets"
                                  name="listenomSujet3"
                                  headerKey="-1" headerValue="Choix n°3 "/>
                        <br/>

                        <s:submit value="Soumettre"/>
                    </s:form>
                    <s:set var="existancevoeu1" value="premiervoeuexiste" />
                    <s:if test="%{#existancevoeu1==false}">
                        <p style="border:1px dashed orange;"> Votre êtes déjà inscrit à votre voeu n°1.</p>
                    </s:if>
                <s:set var="existancevoeu2" value="deuxiemevoeuexiste" />
                <s:if test="%{#existancevoeu2==false}">
                    <p style="border:1px dashed orange;"> Votre êtes déjà inscrit à votre voeu n°2.</p>
                </s:if>
                <s:set var="existancevoeu3" value="troisiemevoeuexiste" />
                <s:if test="%{#existancevoeu3==false}">
                    <p style="border:1px dashed orange;"> Votre êtes déjà inscrit à votre voeu n°3. </p>
                </s:if>


            </div>
        </div>
    </div>
    <!-- Right Column -->
    <div class="col-sm-3">

        <!-- Form -->



    </div><!--/Right Column -->

</div><!--/container-fluid-->


































</div>

</body>
</html>

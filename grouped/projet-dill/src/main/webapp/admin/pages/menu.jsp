<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: cecile
  Date: 18/01/2020
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="admin.menu.titre"></s:text></title>
</head>
<body>

<h3><s:text name="menu.titre"></s:text></h3>
<li><s:a action ="gestionDesSujets" namespace="/admin"><s:text name="admin.menu.sujets"></s:text></s:a></li>
<li><s:a action="gestionDesVoeux"  namespace="/admin"><s:text name="admin.menu.voeux"></s:text></s:a></li>
<li><s:a action="uploadAction" namespace="/admin"><s:text name="menu.import"></s:text></s:a></li>
<li><s:a action="menu" namespace="/"><s:text name="admin.retour"></s:text></s:a></li>

</body>
</html>

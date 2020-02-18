<%--
  Created by IntelliJ IDEA.
  User: MSi
  Date: 12/01/2020
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
<%--         pageEncoding="ISO-8859-1" session="false"%>--%>
<html>
<head>
    <s:head />
    <title>Importer un fichier csv</title>
</head>
<%--<s:actionerror/>--%>
<body>
<table border="0" align="center" bordercolor="red">
    <s:form action="uploadAction" method="POST" enctype="multipart/form-data">
        <tr>
            <td style="color: red;">
                Choissisez le fichier
                <s:file name="uploadFile"  size="40" cssStyle="color:green"/>
            </td>
        </tr>
        <tr>
            <td>
                <s:submit value="Upload" name="submit" cssStyle="color:green"/>
            </td>
        </tr>
    </s:form>
</table>
<s:a action="menuAdmin" namespace="/admin"><s:text name="menu.titre"></s:text></s:a>
</body>
</html>

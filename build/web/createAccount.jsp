<%-- 
    Document   : createAccount
    Created on : Jul 1, 2025, 7:54:17 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <h1>Create Account Page</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="erros" value="${requestScope.CREATE_ACCOUNT}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 - 12 chars)<br/>
            <c:if test="${not empty erros.usernameLengthError}">
                <font color="red">
                ${erros.usernameLengthError}
                </font>
            </c:if>
            <c:if test="${not empty erros.usernameIsExisted}">
                <font color="red">
                ${erros.usernameIsExisted}
                </font>
            </c:if>
            <br>
            Password* <input type="password" name="txtPassword" value="" />(6 - 20 chars)<br/>
            <c:if test="${not empty erros.passwordLengthError}">
                <font color="red">
                ${erros.passwordLengthError}
                </font>
            </c:if>
            <br>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty erros.confirmNotMatch}">
                <font color="red">
                ${erros.confirmNotMatch}
                </font>
            </c:if>
            <br>
            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" />(2 - 50 chars)<br/>
            <c:if test="${not empty erros.fullNameLengthError}">
                <font color="red">
                ${erros.fullNameLengthError}
                </font>
            </c:if>
            <br>
            <input type="submit" name="btAction" value="Create Account" />
            <input type="reset" name="Reset"/>
        </form>
        <a href="login.html">Click to back</a>
    </body>
</html>

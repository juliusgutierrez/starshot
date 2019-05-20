<%--
  Created by IntelliJ IDEA.
  User: juliusgutierrez
  Date: 18/05/2019
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Log in</title>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">

    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>
<div class="loginBody">
    <div class="container">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-heading">
                    <h2 class="text-center">Login</h2>
                </div>
                <hr/>
                <div class="modal-body">
                    <form method="POST" action="${contextPath}/login" role="form">

                        <div class="text-danger ${error != null ? 'has-error' : ''}" role="alert">
                            <small>${error}</small>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
							<span class="input-group-addon">
							<span class="glyphicon glyphicon-user"></span>
							</span>
                                <input type="text" class="form-control" placeholder="Username"
                                       autoComplete="off"
                                       name="username"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
							<span class="input-group-addon">
							<span class="glyphicon glyphicon-lock"></span>
							</span>
                                <input type="text" class="form-control" placeholder="Password"
                                       autoComplete="off"
                                       name="password"/>
                            </div>
                        </div>
                        <div class="form-group text-center">
                            <button class="btn btn-success float-right login-btn"
                                    type="submit">Login
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="app" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html lang="en" ng-app="app">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<base href="${app}/">
		<title>Team Manager</title>
		<!--link rel="styleSheet" href="res/app/js/node_modules/angular-ui-grid/ui-grid.css"/-->
		<link rel="stylesheet" href="res/app/js/node_modules/bootstrap/dist/css/bootstrap.css">
		<link rel="stylesheet" href="res/app/js/node_modules/font-awesome/css/font-awesome.css">
		<link rel="stylesheet" href="res/app/js/node_modules/admin-lte/dist/css/AdminLTE.css">
		<link rel="stylesheet" href="res/app/js/node_modules/admin-lte/dist/css/skins/_all-skins.min.css">
		<link rel="stylesheet" href="res/app/css/app.css">
	</head>

	<body class="skin-blue login-page">
		<div class="login-box">
			<div class="login-logo">
				<a href="#!/"><b>Team</b> Manager</a>
			</div>

			<div class="login-box-body">
				<div>
					<c:if test="${param.error != null && not empty SPRING_SECURITY_LAST_EXCEPTION}">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>Login failed!</strong><br>
								${SPRING_SECURITY_LAST_EXCEPTION.message}
						</div>
					</c:if>
				</div>

				<p class="login-box-msg">Log in to start</p>

				<div>
					<c:if test="${message != null}">
						<div class="alert alert-success">
							${message}
						</div>
					</c:if>
				</div>

				<form action="login" method="post">
					<div class="form-group has-feedback">
						<label for="email">Email</label>

						<input type="email" class="form-control"
							   id="email" name="email" value="${email}"
							   placeholder="Email" autofocus="autofocus" required="required"/>

						<i class="glyphicon glyphicon-envelope form-control-feedback"></i>
					</div>

					<div class="form-group has-feedback">
						<label for="password">Password</label>

						<input type="password" class="form-control"
							   id="password" name="password"
							   placeholder="Password" required="required" />

						<i class="glyphicon glyphicon-lock form-control-feedback"></i>
					</div>

					<button type="submit" class="btn btn-primary btn-block btn-flat">Log In</button>

					<section>
						<a href="restore">Restore password</a>
						<a class="pull-right" href="register">Register</a>
					</section>

				</form>
			</div>

		</div>

		<script src="res/vendor/datejs/date.js"></script>
		<script src="res/vendor/bignumber/bignumber.js"></script>

		<script src="res/app/js/dist/vendor.bundle.js"></script>
		<script src="res/app/js/node_modules/admin-lte/dist/js/app.js"></script>
		<script src="res/app/js/node_modules/admin-lte/plugins/slimScroll/jquery.slimscroll.js"></script>

		<script src="res/app/js/dist/app.bundle.js"></script>

	</body>
</html>
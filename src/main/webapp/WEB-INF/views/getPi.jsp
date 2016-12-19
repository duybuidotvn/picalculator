<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/
bootstrap.min.css">
<title>PI Calculator</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Pi Calculator</h3>
					</div>
					<div class="panel-body">
						<c:if test="${not empty errMsg }">
							<div class="alert alert-danger">
								${errMsg}
								<br />
							</div>
						</c:if>
						<spring:url var = "action" value='/pi/cal.do' />
						<sf:form action="${action}" modelAttribute="piModel" method="GET">
							<fieldset>
								<div class="form-group">
									<sf:select path="framework" class="form-control" placeholder="Framework">
								 		<sf:option value="FORK_JOIN">Fork Join</sf:option>
								 		<sf:option value="THREAD_POOL">Thread Pool</sf:option>								 		
								 		<sf:option value="SINGLE_THREAD">Single Thread</sf:option>
									</sf:select>
									<br />
									<sf:select path="formula" class="form-control" placeholder="Formula">
								 		<sf:option value="LEIBNIZ">LEIBNIZ</sf:option>
								 		<sf:option value="NILAKANTHA">NILAKANTHA</sf:option>
								 		<sf:option value="NILAKANTHA">BBP</sf:option>
									</sf:select>
								<sf:errors path="formula" cssClass="error" />  
								</div>
								<div class="form-group">
									<sf:input path="terms" class="form-control" placeholder="Input Terms" type="text"/>
							<!-- <sf:errors path="terms" cssClass="error" />    -->		
								</div>
								<div class="form-group">
									<label class="form-control" >${piModel.pi}</label>
								</div>
								<div class="form-group">Java Pi: ${piModel.javaPi}</div>
								<div class="form-group">Correct Digit: ${piModel.correctDigit}</div>
								<div class="form-group">Time: ${piModel.time}</div>
								<input class="btn btn-lg btn-success btn-block" type="submit"	value="Calculate">								
							</fieldset>
						</sf:form>
						<spring:url var = "#" value='/pi/cal.do' />
						<sf:form action="#" method="POST">
							<fieldset>
								<input class="btn btn-lg btn-success btn-block" type="submit"	value="Reset">								
							</fieldset>							
						</sf:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
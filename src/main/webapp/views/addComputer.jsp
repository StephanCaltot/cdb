<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/font-awesome.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a id="homePage" class="navbar-brand" href="springcdb"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form:form action="addComputer" modelAttribute="computerDto" method="POST">
                    	<input name="action" type="hidden" value="add"/>
                        <fieldset>
                        
                        
                        
                        
                        
                       		<spring:bind path="computerName">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Computer Name</label>
									<div class="col-sm-10">
									    <form:input path="computerName" type="text" id="computerName" class="form-control" placeholder="Computer name" name="computerName" />
										<form:errors path="computerName" class="control-label" />
									</div>
								</div>
							</spring:bind>
                        
                        
                        
							<spring:bind path="computerName">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Computer Name</label>
									<div class="col-sm-10">
									    <form:input path="computerName" type="text" id="computerName" class="form-control" placeholder="Computer name" name="computerName" />
										<form:errors path="computerName" class="control-label" />
									</div>
								</div>
							</spring:bind>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <form:input path="dateWichIsIntroduced" type="date" class="form-control" id="introduced" placeholder="Introduced date" name="introduced" />
								<span id="dateError">Date must bet like dd/mm/aaaa and introduced must be before discontinued.</span>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <form:input path="dateWichIsDiscontinued" type="date" class="form-control" id="discontinued" placeholder="Discontinued date" name="discontinued" />
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId">                                   
                                    <c:forEach var="company" items="${companies}">
                                    	<option value="${company.id}">${company.name}</option>
              						</c:forEach>
                                </select>
                            </div>                 
                        </fieldset>
                        <div class="actions pull-right">
                            <input id="addComputerButton" type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="springcdb" class="btn btn-default">Cancel</a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
    
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<%-- 	<script src="<c:url value="/resources/js/addcomputer.js"/>"></script> --%>
</body>
</html>
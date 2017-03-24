<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>
    
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computerDto.id}
                    </div>
                    <h1>Edit Computer</h1>

                    <form method="POST">
                        <input name="action" type="hidden" value="edit"/>
                        <fieldset>
                                <input type="hidden" class="form-control" id="id" name="id" value="${computerDto.id}" />
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" placeholder="Computer name" name="computerName" value="${computerDto.name}" />
                                <span id="nameError">Name have to matches letters, numbers or '-' , '_', '.', '*' and '/' characters.</span>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" placeholder="Introduced date" name="introduced" value="${computerDto.dateWichIsIntroduced}" />
								<span id="dateError">Date have to matches dd/mm/aaaa format.</span>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" placeholder="Discontinued date" name="discontinued" value="${computerDto.dateWichIsDiscontinued}"/>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select name="companyId" class="form-control" id="companyId" >                                   
                                    <c:forEach var="company" items="${companies}">
                                    <c:choose>
                                    	<c:when test="${computerDto.companyId == company.id}">
                                    		<option value="${company.id}" selected="selected" >${company.name}</option>
                                    	</c:when>
                                    	
                                    	<c:otherwise>
                                    		<option value="${company.id}">${company.name}</option>
                                    	</c:otherwise>
                                    </c:choose>
              						</c:forEach>
                                </select>
                            </div>             
                        </fieldset>
                        <div class="actions pull-right">
                            <input id="editComputerButton" type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="springcdb" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/editcomputer.js"/>"></script>
</body>
</html>
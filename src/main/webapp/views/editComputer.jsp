<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
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
                        id: ${computer.id}
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="computerdatabase" method="POST">
                        <input name="action" type="hidden" value="edit"/>
                        <fieldset>
                                <input type="hidden" class="form-control" id="id" name="id" value="${computer.id}" />
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" placeholder="Computer name" name="computerName" value="${computer.name}" />
                                <span id="inputName">Name have to matches letters, numbers or '-' , '_', '.', '&', '*' and '/' characters.</span>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" placeholder="Introduced date" name="introduced" value="${computer.dateWichIsIntroduced}" />
								<span id="inputIntroduced">Date have to matches dd/mm/aaaa format.</span>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" placeholder="Discontinued date" name="discontinued" value="${computer.dateWichIsDiscontinued}"/>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select name="companyId" class="form-control" id="companyId" >                                   
                                    <c:forEach var="company" items="${companies}">
                                    <c:choose>
                                    	<c:when test="${computer.manufacturer.id == company.id}">
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
                            <a href="dashboard.html" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
<script src="js/editcomputer.js"></script>
</body>
</html>
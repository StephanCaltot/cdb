<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- <jsp:useBean id="computerManager" scope="page" class="com.excilys.scaltot.cdb.managers.ComputerManagerBean" > --%>
<%--     <jsp:setProperty name="computerManager" property="*"/> --%>
<%-- </jsp:useBean> --%>

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
            <h1 id="homeTitle">
                 ${numberOfElements} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="filter" class="form-control" placeholder="Search name" value="${param.filter}" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer.jsp">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
<!--                 <tbody id="results"> -->
<%-- 		           <c:forEach items="${computerManager.displayedComputers}" var="computer"> --%>
<!-- 		               <tr> -->
<!-- 		                   <td class="editMode"> -->
<%-- 		                   		<input type="checkbox" name="cb" class="cb" value="${computer.id}"> --%>
<!-- 		                   </td> -->
<!-- 		                   <td> -->
<%-- 		                       <jsp:element name="a"> --%>
<%-- 									<jsp:attribute name="href"> --%>
<%-- 										editComputer.html?id=${computer.id} --%>
<%-- 									</jsp:attribute> --%>
<%-- 		                           <jsp:body> --%>
<%-- 		                               <c:out value="${computer.name}"/> --%>
<%-- 		                           </jsp:body> --%>
<%-- 		                       </jsp:element> --%>
<!-- 		                   </td> -->
<%-- 		                   <td><c:out value="${computer.dateWichIsIntroduced}"/></td> --%>
<%-- 		                   <td><c:out value="${computer.dateWichIsDiscontinued}"/></td> --%>
<%-- 		                   <td><c:out value="${computer.manufacturer.name}"/></td> --%>
<!-- 		               </tr> -->
<%-- 		           </c:forEach> --%>
                    
<!--                 </tbody> -->

                <tbody id="results">
				<c:forEach items="${computers}" var="computer">
					<tr>
						<td class="editMode"><input type="checkbox" name="cb"
							class="cb" value="0"></td>
						<td><a href="?action=edit&id=${computer.id}"
							onclick="">${computer.name}</a></td>
						<td>${computer.dateWichIsIntroduced}</td>
						<td>${computer.dateWichIsDiscontinued}</td>
<%-- 						<td>${computer.manufacturerName}</td> --%>
					</tr>
				</c:forEach>

                </tbody>
            </table>
        </div>
    </section>

<!--     <footer class="navbar-fixed-bottom"> -->
<!--         <div class="container text-center">         -->
<!--             <ul class="pagination"> -->
<!--                 <li> -->
<%--                     <a href="?numberForEachPage=${param.numberForEachPage}&currentPage=${param.currentPage -1}" aria-label="Previous"> --%>
<!--                       <span aria-hidden="true">&laquo;</span> -->
<!--                   </a> -->
<!--               </li> -->
<%--               <c:forEach var="i" begin="${(param.currentPage - 2 > 0) ? (param.currentPage -2 ) : 1}" end="${(param.currentPage + 2 < computerManager.numberOfPages) ? (param.currentPage + 2) : computerManager.numberOfPages}"> --%>
<%--               		<li><a href="?numberForEachPage=${param.numberForEachPage}&currentPage=${i-1}">${i}</a></li>  --%>
<%--               </c:forEach> --%>
<!--               <li> -->
<%--                 <a href="?numberForEachPage=${param.numberForEachPage}&currentPage=${param.currentPage + 1}" aria-label="Next"> --%>
<!--                     <span aria-hidden="true">&raquo;</span> -->
<!--                 </a> -->
<!--             </li> -->
<!--         </ul> -->
        
        

<!--         <div class="btn-group btn-group-sm pull-right" role="group" > -->
<%--             <a type="button" class="btn btn-default ${param.numberForEachPage == 10 || empty param.numberForEachPage ? 'active' : ''}" href="?numberForEachPage=10">10</a> --%>
<%--             <a type="button" class="btn btn-default ${param.numberForEachPage == 50 ? 'active' : ''}" href="?numberForEachPage=50">50</a> --%>
<%--             <a type="button" class="btn btn-default ${param.numberForEachPage == 100 ? 'active' : ''}" href="?numberForEachPage=100">100</a> --%>
<!--         </div> -->
<!-- 	</div> -->
<!--     </footer> -->
    <footer class="navbar-fixed-bottom">
    
        <div class="container text-center">
            <ul class="pagination">
                <li>
                    <a href="?action=previousPage" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              <c:forEach var="i" begin="${(currentPage - 2 > 0) ? (currentPage - 2) : 1}" end="${(currentPage + 2 < numberOfPages) ? (currentPage + 2) : numberOfPages}">
	              <li><a href="?action=numOfPage&numOfPage=${i-1}">${i}</a></li>
	          </c:forEach>
              <li>
                <a href="?action=nextPage" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>

        <div class="btn-group btn-group-sm pull-right" role="group" >
            <a class="btn btn-default " href="?action=pageSize&pageSize=10">10</a>
			<a class="btn btn-default " href="?action=pageSize&pageSize=50">50</a>
			<a class="btn btn-default " href="?action=pageSize&pageSize=100">100</a>
        </div>
		</div>
    </footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>
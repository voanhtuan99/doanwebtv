<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Detail's page</title>
    <link rel="stylesheet" href="<c:url value="/resources/admin/css/css.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/admin/css/font-awesome.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/admin/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/admin/css/table.css"/>"/>
    <style>
	    *[id$=errors] {
			color: red;
			font-style: italic;
		}
    </style>
</head>
<body>
	<div class="wrapper d-flex align-items-stretch">
			<nav id="sidebar">
				<div class="p-4 pt-5">
					<c:url var="urlImg" value="resources/admin/img/logo.jpg"/>
                    <a href="#" class="img logo rounded-circle mb-5" style="background-image: url(<c:url value="/resources/admin/img/logo.jpg"/>);"></a>
                    <ul class="list-unstyled components mb-5">
                        <li>
                            <a href="users.htm">dbo.USERS</a>
                        </li>
                        <li>
                            <a href="categories.htm">dbo.CATEGORIES</a>
                        </li>
                        <li>
                            <a href="types.htm">dbo.TYPES</a>
                        </li>
                        <li>
                            <a href="responses.htm">dbo.RESPONSES</a>
                        </li>
                        <li><a href="orders.htm">dbo.ORDERS</a></li>
						<li class="active"><a href="details.htm">dbo.DETAILS</a></li>
                    </ul>
                    <div class="footer">
                        <p>Quản lý thông tin trên database</p>
                    </div>
	            </div>
    	    </nav>

        <!-- Page Content  -->
        <div id="content" class="p-4 p-md-5">
            <h3>Thông tin dbo.USERS</h3>
            <div class="container py-5">
                <div class="row">
                    <div class="col-lg-12 mx-auto">
                        <div class="card rounded-0 border-0 shadow">
                            <div class="card-body p-5">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>IDDETAIL</th>
                                                <th>IDORDER</th>
                                                <th>IDCATE</th>
                                                <th>SO LUONG</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="u" items="${details}">
                                            <tr>
                                                <th>${u.iddetail }</th>
                                                <td>${u.order.getIdorder() }</td>
                                                <td>${u.category.getIdcate() }</td>
                                                <td>${u.quality }</td>
                                                <td>
                                                    <a href="updateDetails/${u.iddetail}.htm"><input id="edit" type="button" value="Sửa"></a>
                                                    <a href="deleteDetails/${u.iddetail}.htm"><input id="delete" type="button" value="Xóa"></a>
                                                </td>
                                            </tr>
                                            </c:forEach>
                                            <tr>
                                            	<c:url var="url" value="insertDetails.htm"/>
                                                <form:form action="${url}" modelAttribute="detail">
                                                <th>
                                                	<input type="number" placeholder="IDDETAIL" name="iddetail">
                                                	<form:errors path="iddetail"/>
                                                </th>
                                                <th>
                                                	<input type="number" placeholder="IDORDER" name="order.idorder">
                                                	<form:errors path="order.idorder"/>
                                                </th>
                                                <th>
                                                	<input type="number" placeholder="IDCATE" name="category.idcate">
                                                	<form:errors path="category.idcate"/>
                                                </th>
                                                <th>
                                                	<input type="number" placeholder="SoLuong" name="quality">
                                                	<form:errors path="quality"/>
                                                </th>
                                                <th>
                                                    <button class="btn btn-primary rounded-0 btn-block">Thêm mới</button>
                                                </th>
                                                </form:form>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>   
        </div>
	</div>

    <script src="<c:url value="/resources/admin/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/admin/js/popper.js"/>"></script>
    <script src="<c:url value="/resources/admin/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/admin/js/main.js"/>"></script>
</body>
</html>
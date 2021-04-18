<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Category's page</title>
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
				<a href="#" class="img logo rounded-circle mb-5" style="background-image: url(<c:url value="/resources/admin/img/logo.jpg"/>);"></a>
				<ul class="list-unstyled components mb-5">
					<li><a href="users.htm">dbo.USERS</a></li>
					<li class="active"><a href="admin/categories.htm">dbo.CATEGORIES</a></li>
					<li><a href="types.htm">dbo.TYPES</a></li>
					<li><a href="responses.htm">dbo.RESPONSES</a></li>
					<li><a href="orders.htm">dbo.ORDERS</a></li>
					<li><a href="details.htm">dbo.DETAILS</a></li>
				</ul>
				<div class="footer">
					<p>Quản lý thông tin trên database</p>
				</div>
			</div>
		</nav>

		<!-- Page Content  -->
		<div id="content" class="p-4 p-md-5">
			<h3>Thông tin dbo.CATEGORIES</h3>
			<div class="container py-5">
				<div class="row">
					<div class="col-lg-12 mx-auto">
						<div class="card rounded-0 border-0 shadow">
							<div class="card-body p-5">
								<div class="table-responsive">
									<table class="table">
										<thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Prices</th>
                                                <th>Discount</th>
                                                <th>New items</th>
                                                <th>Reviews</th>
                                                <th>IDType</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="c" items="${categories}">
                                            <tr>
                                                <th>${c.idcate }</th>
                                                <td>${c.namecate }</td>
                                                <td>${c.prices }</td>
                                                <td>${c.discount }</td>
                                                <td>${c.newitems }</td>
                                                <td>${c.reviews }</td>
                                                <td>${c.type.nametype }</td>
                                                <td>
                                                    <a href="updateCategories/${c.idcate}.htm"><input id="edit" type="button" value="Sửa"></a>
                                                    <a href="deleteCategories/${c.idcate}.htm"><input id="delete" type="button" value="Xóa"></a>
                                                </td>
                                            </tr>
                                            </c:forEach>
                                            <tr>
                                            	<c:url var="url" value="insertCategories.htm"/>
                                                <form:form action="${url}" modelAttribute="category">
                                                <th>
                                                	<input type="number" placeholder="ID" name="idcate">
                                                	<form:errors path="idcate"/>
                                                </th>
                                                <th>
                                                	<input type="text" placeholder="Name" name="namecate">
                                                	<form:errors path="namecate"/>
                                                </th>
                                                <th>
                                                	<input type="number" placeholder="Prices" name="prices" step="0.1">
                                                	<form:errors path="prices"/>
                                                </th>
                                                <th>
                                                	<input type="number" placeholder="Discount" name="discount">
                                                	<form:errors path="discount"/>
                                                </th>
                                                <th>
                                                	<input type="text" placeholder="Newitems" name="newitems">
                                                	<form:errors path="newitems"/>
                                                </th>
                                                <th>
                                                	<input type="text" placeholder="Reviews" name="reviews">
                                                	<form:errors path="reviews"/>
                                                </th>
                                                <th>
                                                	<select name="type.idtype">
													<c:forEach var="t" items="${listIDTypes }">
														<option value="${t.idtype }">${t.nametype }</option>			
													</c:forEach>
													</select>
                                                </th>
                                                <th>
                                                    <button class="btn btn-primary rounded-0 btn-block">Thêm mới</button>
                                                </th>
                                                <th></th>
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
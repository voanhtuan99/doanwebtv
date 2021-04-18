<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>	
	#form {
	    width: 550px;
	    border:1px solid #5f7fa7;
	    background-color: #9ab5c3;
	    border-radius: 10px;
	    margin-left: 35%;
	    margin-top: 10%;
	    align-items: center;
	    display: fixed;
	}
	*[id$=errors] {
		color: red;
		font-style: italic;
	}
	#form .lb {
	    margin: 10px;
	 	padding: 5px;
	}
	
	#form input{
	    border-radius: 5px;
	    width: 150px;
	    padding: 5px;
	    margin: 5px;
	}
	
	#form button {
		border-radius: 5px;
	    width: 150px;
	    padding: 5px;
	    margin: 5px;
	}
	
	#form #namecate {
	    width: 500px;
	}
	
</style>
<body>
	
	
	<c:url var="url" value="/admin/updateCategories.htm"/>
	<form:form action="${url}" modelAttribute="category" id="form">
		<strong>${message }</strong>
		<h5><strong>Sửa thông tin Category</strong></h5>
		<c:forEach var="u" items="${list}">
			<div hidden="true">
				<input type="text" name="idcate" value="${u.idcate}" hidden="true"/>
				<form:errors path="idcate"/>
			</div>
			
			<label class="lb">Namecate</label>	
			<div>
				<input type="text" name="namcate" value="${u.namecate}" id="namecate"/>
				<form:errors path="namecate"/>
			</div>
			
			<label class="lb">prices</label>	
			<div>
				<input type="text" name="prices" value="${u.prices}" id="prices"/>
				<form:errors path="prices"/>
			</div>
			
			<label class="lb">discount</label>	
			<div>
				<input type="text" name="discount" value="${u.discount}" id="discount"/>
				<form:errors path="discount"/>
			</div>
			
			<label class="lb">newitems</label>	
			<div>
				<input type="text" name="newitems" value="${u.newitems}" id="newitems"/>
				<form:errors path="newitems"/>
			</div>
			
			<label class="lb">reviews</label>	
			<div>
				<input type="text" name="reviews" value="${u.reviews}" id="reviews"/>
				<form:errors path="reviews"/>
			</div>
			
			<label class="lb">Type</label>	
			<div>
				<select name="type.idtype">
				<c:forEach var="t" items="${listIDTypes }">
					<option value="${t.idtype }">${t.nametype }</option>			
				</c:forEach>
				</select>
			</div>
			
			<div>
				<button name="acceptCategories">Cap nhat</button>
				<c:url var="fa" value="/admin/updateCategories/fillAgain/${u.idcate}.htm"/>
				<a href="${fa}"><input type="button" value="Nhap lai"/></a>
				<button name="exitCategories">Thoat</button>
			</div>	
		</c:forEach>
	</form:form>
</body>
</html>
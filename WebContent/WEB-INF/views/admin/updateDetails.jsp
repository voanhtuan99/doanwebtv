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
	
	#form #username {
	    width: 500px;
	}
	
</style>
<body>
	
	
	<c:url var="url" value="/admin/updateDetails.htm"/>
	<form:form action="${url}" modelAttribute="detail" id="form">
		<strong>${message }</strong>
		<h5><strong>Sửa thông tin Detail</strong></h5>
		<c:forEach var="u" items="${list}">
			<div hidden="true">
				<input type="text" name="iddetail" value="${u.iddetail}" hidden="true"/>
				<form:errors path="iddetail"/>
			</div>
			
			<label class="lb">IDOrder</label>	
			<div>
				<input type="text" name="order.idorder" value="${u.order.getIdorder()}"/>
				<form:errors path="order.idorder"/>
			</div>
			
			<label class="lb">IdCategory</label>	
			<div>
				<input type="text" name="category.idcate" value="${u.category.getIdcate()}"/>
				<form:errors path="category.idcate"/>
			</div>
			
			<label class="lb">Quality</label>	
			<div>
				<input type="text" name="quality" value="${u.quality}"/>
				<form:errors path="quality"/>
			</div>
			
			<div>
				<button name="acceptDetails">Cap nhat</button>
				<c:url var="fa" value="/admin/updateDetails/fillAgain/${u.iddetail}.htm"/>
				<a href="${fa}"><input type="button" value="Nhap lai"/></a>
				<button name="exitDetails">Thoat</button>
			</div>	
		</c:forEach>
	</form:form>
</body>
</html>
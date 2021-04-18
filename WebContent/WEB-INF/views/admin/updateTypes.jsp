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
	
	
	<c:url var="url" value="/admin/updateTypes.htm"/>
	<form:form action="${url}" modelAttribute="type" id="form">
		<strong>${message }</strong>
		<h5><strong>Sửa thông tin Type</strong></h5>
		<c:forEach var="u" items="${list}">
			<div hidden="true">
				<input type="text" name="idtype" value="${u.idtype}" hidden="true"/>
				<form:errors path="idtype"/>
			</div>
			
			<label class="lb">Nametype</label>	
			<div>
				<input type="text" name="nametype" value="${u.nametype}" id="nametype"/>
				<form:errors path="nametype"/>
			</div>
			
			<div>
				<button name="acceptTypes">Cap nhat</button>
				<c:url var="fa" value="/admin/updateTypes/fillAgain/${u.idtype}.htm"/>
				<a href="${fa}"><input type="button" value="Nhap lai"/></a>
				<button name="exitTypes">Thoat</button>
			</div>	
		</c:forEach>
	</form:form>
</body>
</html>
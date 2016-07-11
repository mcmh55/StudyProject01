<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 
<c:if test = "${empty loginUser}">
	<jsp:forward page = "login_do"/>
</c:if>
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>

<link rel="stylesheet" href="css/Login.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
<script src="js/Login.js"></script>

</head>
<body>

<!-- 백그라운드 영역 -->
<div class="box">

<h1>게시판</h1>

<h6>준비중...</h6>

</div>
<!-- //백그라운드 영역 -->

<form action="logout_do">
<input type="submit" value="로그아웃"/>
<input type="button" value="회원정보 변경"/>
</form>
  


</body>
</html>
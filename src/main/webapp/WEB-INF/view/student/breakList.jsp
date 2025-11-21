<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.pentagonUniv.domain.user.dto.PrincipalDto" %>
<%@ page import="com.example.pentagonUniv._global.utils.Define" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>휴복학 목록</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>

<body>

<table class="table">
  <thead><tr><th>ID</th><th>행위</th><th>사유</th><th>기간</th><th>상태</th><th>신청일</th></tr></thead>
  <tbody>
    <c:forEach var="b" items="${list}">
      <tr>
        <td>${b.id}</td>
        <td>${b.action}</td>
        <td>${b.reasonType}</td>
        <td>${b.fromYear}/${b.fromSemester} ~ ${b.toYear}/${b.toSemester}</td>
        <td>${b.status}</td>
        <td>${b.appDate}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>
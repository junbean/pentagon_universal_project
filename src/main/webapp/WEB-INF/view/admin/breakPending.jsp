<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.pentagonUniv.domain.user.dto.PrincipalDto" %>
<%@ page import="com.example.pentagonUniv._global.utils.Define" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>휴복학 처리</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<style>
  html, body {
    height: 100%;
  }
  body {
    padding-top: 80px;
  }
  #footer {
    margin-top: 40px;
  }
  #main-wrapper {
    min-height: calc(100vh - 200px);
  }
</style>
</head>
<body>

  <%@ include file="/WEB-INF/view/layout/header.jsp"%>
    <div id="main-wrapper" class="container my-5">
      <table class="table">
        <thead><tr><th>ID</th><th>학생</th><th>행위</th><th>사유</th><th>기간</th><th>상세사유</th><th>액션</th></tr></thead>
        <tbody>
          <c:forEach var="b" items="${list}">
        <tr>
          <td>${b.id}</td>
          <td>${b.studentId}</td>

          <!-- 행위 -->
          <td>
            <c:choose>
              <c:when test="${b.action == 'LEAVE'}">휴학</c:when>
              <c:when test="${b.action == 'RETURN'}">복학</c:when>
              <c:otherwise>${b.action}</c:otherwise>
            </c:choose>
          </td>

          <!-- 사유 -->
          <td>
            <c:choose>
              <c:when test="${b.reasonType == 'GENERAL'}">일반</c:when>
              <c:when test="${b.reasonType == 'PREGNANCY'}">임신·출산·육아</c:when>
              <c:when test="${b.reasonType == 'ILLNESS'}">질병</c:when>
              <c:when test="${b.reasonType == 'STARTUP'}">창업</c:when>
              <c:when test="${b.reasonType == 'MILITARY'}">군입대</c:when>
              <c:otherwise>${b.reasonType}</c:otherwise>
            </c:choose>
          </td>

          <td>${b.fromYear}/${b.fromSemester} ~ ${b.toYear}/${b.toSemester}</td>
          <td>${b.reasonText}</td>
          <td>
            <form method="post" action="/admin/break/${b.id}/approve" style="display:inline">
              <button class="btn btn-success btn-sm">승인</button>
            </form>
            <form method="post" action="/admin/break/${b.id}/reject" style="display:inline">
              <button class="btn btn-danger btn-sm">반려</button>
            </form>
          </td>
        </tr>
      </c:forEach>

  </tbody>
</table>
</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</body>
</html>
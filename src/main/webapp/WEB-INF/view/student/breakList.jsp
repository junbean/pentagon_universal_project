<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>휴복학 목록</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<style>
  html, body { height: 100%; }
  body {
    padding-top: 80px;
    min-height: 100vh;
    background: #f8f9fa;
    display: flex;
    flex-direction: column;
  }

  #main-wrapper {
    flex: 1;
  }
</style>
</head>

<body>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="main-wrapper" class="container my-5">

    <h3 class="mb-4">휴복학 신청 내역</h3>

    <c:choose>
        <c:when test="${empty list}">
            <div class="alert alert-secondary text-center py-4">
                신청 기록이 없습니다.
            </div>
        </c:when>

        <c:otherwise>
            <table class="table table-bordered">
              <thead>
                <tr>
                    <th>ID</th>
                    <th>행위</th>
                    <th>사유</th>
                    <th>기간</th>
                    <th>상태</th>
                    <th>신청일</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="b" items="${list}">
                  <tr>
                    <td>${b.id}</td>

                    <!-- 행위: LEAVE / RETURN -->
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

                    <!-- 상태: PENDING / APPROVED / REJECTED -->
                    <td>
                      <c:choose>
                        <c:when test="${b.status == 'PENDING'}">대기중</c:when>
                        <c:when test="${b.status == 'APPROVED'}">승인</c:when>
                        <c:when test="${b.status == 'REJECTED'}">반려</c:when>
                        <c:otherwise>${b.status}</c:otherwise>
                      </c:choose>
                    </td>

                    <td><fmt:formatDate value="${b.appDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
        </c:otherwise>
    </c:choose>

</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.pentagonUniv.domain.user.dto.PrincipalDto" %>
<%@ page import="com.example.pentagonUniv._global.utils.Define" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>휴복학 신청</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>

<body>
<form method="post" action="/student/break/apply" class="container mt-4">
  <div class="row g-2">
    <div class="col">
      <select name="action" class="form-select">
        <option value="LEAVE">휴학</option>
        <option value="RETURN">복학</option>
      </select>
    </div>
    <div class="col">
      <select name="reasonType" class="form-select">
        <option value="GENERAL">일반</option>
        <option value="PREGNANCY">임신·출산·육아</option>
        <option value="ILLNESS">질병</option>
        <option value="STARTUP">창업</option>
        <option value="MILITARY">군입대</option>
      </select>
    </div>
  </div>

  <textarea name="reasonText" class="form-control mt-3" rows="3" placeholder="상세 사유(선택)"></textarea>

  <div class="row g-2 mt-3">
    <div class="col"><input name="fromYear" class="form-control" placeholder="시작년도"></div>
    <div class="col"><input name="fromSemester" class="form-control" placeholder="시작학기(1/2)"></div>
    <div class="col"><input name="toYear" class="form-control" placeholder="종료년도"></div>
    <div class="col"><input name="toSemester" class="form-control" placeholder="종료학기(1/2)"></div>
  </div>

  <button class="btn btn-primary mt-3">신청</button>
</form>

</body>
</html>
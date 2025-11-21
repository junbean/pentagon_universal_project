<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>학생 등록</title>
	  <style>

    .input-form {
      margin-top : 20px;
	    max-width: 680px;
      padding: 32px;

      background: #fff;
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
      -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
    }
  </style>
</head>
<body>
        <%@ include file="/WEB-INF/view/layout/header.jsp"%>
    	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>학생 등록 페이지</h2>
		</div>
        </div>
        <div class="container mb-5">
            <div class="row">
                <%@ include file="/WEB-INF/view/layout/sidebarCdrList.jsp"%>
                <div class="col-md-10">
				<div class="input-form col-md-12 mx-auto">
        <h4 class="mb-3">학생 등록</h4>
        <form class="validation-form" action="/student/studentRegister" method="post">

    <!-- 이름 & 학번 -->
    <div class="row">
        <div class="col-md-6 mb-3">
            <label>유저 학번</label>
            <input type="text" class="form-control" name="userNumber" placeholder="자동 배정됩니다" readonly>
        </div>

        <div class="col-md-6 mb-3">
            <label>이름</label>
            <input type="text" class="form-control" name="name" required>
        </div>
    </div>

    <!-- 생년월일 (BirthDate) -->
    <div class="row">
        <label>생년월일</label>
        <div class="col-md-3 mb-3">
            <select class="form-select" name="birthYear" required>
                <option value="">년도</option>
                <c:forEach var="y" begin="1980" end="2025">
                    <option value="${y}">${y}</option>
                </c:forEach>
            </select>
        </div>

        <div class="col-md-3 mb-3">
            <select class="form-select" name="birthMonth" required>
                <option value="">월</option>
                <c:forEach var="m" begin="1" end="12">
                    <option value="${m}">${m}</option>
                </c:forEach>
            </select>
        </div>

        <div class="col-md-3 mb-3">
            <select class="form-select" name="birthDay" required>
                <option value="">일</option>
                <c:forEach var="d" begin="1" end="31">
                    <option value="${d}">${d}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <!-- 성별 -->
    <div class="mb-3">
        <label>성별</label>
        <select class="form-select form-select-sm" name="gender" required>
            <option value="">성별 선택</option>
            <option value="남자">남자</option>
            <option value="여자">여자</option>
        </select>
    </div>

    <!-- 주소 -->
    <div class="mb-3">
        <label>주소</label>
        <input type="text" class="form-control" name="address" required>
    </div>

    <!-- 상세주소 -->
    <div class="mb-3">
        <label>상세 주소</label>
        <input type="text" class="form-control" name="address">
    </div>

    <!-- 전화번호 -->
    <div class="mb-3">
        <label>전화번호</label>
        <div class="row">
            <div class="col-md-3">
                <input type="text" class="form-control tel-input" maxlength="3" name="tel" required>
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control tel-input" maxlength="4" name="tel" required>
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control tel-input" maxlength="4" name="tel" required>
            </div>
        </div>
    </div>

    <!-- 학년 / 학기 -->
    <div class="row">
        <div class="col-md-3 mb-3">
            <label>학년</label>
            <select class="form-select" name="grade" required>
                <option value="">학년</option>
                <option value="1">1학년</option>
                <option value="2">2학년</option>
                <option value="3">3학년</option>
                <option value="4">4학년</option>
            </select>
        </div>

        <div class="col-md-3 mb-3">
            <label>학기</label>
            <select class="form-select" name="semester" required>
                <option value="">학기</option>
                <option value="1">1학기</option>
                <option value="2">2학기</option>
            </select>
        </div>

        <!-- 학과 deptId -->
        <div class="col-md-6 mb-3">
            <label>학과</label>
            <select class="form-select" name="deptId" required>
                <option value="">학과 선택</option>
                <c:forEach var="d" items="${deptList}">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <!-- 이메일 -->
    <div class="mb-3">
        <label>이메일</label>
        <input type="email" class="form-control" name="email" required>
    </div>

    <button class="btn btn-primary btn-lg btn-block" type="submit">학생 등록 완료</button>
</form>
      </div>
			</div>
            </div>
        </div>
        </div>

        
	</div>
</body>
</html>

        <%@ include file="/WEB-INF/view/layout/footer.jsp"%>

<script>
document.querySelectorAll(".tel-input").forEach(input => {
    input.addEventListener("input", () => {
        input.value = input.value.replace(/[^0-9]/g, "");
    });
});
</script>
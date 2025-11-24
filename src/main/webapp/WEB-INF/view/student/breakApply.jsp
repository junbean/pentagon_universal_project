<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>휴복학 신청</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<style>
  html, body { height: 100%; margin: 0; padding: 0; }

  body {
    padding-top: 80px;
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    background: #f8f9fa;
  }

  #main-wrapper {
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: flex-start;
    padding: 40px 0;
  }

  .apply-card {
      width: 100%;
      max-width: 650px;
      padding: 35px 40px;
      border-radius: 12px;
      background: #ffffff;
      box-shadow: 0 4px 20px rgba(0,0,0,.08);
  }

  .btn-main {
      width: 100%;
      background-color: #5fcf80 !important;
      color: white !important;
      border: none;
      font-size: 17px;
      padding: 10px 0;
      border-radius: 6px;
  }

  .btn-main:hover {
      background-color: #4cb76a !important;
  }

</style>
</head>

<body>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>

<div id="main-wrapper">
    <div class="apply-card">

        <h3 class="text-center mb-4">휴학 / 복학 신청</h3>

        <form method="post" action="/student/break/apply" onsubmit="return validateBreakForm()">

            <div class="form-row mb-3">
                <div class="col">
                    <select name="action" class="form-control">
                        <option value="LEAVE">휴학</option>
                        <option value="RETURN">복학</option>
                    </select>
                </div>
                <div class="col">
                    <select name="reasonType" class="form-control">
                        <option value="GENERAL">일반</option>
                        <option value="PREGNANCY">임신·출산·육아</option>
                        <option value="ILLNESS">질병</option>
                        <option value="STARTUP">창업</option>
                        <option value="MILITARY">군입대</option>
                    </select>
                </div>
            </div>

            <textarea name="reasonText" class="form-control mb-3"
                      rows="3" placeholder="상세 사유(선택)"></textarea>

            <div class="form-row mb-3">
                <div class="col"><input name="fromYear" class="form-control" placeholder="시작년도"></div>
                <div class="col"><input name="fromSemester" class="form-control" placeholder="시작학기 (1 or 2)"></div>
                <div class="col"><input name="toYear" class="form-control" placeholder="종료년도"></div>
                <div class="col"><input name="toSemester" class="form-control" placeholder="종료학기 (1 or 2)"></div>
            </div>

            <button class="btn btn-main mt-2">신청하기</button>

        </form>
    </div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>

</body>
<script>
function validateBreakForm() {
    const year = document.querySelector("input[name='fromYear']").value.trim();
    const sem  = document.querySelector("input[name='fromSemester']").value.trim();

    if (year === "" || sem === "") {
        alert("시작년도와 시작학기는 반드시 입력해야 합니다.");
        return false;
    }

    return true;
}
</script>
</html>

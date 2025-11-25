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

            <!-- 액션 선택 -->
            <div class="form-row mb-3">
                <div class="col">
                    <select name="action" id="breakAction" class="form-control" onchange="toggleBreakForm()">
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

            <!-- 기간 / 복학 학기 -->
            <div class="form-row mb-3" id="periodRow">
                <div class="col">
                    <input name="fromYear" class="form-control"
                           placeholder="시작년도 / 복학년도">
                </div>
                <div class="col">
                    <input name="fromSemester" class="form-control"
                           placeholder="시작학기 / 복학학기 (1 or 2)">
                </div>
                <div class="col" id="toYearCol">
                    <input name="toYear" class="form-control"
                           placeholder="종료년도">
                </div>
                <div class="col" id="toSemCol">
                    <input name="toSemester" class="form-control"
                           placeholder="종료학기 (1 or 2)">
                </div>
            </div>

            <button class="btn btn-main mt-2">신청하기</button>

        </form>
    </div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>

<script>
function toggleBreakForm() {
    const action   = document.getElementById("breakAction").value;
    const toYearCol = document.getElementById("toYearCol");
    const toSemCol  = document.getElementById("toSemCol");

    if (action === 'LEAVE') {
        // 휴학: 종료 연/학기 입력 노출
        toYearCol.style.display = '';
        toSemCol.style.display  = '';
    } else {
        // 복학: 종료 연/학기 입력 숨김
        toYearCol.style.display = 'none';
        toSemCol.style.display  = 'none';

        // 값도 지워두는 편이 깔끔
        document.querySelector("input[name='toYear']").value = '';
        document.querySelector("input[name='toSemester']").value = '';
    }
}

function validateBreakForm() {
    const action = document.getElementById("breakAction").value;

    const fromYear = document.querySelector("input[name='fromYear']").value.trim();
    const fromSem  = document.querySelector("input[name='fromSemester']").value.trim();
    const toYear   = document.querySelector("input[name='toYear']").value.trim();
    const toSem    = document.querySelector("input[name='toSemester']").value.trim();

    if (action === 'LEAVE') {
        // 휴학: 시작/종료 모두 필수
        if (fromYear === "" || fromSem === "" || toYear === "" || toSem === "") {
            alert("휴학 신청 시 시작/종료 연도와 학기를 모두 입력하세요.");
            return false;
        }
    } else { // RETURN
        // 복학: 복학할 연/학기만 필수
        if (fromYear === "" || fromSem === "") {
            alert("복학 신청 시 복학할 연도와 학기를 입력하세요.");
            return false;
        }
    }

    return true;
}

// 첫 로딩 시 현재 액션 값 기준으로 폼 상태 세팅
document.addEventListener('DOMContentLoaded', toggleBreakForm);
</script>

</body>
</html>

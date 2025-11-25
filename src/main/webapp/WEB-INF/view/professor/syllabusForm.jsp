<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<style>
    textarea.form-control {
        resize: none;
        height: 120px;
    }

    .week-textarea {
        resize: none;
        height: 70px !important;
    }

    .syllabus-btn {
        padding: 5px 12px;
        font-size: 14px;
        border-radius: 6px;
        border: none !important;
        height: 38px;
        line-height: 26px;
    }

    .complete-btn {
        background: #5fcf80;
        color: white !important;
    }

    .complete-btn:hover {
        background: #4bb96a;
    }
</style>

<body>

<main id="main">

    <!-- ===================== 강의계획서 작성 Form ===================== -->
    <section id="syllabus-create" class="courses" style="padding-top: 150px;">
        <div class="container">

            <div class="section-title">
                <h2>Syllabus</h2>
                <p>강의계획서 등록</p>
            </div>

            <form action="/professor/syllabus/create/${subject.id}" method="post">

                <input type="hidden" name="subjectId" value="${subject.id}">

                <!-- 기본 정보 -->
                <div class="card mb-4">
                    <div class="card-header fw-bold">기본정보</div>
                    <div class="card-body">

                        <div class="mb-3">
                            <label class="form-label">과목명</label>
                            <input type="text" class="form-control" value="${subject.name}" readonly>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">수업 개요</label>
                            <textarea name="overview" class="form-control" required>${syllabus.overview}</textarea>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">강의 목표</label>
                            <textarea name="objective" class="form-control" required>${syllabus.objective}</textarea>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">교재</label>
                            <input type="text" name="textbook" class="form-control" value="${syllabus.textbook}" required>
                        </div>

                    </div>
                </div>

                <!-- 주차별 계획 -->
                <div class="card mb-4">
                    <div class="card-header fw-bold">주차별 수업계획 (1~15주)</div>
                    <div class="card-body">

                        <div class="row">
                            <c:forEach var="i" begin="1" end="15">
                                <c:set var="weekKey" value="${'week' += i}" />
                                <div class="col-12 mb-3">
                                    <label class="form-label">${i}주차</label>
                                    <textarea name="week${i}" class="form-control week-textarea" required>${syllabus[weekKey]}</textarea>
                                </div>
                            </c:forEach>
                        </div>

                    </div>
                </div>

                <!-- 버튼 -->
                <div class="text-center mb-5">
                    <button type="submit" name="status" value="TEMP" class="btn btn-secondary syllabus-btn me-2" onclick="tempSave()">
                        임시저장
                    </button>
                    <button type="submit" name="status" value="COMPLETE" class="syllabus-btn complete-btn" onclick="return confirmSubmit();">
                        최종 제출
                    </button>
                </div>

            </form>

        </div>
    </section>
    <!-- ===================== END ===================== -->

</main>

<script>
    function confirmSubmit() {
        // 최종제출 시
        completeSave();

        return confirm("정말 최종 제출하시겠습니까?\n제출 후 수정할 수 없습니다");
    }

    function tempSave() {
        // 모든 required 제거
        document.querySelectorAll("[required]").forEach(el => {
            el.dataset.required = "true";   // 나중에 다시 복구용 저장
            el.removeAttribute("required");
        });
    }

    function completeSave() {
        // required 원래대로 복구
        document.querySelectorAll("[data-required]").forEach(el => {
            el.setAttribute("required", "true");
        });
    }
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

</body>
</html>

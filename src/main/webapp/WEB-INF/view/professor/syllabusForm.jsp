<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<body>

<!-- ======= Hero Section ======= -->
<section id="hero" class="d-flex justify-content-center align-items-center">
    <div class="container position-relative" data-aos="zoom-in" data-aos-delay="100">
        <h1>
            강의계획서 작성<br>
            <span style="font-size: 22px;">${subject.name}</span>
        </h1>
    </div>
</section>
<!-- End Hero -->

<main id="main">

    <!-- ===================== 강의계획서 작성 Form ===================== -->
    <section id="syllabus-create" class="courses">
        <div class="container" data-aos="fade-up">

            <div class="section-title">
                <h2>Syllabus</h2>
                <p>강의계획서 등록</p>
            </div>

            <form action="/professor/syllabus/create" method="post" enctype="multipart/form-data">

                <input type="hidden" name="subject_id" value="${subject.id}">

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
                            <textarea name="overview" class="form-control" rows="3" required></textarea>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">강의 목표</label>
                            <textarea name="objective" class="form-control" rows="3" required></textarea>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">교재</label>
                            <input type="text" name="textbook" class="form-control" required>
                        </div>

                    </div>
                </div>

                <!-- 주차별 계획 -->
                <div class="card mb-4">
                    <div class="card-header fw-bold">주차별 수업계획 (1~15주)</div>
                    <div class="card-body">

                        <div class="row">
                            <c:forEach var="i" begin="1" end="15">
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">${i}주차</label>
                                    <textarea name="week${i}" class="form-control" rows="2" required></textarea>
                                </div>
                            </c:forEach>
                        </div>

                    </div>
                </div>

                <!-- 버튼 -->
                <div class="text-center mb-5">
                    <button type="submit" name="status" value="TEMP" class="btn btn-secondary me-2">
                        임시저장
                    </button>
                    <button type="submit" name="status" value="COMPLETE" class="btn btn-primary">
                        최종 제출
                    </button>
                </div>

            </form>

        </div>
    </section>
    <!-- ===================== END ===================== -->

</main>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

</body>
</html>

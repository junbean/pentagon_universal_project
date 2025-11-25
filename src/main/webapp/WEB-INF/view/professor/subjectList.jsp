<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/view/layout/header.jsp" />
<style>
    html, body {
        height: 100%;
    }

    body {
        padding-top: 90px !important;
    }

    #content-wrapper {
        min-height: calc(100vh - 200px);
        display: flex;
        flex-direction: column;
    }

    footer {
        margin-top: auto;
    }

    .subject-card {
        border-radius: 10px;
        padding: 20px;
        background: white;
        box-shadow: 0 3px 10px rgba(0,0,0,0.08);
        margin-bottom: 50px;
    }

    .subject-table th {
        background-color: #5fcf80;
        color: white;
        text-align: center;
        vertical-align: middle;
    }

    .subject-table td {
        vertical-align: middle;
        text-align: center;
    }

    .subject-table tbody tr:hover {
        background-color: #f1f9f3;
        transition: 0.2s;
    }

    .write-btn, .view-btn {
        padding: 5px 12px;
        background: #5fcf80;
        color: white !important;
        border-radius: 6px;
        text-decoration: none;
        font-size: 14px;
    }

    .write-btn:hover, .view-btn:hover {
        background: #4bb96a;
    }

    .done-text {
        color: #888;
        font-weight: 600;
    }
</style>

<c:if test="${not empty message}">
    <script>
        alert("${message}");
    </script>
</c:if>

<body id="page-top">

<main id="main" class="mt-5 pt-4"> <!-- 헤더 높이만큼 여백 -->

    <div id="wrapper" class="container-fluid">
        <div class="row">

            <%@ include file="../layout/professorAsidebar.jsp" %>

            <!-- Content -->
            <div id="content-wrapper" class="col-md-10 d-flex flex-column">
                <div id="content">

                    <div class="container mt-4">

                        <h2 class="mb-4 fw-bold text-center">강의 목록</h2>

                        <div class="subject-card">

                            <table class="table table-bordered subject-table">
                                <thead>
                                <tr>
                                    <th>번호</th>
                                    <th>과목코드</th>
                                    <th>강의명</th>
                                    <th>강의구분</th>
                                    <th>강의 시간</th>
                                    <th>이수학점</th>
                                    <th>강의계획서</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach var="subject" items="${subjectList}" varStatus="status">
                                    <tr>
                                        <td>${status.index + 1}</td>
                                        <td>${subject.id}</td>
                                        <td>${subject.name}</td>
                                        <td>${subject.type}</td>
                                        <td>${subject.subYear}학년도 / ${subject.semester}학기 / ${subject.subDay}요일</td>
                                        <td>${subject.grades}</td>

                                        <td>
                                            <c:if test="${subject.syllabus eq false}">
                                                <a href="/professor/syllabus/create/${subject.id}" class="write-btn">작성</a>
                                            </c:if>

                                            <c:if test="${subject.syllabus eq true}">
                                                <span class="done-text">등록 완료</span>
                                                <a href="/professor/syllabus/view/${subject.id}"
                                                   target="_blank"
                                                   class="view-btn ms-2">조회</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>

                            </table>
                        </div>

                    </div>

                </div>
            </div>

        </div>
    </div>

</main>

<!-- Footer -->
<%@ include file="../layout/footer.jsp" %>
<!-- End Footer -->

</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/view/layout/header.jsp" />

<body id="page-top">
<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="/WEB-INF/view/layout/professorAsidebar.jsp" />
    <!-- End Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">
            <div class="container mt-4">
                <h2 class="mb-4 fw-bold text-center">강의 목록</h2>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>과목코드</th>
                            <th>강의명</th>
                            <th>강의 구분</th>
                            <th>강의 시간</th>
                            <th>이수 학점</th>
                            <th>강의 계획서</th>
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
                                    <a href="/professor/syllabus/create/${subject.id}">작성</a>
                                </c:if>

                                <c:if test="${subject.syllabus eq true}">
                                    <span class="text-muted">완료</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>

        </div>
        <!-- End Main Content -->

        <!-- Footer -->
        <jsp:include page="/WEB-INF/view/layout/footer.jsp" />
        <!-- End Footer -->

    </div>
</div>

</body>
</html>
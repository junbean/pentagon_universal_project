<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>학생 등록</title>
    <style>
      .input-form {
        margin-top: 20px;
        max-width: 680px;
        padding: 32px;

        background: #fff;
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        border-radius: 10px;
        -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
        -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
        box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      }
    </style>
  </head>
  <body>
    <%@ include file="/WEB-INF/view/layout/header.jsp"%>
    <div class="breadcrumbs" data-aos="fade-in">
      <div class="container">
        <h2>학생 명단</h2>
      </div>
    </div>
    <div class="container mb-5">
      <div class="row">
        <%@ include file="/WEB-INF/view/layout/sidebarCdrList.jsp"%>
        <div class="col-md-10">
          <div class="input-form col-md-12 mx-auto">
            <h4 class="mb-3">학생 명단</h4>
            <div
              class="d-flex flex-wrap justify-content-end m-3 gap-2 align-items-center">
              <select
                class="form-select form-select-sm w-auto"
                aria-label="Small select example">
                <option selected>단과</option>
                <c:forEach var="college" items="${collegeList}">
                  <option value="${college.id}">${college.name}</option>
                </c:forEach>
              </select>
              <select
                class="form-select form-select-sm w-auto"
                aria-label="Small select example">
                <option selected>학과</option>
                <c:forEach var="dept" items="${deptList}">
                  <option value="${dept.id}">${dept.name}</option>
                </c:forEach>
              </select>
              <select
                class="form-select form-select-sm w-auto"
                aria-label="Small select example">
                <option selected>학년</option>
              </select>
              <select
                class="form-select form-select-sm w-auto"
                aria-label="Small select example">
                <option selected>학기</option>
              </select>
              <select
                class="form-select form-select-sm w-auto"
                aria-label="Small select example">
                <option selected>학적</option>
                <c:forEach var="studentStatus" items="${studentStatus}">
                  <option value="${studentStatus}">
                    ${studentStatus.displayName}
                  </option>
                </c:forEach>
              </select>

              <div class="input-group input-group-sm w-auto">
                <input
                  type="text"
                  class="form-control"
                  placeholder="학번 또는 이름 입력" />
                <span class="input-group-text">
                  <i class="bi bi-search"></i>
                </span>
              </div>
            </div>
            <div class="text-center">
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">No</th>
                    <th scope="col">이름</th>
                    <th scope="col">학번</th>
                    <th scope="col">단과</th>
                    <th scope="col">학과</th>
                    <th scope="col">학년</th>
                    <th scope="col">학기</th>
                    <th scope="col">학적 상태</th>
                  </tr>
                </thead>
                <tbody class="table-group-divider">
                  <c:forEach
                    var="student"
                    items="${studentList}"
                    varStatus="status">
                    <tr>
                      <th scope="row">${status.index + 1}</th>
                      <td>${student.name}</td>
                      <td>${student.userNumber}</td>
                      <td>${student.collegeName}</td>
                      <td>${student.deptName}</td>
                      <td>${student.grade}</td>
                      <td>${student.semester}</td>
                      <td>${student.studentStatus.displayName}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
            <div class="text-center">
              <nav>
                <ul class="pagination justify-content-center">
                  <!-- 이전 블록 -->
                  <c:if test="${page.startPage > 1}">
                    <li class="page-item">
                      <a class="page-link" href="?page=${page.startPage - 1}"
                        >&laquo;</a
                      >
                    </li>
                  </c:if>

                  <!-- 이전 페이지 -->
                  <c:if test="${page.currentPage > 1}">
                    <li class="page-item">
                      <a class="page-link" href="?page=${page.currentPage - 1}"
                        >&lsaquo;</a
                      >
                    </li>
                  </c:if>

                  <!-- 페이지 번호 출력 -->
                  <c:forEach
                    var="i"
                    begin="${page.startPage}"
                    end="${page.endPage}">
                    <li
                      class="page-item ${i == page.currentPage ? 'active' : ''}">
                      <a class="page-link" href="?page=${i}">${i}</a>
                    </li>
                  </c:forEach>

                  <!-- 다음 페이지 -->
                  <c:if test="${page.currentPage < page.totalPage}">
                    <li class="page-item">
                      <a class="page-link" href="?page=${page.currentPage + 1}"
                        >&rsaquo;</a
                      >
                    </li>
                  </c:if>

                  <!-- 다음 블록 -->
                  <c:if test="${page.endPage < page.totalPage}">
                    <li class="page-item">
                      <a class="page-link" href="?page=${page.endPage + 1}"
                        >&raquo;</a
                      >
                    </li>
                  </c:if>
                </ul>
              </nav>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

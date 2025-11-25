<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <style>
        body {
            font-family: "NotoSansKR-Regular";
            padding: 18px;
            font-size: 14px;
        }

        h1 {
            text-align: center;
            margin-bottom: 25px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 25px;
        }

        th, td {
            border: 1px solid #444;
            padding: 6px;
            vertical-align: top;
        }

        .header-cell {
            background: #e2e2e2;
            font-family: "NotoSansKR-Medium";
            font-weight: normal;
            text-align: center;
            font-size: 13px;
        }

        .section-title {
            font-size: 16px;
            font-family: "NotoSansKR-Medium";
            margin-top: 30px;
            margin-bottom: 10px;
        }

        /* multi-page table for weekly plan */
        @page { size: A4; margin: 10mm; }

        table.weekly {
            width: 100%;
            border-collapse: collapse;
            font-size: 13px;

            -fs-table-paginate: paginate;
            -fs-page-break-min-height: 1.5cm;
        }

        table.weekly th,
        table.weekly td {
            border: 1px solid #555;
            padding: 6px;
        }


        .header-row {
            background: #e2e2e2;
            font-family: "NotoSansKR-Medium";
            text-align: center;
            page-break-inside: avoid;
        }

        table.weekly tfoot td {
            background: #f4f4f4;
            text-align: center;
        }

        tr, tfoot {
            page-break-inside: avoid;
        }
        tfoot {
            font-family: "NotoSansKR-Medium";
        }

        .weekly-title {
            break-before: page;
            page-break-before: always;
        }
    </style>
</head>

<body>

<h1>강의계획서</h1>

<!-- 기본 정보 -->
<table>
    <tr>
        <td class="header-cell">강의명</td>
        <td>${subjectName}</td>
        <td class="header-cell">담당 교수</td>
        <td>${professorName}</td>
    </tr>
    <tr>
        <td class="header-cell">학과명</td>
        <td>${departmentName}</td>
        <td class="header-cell">강의 구분</td>
        <td>${lectureType}</td>
    </tr>
    <tr>
        <td class="header-cell">연도</td>
        <td>${year} 학년도</td>
        <td class="header-cell">학기</td>
        <td>${semester} 학기</td>
    </tr>
    <tr>
        <td class="header-cell">요일</td>
        <td>${dayOfWeek}요일</td>
        <td class="header-cell">강의 시간</td>
        <td>${startTime} 시 ~ ${endTime} 시</td>
    </tr>
    <tr>
        <td class="header-cell">이수학점</td>
        <td>${credit}</td>
        <td class="header-cell">수강 정원</td>
        <td>${capacity}</td>
    </tr>
</table>

<!-- 수업 개요 -->
<div class="section-title">수업 개요</div>
<table>
    <tr><td>${overview}</td></tr>
</table>

<!-- 강의 목표 -->
<div class="section-title">강의 목표</div>
<table>
    <tr><td>${objective}</td></tr>
</table>

<!-- 교재 -->
<div class="section-title">교재</div>
<table>
    <tr><td>${textbook}</td></tr>
</table>

<!-- 주차별 계획 -->
<div class="section-title weekly-title">주차별 강의 계획 (1~15주)</div>

<table class="weekly">
    <tr class="header-row">
        <td class="header-cell" style="width: 10%;">주차</td>
        <td class="header-cell" style="width: 90%;">강의 내용</td>
    </tr>

    <tbody>
    <tr><td>1주차</td><td>${week1}</td></tr>
    <tr><td>2주차</td><td>${week2}</td></tr>
    <tr><td>3주차</td><td>${week3}</td></tr>
    <tr><td>4주차</td><td>${week4}</td></tr>
    <tr><td>5주차</td><td>${week5}</td></tr>
    <tr><td>6주차</td><td>${week6}</td></tr>
    <tr><td>7주차</td><td>${week7}</td></tr>
    <tr><td>8주차</td><td>${week8}</td></tr>
    <tr><td>9주차</td><td>${week9}</td></tr>
    <tr><td>10주차</td><td>${week10}</td></tr>
    <tr><td>11주차</td><td>${week11}</td></tr>
    <tr><td>12주차</td><td>${week12}</td></tr>
    <tr><td>13주차</td><td>${week13}</td></tr>
    <tr><td>14주차</td><td>${week14}</td></tr>
    <tr><td>15주차</td><td>${week15}</td></tr>
    </tbody>

    <tfoot>
    <tr><td colspan="2">※ 본 강의계획은 사정에 따라 변경될 수 있습니다.</td></tr>
    </tfoot>
</table>

</body>
</html>

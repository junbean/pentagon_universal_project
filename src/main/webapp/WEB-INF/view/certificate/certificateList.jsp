<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<style>
    .certificate-list-container {
        max-width: 1000px;
        margin: 50px auto;
    }

    .table-responsive {
        margin-top: 30px;
    }

    .table {
        border-collapse: collapse;
    }

    .table thead th {
        background-color: #f2f2f2;
        color: #333;
        padding: 12px;
        text-align: center;
        border: 1px solid #ddd;
    }

    .table tbody td {
        padding: 12px;
        text-align: center;
        border: 1px solid #ddd;
    }

    .table tbody tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .table tbody tr:hover {
        background-color: #f0f0f0;
    }

    .status-badge {
        display: inline-block;
        padding: 5px 10px;
        border-radius: 4px;
        font-size: 12px;
        font-weight: bold;
    }

    .status-issued {
        background-color: #d4edda;
        color: #155724;
    }

    .status-cancelled {
        background-color: #f8d7da;
        color: #721c24;
    }

    .download-btn {
        padding: 6px 12px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 12px;
    }

    .download-btn:hover {
        background-color: #0056b3;
    }

    .empty-message {
        text-align: center;
        padding: 40px;
        color: #666;
        font-size: 16px;
    }

    .issue-btn {
        background-color: #28a745;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-top: 20px;
    }

    .issue-btn:hover {
        background-color: #218838;
    }

    #main {
        padding-top: 72.5px;
    }

</style>

<main id="main">
    <div class="certificate-list-container" data-aos="fade-up">
        <div class="section-title">
            <h2>발급 내역</h2>
            <p>발급받은 증명서 목록입니다.</p>
        </div>

        <div id="certificateList"></div>

        <button class="issue-btn" onclick="goToIssue()">새 증명서 발급</button>
    </div>
</main>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

<script>
    const studentId = '${principal.id}';
    document.addEventListener('DOMContentLoaded', function() {
        loadCertificateList();
    });

    /**
     * 증명서 목록 조회
     */
    function loadCertificateList() {
        fetch('/certificate/api/list/' + studentId)
            .then(response => response.json())
            .then(data => {
                console.log('받은 응답:', data);
                displayCertificateList(data.data || data);
            })
            .catch(error => {
                console.error('Error:', error);
                document.getElementById('certificateList').innerHTML = '<div class="empty-message">목록을 불러올 수 없습니다.</div>';
            });
    }

    /**
     * 증명서 목록 화면에 표시
     */
    function displayCertificateList(certificates) {
        console.log('받은 데이터:', certificates);
        console.log('데이터 타입:', typeof certificates);
        console.log('배열 여부:', Array.isArray(certificates));


        const certificateList = document.getElementById('certificateList');

        if (!certificates || certificates.length === 0) {
            certificateList.innerHTML = '<div class="empty-message">발급된 증명서가 없습니다.</div>';
            return;
        }

        let html = '<div class="table-responsive"><table class="table">';
        html += '<thead><tr>';
        html += '<th>증명서 타입</th>';
        html += '<th>발급일</th>';
        html += '<th>상태</th>';
        html += '<th>다운로드</th>';
        html += '</tr></thead>';
        html += '<tbody>';

        certificates.forEach(cert => {
            const statusClass = cert.status === '발급완료' ? 'status-issued' : 'status-cancelled';
            html += '<tr>';
            html += '<td>' + cert.certificateType + '</td>';
            html += '<td>' + cert.issuedDate + '</td>';
            html += '<td><span class="status-badge ' + statusClass + '">' + cert.status + '</span></td>';
            html += '<td><button class="download-btn" data-id="' + cert.id + '" data-type="' + cert.certificateType + '">다운로드</button></td>';
            html += '</tr>';
        });

        html += '</tbody></table></div>';
        certificateList.innerHTML = html;

        certificateList.addEventListener('click', function(e) {
            if (e.target.classList.contains('download-btn')) {
                const certificateId = e.target.getAttribute('data-id');
                const certificateType = e.target.getAttribute('data-type');
                downloadCertificate(certificateId, certificateType);
            }
        });

    }

    /**
     * 증명서 PDF 다운로드
     */
    function downloadCertificate(certificateId, certificateType) {
        location.href = '/certificate/download/' + certificateId + '?type=' + encodeURIComponent(certificateType);

    }

    /**
     * 증명서 발급 페이지로 이동
     */
    function goToIssue() {
        location.href = '/certificate/issue';
    }
</script>
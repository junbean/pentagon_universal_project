<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<style>
    .certificate-container {
        max-width: 900px;
        margin: 50px auto;
    }

    .certificate-grid {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 20px;
        margin-bottom: 30px;
    }

    .icon-box {
        padding: 30px;
        text-align: center;
        border: 2px solid #007bff;
        border-radius: 8px;
        background-color: white;
        cursor: pointer;
        transition: all 0.3s ease;
    }

    .icon-box:hover {
        background-color: #007bff;
        color: white;
        border-color: #0056b3;
    }

    .icon-box h3 {
        margin-top: 15px;
        font-size: 18px;
        font-weight: bold;
    }

    .icon-box i {
        font-size: 40px;
        color: #007bff;
    }

    .icon-box:hover i {
        color: white;
    }

    .success-message {
        display: none;
        background-color: #d4edda;
        color: #155724;
        padding: 15px;
        border-radius: 4px;
        margin-bottom: 20px;
        border: 1px solid #c3e6cb;
    }

    .success-message.show {
        display: block;
    }

    .error-message {
        display: none;
        background-color: #f8d7da;
        color: #721c24;
        padding: 15px;
        border-radius: 4px;
        margin-bottom: 20px;
        border: 1px solid #f5c6cb;
    }

    .error-message.show {
        display: block;
    }

    .view-list-btn {
        display: block;
        width: 100%;
        padding: 12px;
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 4px;
        font-size: 16px;
        cursor: pointer;
        margin-top: 20px;
    }

    .view-list-btn:hover {
        background-color: #218838;
    }

    #main {
        padding-top: 72.5px;
    }
</style>

<main id="main">
    <div class="certificate-container" data-aos="fade-up">
        <div class="section-title">
            <h2>증명서 발급</h2>
            <p>필요한 증명서를 선택하여 발급받으세요.</p>
        </div>

        <div class="success-message" id="successMsg"></div>
        <div class="error-message" id="errorMsg"></div>

        <div class="row certificate-grid">
            <div class="col-lg-6" onclick="issueCertificate('재학증명서')">
                <div class="icon-box">
                    <i class="ri-award-line"></i>
                    <h3>재학증명서</h3>
                </div>
            </div>

            <div class="col-lg-6" onclick="issueCertificate('성적증명서')">
                <div class="icon-box">
                    <i class="ri-bar-chart-2-line"></i>
                    <h3>성적증명서</h3>
                </div>
            </div>

            <div class="col-lg-6" onclick="issueCertificate('등록금고지서')">
                <div class="icon-box">
                    <i class="ri-receipt-line"></i>
                    <h3>등록금고지서</h3>
                </div>
            </div>

            <div class="col-lg-6" onclick="issueCertificate('졸업증명서')">
                <div class="icon-box">
                    <i class="ri-graduation-cap-line"></i>
                    <h3>졸업증명서</h3>
                </div>
            </div>
        </div>

        <button class="view-list-btn" onclick="goToList()">발급 내역 조회</button>
    </div>
</main>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

<script>
    function issueCertificate(certificateType) {
        const successMsg = document.getElementById('successMsg');
        const errorMsg = document.getElementById('errorMsg');

        successMsg.classList.remove('show');
        errorMsg.classList.remove('show');

        fetch('/certificate/api/issue', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'certificateType=' + encodeURIComponent(certificateType)
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                successMsg.textContent = '✅ ' + data.message;
                successMsg.classList.add('show');

                setTimeout(() => {
                    goToList();
                }, 2000);
            } else {
                errorMsg.textContent = '❌ ' + data.message;
                errorMsg.classList.add('show');
            }
        })
        .catch(error => {
            errorMsg.textContent = '❌ 통신 오류가 발생했습니다.';
            errorMsg.classList.add('show');
            console.error('Error:', error);
        });
    }

    function goToList() {
        location.href = '/certificate/list';
    }
</script>
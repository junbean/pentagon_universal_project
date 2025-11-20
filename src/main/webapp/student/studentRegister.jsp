<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>학생 등록</title>
</head>
<body>
        <%@ include file="/WEB-INF/view/layout/header.jsp"%>
    	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>학생 등록 페이지</h2>
		</div>
        </div>
        <div class="container mb-5">
            <div class="row">
                <%@ include file="/WEB-INF/view/layout/sidebarCdrList.jsp"%>
                <div class="col-md-10">
				<section id="pricing" class="pricing" style="height:632px;">
                    <!-- 학생 등록 페이지 -->
					<form action="/college/collegeRegister" method="post">
						<div class="container" data-aos="fade-up">

							<div class="row" style="justify-content: center;">

								<div class="col-lg-4 col-md-6">
									<div class="box" style="margin-top: 13%;">
										<h3 style="text-align: center;">학생 등록 페이지</h3>
										<ul>
											<div class="col-md-6 form-group mt-3 mt-md-0">
												<input type="text" class="form-control" name="name"
													id="name" style="width: 226%;top:26px;position:relative;" autocomplete="off" placeholder="단과대학이름">
                                                    <input type="text" class="form-control" name="name"
													id="name" style="width: 226%;top:26px;position:relative;" autocomplete="off" placeholder="단과대학이름">
                                                    <input type="text" class="form-control" name="name"
													id="name" style="width: 226%;top:26px;position:relative;" autocomplete="off" placeholder="단과대학이름">
                                                    <input type="text" class="form-control" name="name"
													id="name" style="width: 226%;top:26px;position:relative;" autocomplete="off" placeholder="단과대학이름">
                                                    <input type="text" class="form-control" name="name"
													id="name" style="width: 226%;top:26px;position:relative;" autocomplete="off" placeholder="단과대학이름">
                                                    <input type="text" class="form-control" name="name"
													id="name" style="width: 226%;top:26px;position:relative;" autocomplete="off" placeholder="단과대학이름">
                                                    <input type="text" class="form-control" name="name"
													id="name" style="width: 226%;top:26px;position:relative;" autocomplete="off" placeholder="단과대학이름">
                                                    <input type="text" class="form-control" name="name"
													id="name" style="width: 226%;top:26px;position:relative;" autocomplete="off" placeholder="단과대학이름">
                                                    <input type="text" class="form-control" name="name"
													id="name" style="width: 226%;top:26px;position:relative;" autocomplete="off" placeholder="단과대학이름">
                                                    <input type="text" class="form-control" name="name"
													id="name" style="width: 226%;top:26px;position:relative;" autocomplete="off" placeholder="단과대학이름">
                                                    <input type="text" class="form-control" name="name"
													id="name" style="width: 226%;top:26px;position:relative;" autocomplete="off" placeholder="단과대학이름">
                                                    
											</div>
											</br>
										</ul>
										<div class="btn-wrap">
											<button type="submit" class="btn-buy" style="border: none;">등록하기</button>
										</div>
									</div>
								</div>
							</div>

						</div>
			</div>
            </div>
        </div>
        </div>

        
	</div>
</body>
</html>

        <%@ include file="/WEB-INF/view/layout/footer.jsp"%>

<script>

</script>
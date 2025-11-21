-- 단과대
CREATE TABLE pu_college
(
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(10) NOT NULL UNIQUE
);

-- 학과
CREATE TABLE pu_department
(
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(10) NOT NULL UNIQUE,
   college_id BIGINT NOT NULL COMMENT '단과대 id',
   FOREIGN KEY (college_id) REFERENCES pu_college(id) ON DELETE CASCADE
);
ALTER TABLE pu_department AUTO_INCREMENT = 101;

-- 사용자
CREATE TABLE pu_user (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '고유 번호',
  user_number BIGINT UNIQUE COMMENT '로그인 학번',
  password VARCHAR(255) NOT NULL COMMENT '비밀번호(암호화됨)',
  name VARCHAR(30) NOT NULL,
  birth_date DATE NOT NULL,
  gender CHAR(2) NOT NULL COMMENT '남성, 여성',
  address VARCHAR(100),
  tel VARCHAR(13) NOT NULL,
  email VARCHAR(30) NOT NULL,
  dept_id BIGINT COMMENT '학과',
  grade BIGINT DEFAULT '1' COMMENT '학년',
  semester INT DEFAULT '1' COMMENT '학기',
  entrance_date DATE NOT NULL COMMENT '입사일 및 입학일',
  graduation_date DATE DEFAULT NULL COMMENT '퇴사일 및 졸업일',
  origin_file_name VARCHAR(255) DEFAULT NULL,
  upload_file_name VARCHAR(255) DEFAULT NULL,
  user_type ENUM('STUDENT','PROFESSOR','STAFF') NOT NULL COMMENT '사용자 유형',
  PRIMARY KEY (id),
  KEY dept_id (dept_id),
  CONSTRAINT pu_user_ibfk_1 FOREIGN KEY (dept_id) REFERENCES pu_department(id) ON DELETE CASCADE
);

-- 강의실
CREATE TABLE pu_room
(
   id VARCHAR(5) PRIMARY KEY,
   college_id BIGINT NOT NULL,
   FOREIGN KEY (college_id) REFERENCES pu_college(id) ON DELETE CASCADE
);

-- 강의
CREATE TABLE pu_subject
(
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(20) NOT NULL,
   professor_id BIGINT NOT NULL,
   room_id VARCHAR(5),
   dept_id BIGINT NOT NULL,
   type VARCHAR(2) NOT NULL COMMENT '강의 구분 (전공, 교양)',
   sub_year INT NOT NULL COMMENT '연도',
   semester INT NOT NULL COMMENT '학기',
   sub_day VARCHAR(1) NOT NULL COMMENT '요일',
   start_time INT NOT NULL COMMENT '시작 시간',
   end_time INT NOT NULL COMMENT '종료 시간',
   grades INT NOT NULL COMMENT '이수 학점',
   capacity INT NOT NULL COMMENT '수강 정원',
   num_of_student INT NOT NULL DEFAULT 0 COMMENT '현재 신청 인원',
   FOREIGN KEY (professor_id) REFERENCES pu_user(id) ON DELETE CASCADE,
   FOREIGN KEY (room_id) REFERENCES pu_room(id) ON DELETE CASCADE,
   FOREIGN KEY (dept_id) REFERENCES pu_department(id) ON DELETE CASCADE
);
ALTER TABLE pu_subject AUTO_INCREMENT = 10000;

-- 환산 점수
CREATE TABLE pu_grade
(
   grade VARCHAR(2) PRIMARY KEY COMMENT '학점 (평점)',
   grade_value FLOAT NOT NULL COMMENT '환산 점수'
);

-- 수강 신청
CREATE TABLE pu_pre_stu_sub
(
   student_id BIGINT,
   subject_id BIGINT,
   PRIMARY KEY (student_id, subject_id),
   FOREIGN KEY (student_id) REFERENCES pu_user(id) ON DELETE CASCADE,
   FOREIGN KEY (subject_id) REFERENCES pu_subject(id) ON DELETE CASCADE
);

-- 수강 내역
CREATE TABLE pu_stu_sub (
  id BIGINT NOT NULL AUTO_INCREMENT,
  student_id BIGINT DEFAULT NULL,
  subject_id BIGINT DEFAULT NULL,
  grade VARCHAR(2) DEFAULT NULL COMMENT '신청 학점 (평점)',
  complete_grade INT DEFAULT NULL COMMENT '이수 학점',
  evaluation_id BIGINT DEFAULT NULL,
  PRIMARY KEY (id),
  KEY student_id (student_id),
  KEY subject_id (subject_id),
  KEY grade (grade),
  CONSTRAINT pu_stu_sub_ibfk_1 FOREIGN KEY (student_id) REFERENCES pu_user(id) ON DELETE CASCADE,
  CONSTRAINT pu_stu_sub_ibfk_2 FOREIGN KEY (subject_id) REFERENCES pu_subject(id) ON DELETE CASCADE,
  CONSTRAINT pu_stu_sub_ibfk_3 FOREIGN KEY (grade) REFERENCES pu_grade(grade)
);

-- 단과대별 등록금
CREATE TABLE pu_coll_tuit
(
   college_id BIGINT PRIMARY KEY,
   amount INT NOT NULL,
   FOREIGN KEY (college_id) REFERENCES pu_college(id) ON DELETE CASCADE
);

-- 장학금
CREATE TABLE pu_scholarship
(
   type BIGINT PRIMARY KEY COMMENT '장학금 유형',
   max_amount INT NOT NULL COMMENT '최대 지원 금액'
);

-- 학생별 장학금 유형
CREATE TABLE pu_stu_sch
(
   student_id BIGINT NOT NULL,
   sch_year INT NOT NULL COMMENT '지원 연도',
   semester INT NOT NULL COMMENT '지원 학기',
   sch_type BIGINT COMMENT '장학금 유형',
   PRIMARY KEY (student_id, sch_year, semester),
   FOREIGN KEY (sch_type) REFERENCES pu_scholarship(type)
);

-- 등록금
CREATE TABLE pu_tuition
(
   student_id BIGINT,
   tui_year INT NOT NULL COMMENT '등록 연도',
   semester INT NOT NULL COMMENT '등록 학기',
   tui_amount INT NOT NULL COMMENT '등록금',
   sch_type BIGINT COMMENT '장학금 유형',
   sch_amount INT COMMENT '장학금',
   status BOOLEAN DEFAULT false COMMENT '납부 여부',
   PRIMARY KEY (student_id, tui_year, semester),
   FOREIGN KEY (student_id) REFERENCES pu_user(id) ON DELETE CASCADE,
   FOREIGN KEY (sch_type) REFERENCES pu_scholarship(type)
);

-- 공지사항
CREATE TABLE pu_notice
(
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   category VARCHAR(10) NOT NULL,
   title VARCHAR(255) NOT NULL,
   content TEXT NOT NULL,
   created_time TIMESTAMP DEFAULT now(),
   views INT NOT NULL DEFAULT 0 COMMENT '조회수'
);

-- 공지사항 첨부 파일
CREATE TABLE pu_notice_file
(
   notice_id BIGINT NOT NULL,
   origin_filename VARCHAR(100) NOT NULL COMMENT '기존 파일명',
   uuid_filename VARCHAR(255) NOT NULL COMMENT '랜덤 문자열 포함 파일명',
   FOREIGN KEY (notice_id) REFERENCES pu_notice(id) ON DELETE CASCADE
);

-- 휴학 신청 내역
CREATE TABLE pu_break
(
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   student_id BIGINT NOT NULL,
   student_grade INT NOT NULL,
   from_year INT NOT NULL,
   from_semester INT NOT NULL,
   to_year INT NOT NULL,
   to_semester INT NOT NULL,
   type VARCHAR(10) NOT NULL COMMENT '일반, 임신·출산·육아, 질병, 창업, 군입대',
   app_date DATE DEFAULT (current_date) NOT NULL COMMENT '신청 일자',
   status VARCHAR(3) NOT NULL DEFAULT '처리중' COMMENT '처리중, 승인, 거부',
   FOREIGN KEY (student_id) REFERENCES pu_user(id) ON DELETE CASCADE
);

-- 학적 상태
CREATE TABLE pu_stu_stat
(
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   student_id BIGINT NOT NULL,
   status VARCHAR(3) NOT NULL DEFAULT '재학',
   from_date DATE,
   to_date DATE,
   break_app_id BIGINT,
   FOREIGN KEY (student_id) REFERENCES pu_user(id) ON DELETE CASCADE,
   FOREIGN KEY (break_app_id) REFERENCES pu_break(id) ON DELETE CASCADE
);

-- 수강 상세
CREATE TABLE pu_stu_sub_detail
(
   id BIGINT PRIMARY KEY,
   student_id BIGINT NOT NULL,
   subject_id BIGINT NOT NULL,
   absent INT COMMENT '결석 횟수',
   lateness INT COMMENT '지각 횟수',
   homework INT COMMENT '과제 점수',
   mid_exam INT COMMENT '중간고사 점수',
   final_exam INT COMMENT '기말고사 점수',
   converted_mark INT COMMENT '환산점수',
   FOREIGN KEY (id) REFERENCES pu_stu_sub(id) ON DELETE CASCADE,
   FOREIGN KEY (student_id) REFERENCES pu_user(id) ON DELETE CASCADE,
   FOREIGN KEY (subject_id) REFERENCES pu_subject(id) ON DELETE CASCADE
);

-- 강의 계획서
CREATE TABLE pu_syllabus
(
   subject_id BIGINT PRIMARY KEY,
   overview VARCHAR(255) COMMENT '수업 개요',
   objective VARCHAR(255) COMMENT '강의 목표',
   textbook VARCHAR(255) COMMENT '교재',
   status VARCHAR(40) DEFAULT 'TEMP' COMMENT '임시저장(TEMP)/최종제출(COMPLETE) 상태',
   pdf_path VARCHAR(255) COMMENT 'PDF 저장 경로',
   week1 TEXT COMMENT '1주차 수업계획' ,
   week2 TEXT COMMENT '2주차 수업계획' ,
   week3 TEXT COMMENT '3주차 수업계획',
   week4 TEXT COMMENT '4주차 수업계획',
   week5 TEXT COMMENT '5주차 수업계획',
   week6 TEXT COMMENT '6주차 수업계획',
   week7 TEXT COMMENT '7주차 수업계획',
   week8 TEXT COMMENT '8주차 수업계획',
   week9 TEXT COMMENT '9주차 수업계획',
   week10 TEXT COMMENT '10주차 수업계획',
   week11 TEXT COMMENT '11주차 수업계획',
   week12 TEXT COMMENT '12주차 수업계획',
   week13 TEXT COMMENT '13주차 수업계획',
   week14 TEXT COMMENT '14주차 수업계획',
   week15 TEXT COMMENT '15주차 수업계획',
   FOREIGN KEY (subject_id) REFERENCES pu_subject(id) ON DELETE CASCADE
);

-- 강의 평가
CREATE TABLE pu_evaluation
(
   evaluation_id BIGINT AUTO_INCREMENT,
   student_id BIGINT,
   subject_id BIGINT,
   PRIMARY KEY (evaluation_id, student_id, subject_id),
   answer1 INT NOT NULL,
   answer2 INT NOT NULL,
   answer3 INT NOT NULL,
   answer4 INT NOT NULL,
   answer5 INT NOT NULL,
   answer6 INT NOT NULL,
   answer7 INT NOT NULL,
   improvements VARCHAR(255) COMMENT '건의사항',
   FOREIGN KEY (student_id) REFERENCES pu_user(id) ON DELETE CASCADE,
   FOREIGN KEY (subject_id) REFERENCES pu_subject(id) ON DELETE CASCADE
);

-- 질문지
CREATE TABLE pu_question
(
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   question1 VARCHAR(100) NOT NULL COMMENT '질문 내용',
   question2 VARCHAR(100) NOT NULL,
   question3 VARCHAR(100) NOT NULL,
   question4 VARCHAR(100) NOT NULL,
   question5 VARCHAR(100) NOT NULL,
   question6 VARCHAR(100) NOT NULL,
   question7 VARCHAR(100) NOT NULL,
   sug_content VARCHAR(255) NOT NULL
);

-- 학사일정
CREATE TABLE pu_schedule(
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   staff_id BIGINT,
   start_day DATE NOT NULL,
   end_day DATE NOT NULL,
   information VARCHAR(50) NOT NULL,
   FOREIGN KEY (staff_id) REFERENCES pu_user(id)
);

-- 신청 강의
CREATE TABLE pu_apply_sub (
   id BIGINT NOT NULL AUTO_INCREMENT,
   professor_id BIGINT NOT NULL,
   name VARCHAR(30) NOT NULL COMMENT '강의 명',
   room_id VARCHAR(5) NOT NULL COMMENT '강의실 명',
   dept_id BIGINT NOT NULL COMMENT '학과 명',
   type CHAR(2) NOT NULL COMMENT '전공/교양',
   start_time INT NOT NULL COMMENT '강의 시작 시간',
   end_time INT NOT NULL COMMENT '강의 끝나는 시간',
   sub_year INT NOT NULL COMMENT '강의 개설 년도',
   semester INT NOT NULL COMMENT '강의 개설 학기',
   sub_day VARCHAR(1) NOT NULL COMMENT '강의 요일',
   grades INT NOT NULL COMMENT '이수 학점',
   capacity INT NOT NULL COMMENT '강의 정원 수',
   approval CHAR(10) NOT NULL DEFAULT '미승인',
   reason VARCHAR(1000) DEFAULT NULL,
   PRIMARY KEY (id)
);

-- 커뮤니티
CREATE TABLE pu_community (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  content VARCHAR(1000) DEFAULT NULL,
  userName VARCHAR(100) NOT NULL,
  createDate DATETIME NOT NULL,
  updateDate DATETIME DEFAULT NULL,
  PRIMARY KEY (id)
);

-- 댓글
CREATE TABLE pu_comment (
  id BIGINT NOT NULL AUTO_INCREMENT,
  community_id BIGINT DEFAULT NULL,
  content TEXT,
  createDate DATETIME NOT NULL,
  user_id VARCHAR(20) DEFAULT NULL,
  role VARCHAR(10) DEFAULT NULL,
  updateDate DATETIME DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_community_id(community_id),
  CONSTRAINT fk_community_comment FOREIGN KEY (community_id) REFERENCES pu_community(id) ON DELETE CASCADE
);

-- 결제
CREATE TABLE pu_payment (
 id BIGINT NOT NULL AUTO_INCREMENT,
 u_id VARCHAR(20) NOT NULL,
 m_id VARCHAR(20) NOT NULL,
 stu_id BIGINT NOT NULL,
 buyer_name VARCHAR(20) NOT NULL,
 total_price INT NOT NULL,
 payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (id)
);

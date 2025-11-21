-- 1. 단과대
INSERT INTO pu_college (id, name) VALUES
(1, '공과대'),
(2, '인문대'),
(3, '자연대');

-- 2. 학과
INSERT INTO pu_department (id, name, college_id) VALUES
(1, '컴퓨터공학과', 1),
(2, '전자공학과', 1),
(3, '수학과', 3);

-- 3. 학점 기준
INSERT INTO pu_grade (grade, grade_value) VALUES
('A', 4.5), ('B', 4.0), ('C', 3.5), ('D', 3.0), ('F', 0.0);

-- 4. 사용자 (학생)
INSERT INTO pu_user (id, user_number, password, name, birth_date, gender, tel, email, dept_id, grade, semester, entrance_date, user_type)
VALUES
(1, '20250001', '$2a$10$hEYFZOe.PbFbVRcPZymRKuCGMU2MilAn64ZGnIqPgfwWL7JyGeKKW', '김학생1', '2003-03-01', '남', '010-1111-1111', 'student1@test.com', 1, 1, 1, '2025-03-01', 'STUDENT'),
(2, '20250002', '$2a$10$hEYFZOe.PbFbVRcPZymRKuCGMU2MilAn64ZGnIqPgfwWL7JyGeKKW', '김학생2', '2003-04-05', '여', '010-1111-1112', 'student2@test.com', 2, 1, 1, '2025-03-01', 'STUDENT'),
(3, '20250003', '$2a$10$hEYFZOe.PbFbVRcPZymRKuCGMU2MilAn64ZGnIqPgfwWL7JyGeKKW', '박학생3', '2003-05-10', '남', '010-1111-1113', 'student3@test.com', 3, 1, 1, '2025-03-01', 'STUDENT');

-- 5. 사용자 (교수)
INSERT INTO pu_user (id, user_number, password, name, birth_date, gender, tel, email, dept_id, entrance_date, user_type)
VALUES
(4, '30001', '$2a$10$hEYFZOe.PbFbVRcPZymRKuCGMU2MilAn64ZGnIqPgfwWL7JyGeKKW', '이교수1', '1980-02-15', '남', '010-2222-1111', 'prof1@test.com', 1, '2010-03-01', 'PROFESSOR'),
(5, '30002', '$2a$10$hEYFZOe.PbFbVRcPZymRKuCGMU2MilAn64ZGnIqPgfwWL7JyGeKKW', '이교수2', '1975-06-10', '여', '010-2222-1112', 'prof2@test.com', 2, '2008-03-01', 'PROFESSOR');

-- 6. 사용자 (교직원)
INSERT INTO pu_user (id, user_number, password, name, birth_date, gender, tel, email, entrance_date, user_type)
VALUES
(6, '40001', '$2a$10$hEYFZOe.PbFbVRcPZymRKuCGMU2MilAn64ZGnIqPgfwWL7JyGeKKW', '김직원1', '1985-01-01', '남', '010-3333-1111', 'staff1@test.com', '2015-03-01', 'STAFF'),
(7, '40002', '$2a$10$hEYFZOe.PbFbVRcPZymRKuCGMU2MilAn64ZGnIqPgfwWL7JyGeKKW', '박직원2', '1988-02-02', '여', '010-3333-1112', 'staff2@test.com', '2018-03-01', 'STAFF');

-- 7. 강의실
INSERT INTO pu_room (id, college_id) VALUES
('R101', 1), ('R102', 1), ('R201', 2);

-- 8. 강의
INSERT INTO pu_subject (id, name, professor_id, room_id, dept_id, type, sub_year, semester, sub_day, start_time, end_time, grades, capacity)
VALUES
(10000, '자료구조', 4, 'R101', 1, '전공', 2025, 1, '월', 9, 11, 3, 30),
(10001, '전자회로', 5, 'R102', 2, '전공', 2025, 1, '화', 10, 12, 3, 30);

-- 9. 수강 신청
-- INSERT INTO pu_pre_stu_sub (student_id, subject_id) VALUES
-- (1, 10000), (2, 10000), (3, 10001);

-- 10. 수강 내역
-- INSERT INTO pu_stu_sub (student_id, subject_id, grade, complete_grade) VALUES
-- (1, 10000, 'A', 3),
-- (2, 10000, 'B', 3),
-- (3, 10001, 'C', 3);

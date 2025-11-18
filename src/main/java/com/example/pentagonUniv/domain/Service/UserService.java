package com.example.pentagonUniv.domain.Service;

import com.example.pentagonUniv._global.handler.exception.CustomRestfullException;
import com.example.pentagonUniv._global.utils.Define;
import com.example.pentagonUniv.domain.dto.ChangePasswordDto;
import com.example.pentagonUniv.domain.dto.LoginDto;
import com.example.pentagonUniv.domain.dto.UserInfoDto;
import com.example.pentagonUniv.domain.dto.response.PrincipalDto;
import com.example.pentagonUniv.domain.dto.response.StudentInfoDto;
import com.example.pentagonUniv.domain.repository.interfaces.StudentRepository;
import com.example.pentagonUniv.domain.repository.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.cyber.university.service
 * <p>
 * fileName       : UserService
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 유저 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */

/**
 * @FileName : UserService.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 12.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 :
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public PrincipalDto login(LoginDto loginDto) {
        PrincipalDto userEntity = userRepository.selectById(loginDto.getId());

        if (userEntity == null) {

            throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())) {
            throw new CustomRestfullException(Define.WRONG_PASSWORD, HttpStatus.BAD_REQUEST);
        }

        return userEntity;
    }

    /**
     *
     * @Method Name : updatePassword
     * @작성일 : 2024. 3. 12.
     * @작성자 : 이준혁
     * @변경이력 :
     * @Method 설명 : 비번변경
     */
    @Transactional
    public void updatePassword(ChangePasswordDto changePasswordDto) {
        int resultCountRaw = userRepository.updatePassword(changePasswordDto);
        if (resultCountRaw != 1) {
            throw new CustomRestfullException(Define.UPDATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @Method Name : readIdByNameAndEmail
     * @작성일 : 2024. 3. 12.
     * @작성자 : 이준혁
     * @변경이력 :
     * @Method 설명 : 아이디 찾기
     */
//	@Transactional
//	public Integer readIdByNameAndEmail(FindIdFormDto findIdFormDto) {
//
//		Integer findId = null;
//		/**
//		if (findIdFormDto.getUserRole().equals("student")) {
//			findId = studentRepository.selectIdByNameAndEmail(findIdFormDto);
//		} else if (findIdFormDto.getUserRole().equals("professor")) {
//			findId = professorRepository.selectIdByNameAndEmail(findIdFormDto);
//		} else if (findIdFormDto.getUserRole().equals("staff")) {
//			findId = staffRepository.selectIdByNameAndEmail(findIdFormDto);
//		}
//		*/
//
//		findId = studentRepository.selectIdByNameAndEmail(findIdFormDto);
//		if (findId == null) {
//			throw new CustomRestfullException("아이디를 찾을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		return findId;
//
//	}

    /**
     *
     * @Method Name : updateTempPassword
     * @작성일 : 2024. 3. 12.
     * @작성자 : 이준혁
     * @변경이력 :
     * @Method 설명 : 비번찾기
     */
//	@Transactional
//	public String updateTempPassword(FindPasswordFormDto findPasswordFormDto) {
//		String password = null;
//		Integer findId = 0;
//
//		switch (findPasswordFormDto.getUserRole()) {
//		case "student":
//			findId = studentRepository.selectStudentByIdAndNameAndEmail(findPasswordFormDto);
//			break;
//		case "professor":
//			findId = professorRepository.selectProfessorByIdAndNameAndEmail(findPasswordFormDto);
//			break;
//		case "staff":
//			findId = staffRepository.selectStaffByIdAndNameAndEmail(findPasswordFormDto);
//			break;
//		default:
//			throw new CustomRestfullException("잘못된 접근입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		if (findId == null) {
//			throw new CustomRestfullException("회원정보를 찾을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		password = new TempPassword().returnTempPassword();
//		System.out.println("임시 비밀번호: " + password);
//
//		ChangePasswordDto changePasswordDto = new ChangePasswordDto();
//		changePasswordDto.setAfterPassword(passwordEncoder.encode(password));
//		changePasswordDto.setId(findPasswordFormDto.getId());
//		userRepository.updatePassword(changePasswordDto);
//
//		return password;
//	}

    /**
     *
     * @Method Name : readStudentInfo
     * @작성일 : 2024. 3. 12.
     * @작성자 : 이준혁
     * @변경이력 :
     * @Method 설명 : 학생정보조회
     */
	@Transactional
	public StudentInfoDto readStudentInfo(Integer id) {
		StudentInfoDto studentEntity = studentRepository.selectStudentInfoById(id);
		return studentEntity;
	}

    /**
     *
     * @Method Name : readProfessorInfo
     * @작성일 : 2024. 3. 12.
     * @작성자 : 이준혁
     * @변경이력 :
     * @Method 설명 : 교수 정보 조회
     */
//	@Transactional
//	public ProfessorInfoDto readProfessorInfo(Integer id) {
//		ProfessorInfoDto professorEntity = professorRepository.selectProfessorInfoById(id);
//		return professorEntity;
//	}

    /**
     *
     * @Method Name : readStaffInfoForUpdate
     * @작성일 : 2024. 3. 12.
     * @작성자 : 이준혁
     * @변경이력 :
     * @Method 설명 : 직원 회원정보 업데이트
     */
//	public UserInfoForUpdateDto readStaffInfoForUpdate(Integer userId) {
//		UserInfoForUpdateDto userInfoForUpdateDto = staffRepository.selectByUserId(userId);
//		return userInfoForUpdateDto;
//	}


    /**
     *
     * @Method Name : readStudentInfoForUpdate
     * @작성일 : 2024. 3. 12.
     * @작성자 : 이준혁
     * @변경이력 :
     * @Method 설명 : 학생 회원정보 업데이트
     */
//	public UserInfoForUpdateDto readStudentInfoForUpdate(Integer userId) {
//		UserInfoForUpdateDto userInfoForUpdateDto = studentRepository.selectByUserId(userId);
//		return userInfoForUpdateDto;
//	}


    /**
     *
     * @Method Name : readProfessorInfoForUpdate
     * @작성일 : 2024. 3. 12.
     * @작성자 : 이준혁
     * @변경이력 :
     * @Method 설명 : 교수 정보 업데이트
     */
//	public UserInfoForUpdateDto readProfessorInfoForUpdate(Integer userId) {
//		UserInfoForUpdateDto userInfoForUpdateDto = professorRepository.selectByUserId(userId);
//		return userInfoForUpdateDto;
//	}


    /**
     *
     * @Method Name : updateStaff
     * @작성일 : 2024. 3. 12.
     * @작성자 : 이준혁
     * @변경이력 :
     * @Method 설명 : 직원 회원정보 수정
     */
//	@Transactional
//	public void updateStaff(UserUpdateDto updateDto) {
//		int resultCountRaw = staffRepository.updateStaff(updateDto);
//		if (resultCountRaw != 1) {
//			throw new CustomRestfullException(Define.UPDATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}


    /**
     *
     * @Method Name : updateStudent
     * @작성일 : 2024. 3. 12.
     * @작성자 : 이준혁
     * @변경이력 :
     * @Method 설명 : 학생정보 수정
     */
//	@Transactional
//	public void updateStudent(UserUpdateDto updateDto) {
//		int resultCountRaw = studentRepository.updateStudent(updateDto);
//		if (resultCountRaw != 1) {
//			throw new CustomRestfullException(Define.UPDATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}


    /**
     *
     * @Method Name : updateProfessor
     * @작성일 : 2024. 3. 12.
     * @작성자 : 이준혁
     * @변경이력 :
     * @Method 설명 : 교수정보 업데이트
     */
//	@Transactional
//	public void updateProfessor(UserUpdateDto updateDto) {
//		int resultCountRaw = professorRepository.updateProfessor(updateDto);
//		if (resultCountRaw != 1) {
//			throw new CustomRestfullException(Define.UPDATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}


    /**
     * @Method Name : findById
     * @작성일 : 2024. 3. 12.
     * @작성자 : 박경진
     * @변경이력 :
     * @Method 설명 : userId로 user 정보 조회
     */
    public UserInfoDto findById(Integer userId) {
        UserInfoDto userInfoDto = userRepository.findById(userId);
        return userInfoDto;
    }

    /**
     *
     * @Method Name : readStudent
     * @작성일 : 2024. 3. 13.
     * @작성자 : 이준혁
     * @변경이력 :
     * @Method 설명 : 학생조회
     */
//	@Transactional
//	public Student readStudent(Integer studentId) {
//		Student studentEntity = studentRepository.selectByStudentId(studentId);
//		return studentEntity;
//	}

}

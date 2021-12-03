package com.project.dwine.personalQnA.model.service;

import java.util.List;

import com.project.dwine.personalQnA.model.vo.PersonalQ;

public interface PersonalQnAService {

	int insertUserQna(String qna_title, String qna_content, String qna_image, int user_no);

	int deleteUserQna(int qna_no);

	PersonalQ selectOneQna(int qna_no);

	int modifyUserQna(String qna_title, String qna_content, String qna_image, int qna_no);

	int insertAdminAnswer(int qna_no, String answer_content);

	int adminDeleteUserQna(int qna_no);

	int adminAnswerModify(int qna_no, String answer_content);

	List<PersonalQ> findUserQnaListPage(int user_no, int startRow, int endRow);

	int getTotalQnaListCount(int user_no);

	int getTotalQnaListCount();

	List<PersonalQ> findAllQnaListPage(int startRow, int endRow);

	int insertUserQnaNoImage(String qna_title, String qna_content, int user_no);

	int modifyUserQnaNoImage(String qna_title, String qna_content, int qna_no);

}

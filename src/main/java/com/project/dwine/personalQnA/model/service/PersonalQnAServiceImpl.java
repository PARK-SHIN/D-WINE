package com.project.dwine.personalQnA.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.personalQnA.model.dao.PersonalQnAMapper;
import com.project.dwine.personalQnA.model.vo.PersonalQ;


@Service
public class PersonalQnAServiceImpl implements PersonalQnAService {

	private PersonalQnAMapper qnaMapper;
	
	@Autowired
	public PersonalQnAServiceImpl(PersonalQnAMapper qnaMapper) {
		this.qnaMapper = qnaMapper;
	}
	
	@Override
	public int insertUserQna(String qna_title, String qna_content, String qna_image, int user_no) {
		return qnaMapper.insertUserQna(qna_title, qna_content, qna_image, user_no);
	}

	@Override
	public int deleteUserQna(int qna_no) {
		return qnaMapper.deleteUserQna(qna_no);
	}

	@Override
	public PersonalQ selectOneQna(int qna_no) {
		return qnaMapper.selectOneQna(qna_no);
	}

	@Override
	public int modifyUserQna(String qna_title, String qna_content, String qna_image, int qna_no) {
		return qnaMapper.modifyUserQna(qna_title, qna_content, qna_image, qna_no);
	}

	@Override
	public int insertAdminAnswer(int qna_no, String answer_content) {
		return qnaMapper.insertAdminAnswer(qna_no, answer_content);
	}

	@Override
	public int adminDeleteUserQna(int qna_no) {
		return qnaMapper.adminDeleteUserQna(qna_no);
	}

	@Override
	public int adminAnswerModify(int qna_no, String answer_content) {
		return qnaMapper.adminAnswerModify(qna_no, answer_content);
	}

	@Override
	public List<PersonalQ> findUserQnaListPage(int user_no, int startRow, int endRow) {
		return qnaMapper.findUserQnaListPage(user_no, startRow, endRow);
	}

	@Override
	public int getTotalQnaListCount(int user_no) {
		return qnaMapper.getTotalQnaListCount(user_no);
	}

	@Override
	public int getTotalQnaListCount() {
		return qnaMapper.getTotalQnaListCount();
	}

	@Override
	public List<PersonalQ> findAllQnaListPage(int startRow, int endRow) {
		return qnaMapper.findAllQnaListPage(startRow, endRow);
	}

	@Override
	public int insertUserQnaNoImage(String qna_title, String qna_content, int user_no) {
		return qnaMapper.insertUserQnaNoImage(qna_title, qna_content, user_no);
	}

	@Override
	public int modifyUserQnaNoImage(String qna_title, String qna_content, int qna_no) {
		return qnaMapper.modifyUserQnaNoImage(qna_title, qna_content, qna_no);
	}

}

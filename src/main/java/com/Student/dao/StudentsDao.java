package com.Student.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ELearning.model.Interest;
import com.Student.model.StudentBean;
import com.Trainer.model.GroupBean;

@Repository
public interface StudentsDao extends JpaRepository<StudentBean, Integer> {

	List<StudentBean> findByGroupId(int groupId);

	List<StudentBean> findByUserId(Integer id);

	StudentBean findByGroupIdAndUserId(Integer groupId, Integer id);

	boolean existsByGroupIdAndUserId(int groupId, Integer id);
	
	
	

}

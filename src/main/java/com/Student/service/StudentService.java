package com.Student.service;

import java.util.List;
import java.util.Map;

import com.Student.Exceptions.Empty;
import com.Student.Exceptions.LimitCross;
import com.Student.model.StudentBean;
import com.Trainer.Exceptions.GroupNotFound;
import com.Trainer.model.GroupBean;

public interface StudentService {

	List<GroupBean> getGroups(Integer id) throws GroupNotFound;

	StudentBean joinGroup(StudentBean sb) throws LimitCross;

	List<GroupBean> getClasses(Integer id) throws Empty;

	String deleteGroup(Integer id);

	StudentBean studInfo(Integer id, Integer groupId);

	Map<String, String> interests(int id);

	List<StudentBean> peopleInfo(int groupId);

}

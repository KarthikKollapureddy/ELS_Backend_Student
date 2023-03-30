package com.Student.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.ELearning.model.Interest;
import com.Student.Exceptions.Empty;
import com.Student.Exceptions.LimitCross;
import com.Student.Exceptions.StudentNotFound;
import com.Student.dao.StudentsDao;
import com.Student.model.StudentBean;
import com.Trainer.Exceptions.GroupNotFound;
import com.Trainer.model.GroupBean;

import coms.Admin.bean.Subjects;



@Service
public class StudentServiceImp implements StudentService {
	
	
	@Autowired StudentsDao studDao;
	@Autowired
	   RestTemplate restTemplate;

	@Override
	public List<GroupBean> getGroups(Integer id) throws GroupNotFound {
		// TODO Auto-generated method stub
		Interest intrst= restTemplate.getForObject("http://localhost:8098/elearning/api/main/getInterest/"+id, Interest.class);
		//System.out.println(intrst.getUserId());
		//System.out.println(restTemplate.getForObject("http://localhost:8098/elearning/api/interest/"+id, Interest.class));
		
		ResponseEntity<GroupBean[]> response= restTemplate.getForEntity("http://localhost:8080/elearning/api/trainer/getAll",GroupBean[].class);
			   
		
//		GroupBean[] res = response.getBody();
	List<GroupBean> res=Arrays.asList(response.getBody());
		
		
		//System.out.print(res.get(0).getGroupDesc());
		List<GroupBean> fres=new ArrayList<>();
		if(res != null) {
			for(GroupBean g:res) {
				//System.out.println(intrst.getIntrst3());
				if(g.getGroupSub()==intrst.getIntrst1() || g.getGroupSub()==intrst.getIntrst2() || g.getGroupSub()==intrst.getIntrst3()){
					{
						if(studDao.findByGroupIdAndUserId(g.getGroupId(),id) == null) {
							
							fres.add(g);
						}
				}
				
				//System.out.println(intrst.getIntrst1());
				}
			}
			
			if(fres!=null) {
				return fres;
			}
		}
		
		
		throw new GroupNotFound();
		
	
		
		
	}

	@Override
	public StudentBean joinGroup(StudentBean sb) throws LimitCross {
		// TODO Auto-generated method stub
		List<StudentBean> res=studDao.findByGroupId(sb.getGroupId());
		GroupBean gr= restTemplate.getForObject("http://localhost:8080/elearning/api/trainer/getClass/"+sb.getGroupId(), GroupBean.class);
		//System.out.println(intrst.getUserId());
		int len=res.size();
		if(len<gr.getGroupLimit()) {
			return studDao.save(sb);
		}
		throw new LimitCross();
	}

	@Override
	public List<GroupBean> getClasses(Integer id) throws Empty {
		// TODO Auto-generated method stub
		List<StudentBean> stud = studDao.findByUserId(id);
		List<GroupBean> gr=new ArrayList<>();
		for(StudentBean st:stud) {
			GroupBean g= restTemplate.getForObject("http://localhost:8080/elearning/api/trainer/getClass/"+st.getGroupId(), GroupBean.class);
			gr.add(g);
		}
		
		if(gr != null) {
			return gr;
		}
		throw new Empty();

	
		
		
	}

	@Override
	public String deleteGroup(Integer id) {
		// TODO Auto-generated method stub
		
		studDao.deleteById(id);
		return "Success";
		}

	@Override
	public StudentBean studInfo(Integer id, Integer groupId) {
		// TODO Auto-generated method stub
		return studDao.findByGroupIdAndUserId(groupId,id);
	}

	
	@Override
	public List<StudentBean> peopleInfo(int groupId) {
		// TODO Auto-generated method stub
		return studDao.findByGroupId(groupId);
	}

	@Override
	public StudentBean addRatingClass(int id, int groupId, int val) {
		// TODO Auto-generated method stub
		StudentBean st=studDao.findByGroupIdAndUserId(groupId,id);
		int s=0;
		st.setRating(val);
		List<StudentBean> students=studDao.findByGroupId(groupId);
		for(StudentBean stud:students) {
			s+=stud.getRating();
		}
		GroupBean response= restTemplate.getForObject("http://localhost:8080/elearning/api/trainer/getClass/"+groupId,GroupBean.class);
		Map < String, Integer > params = new HashMap < String,Integer > ();

		response.setRating(s/students.size());
		System.out.print(s/students.size());
		//restTemplate.postForObject("http://localhost:8787/elearning/api/trainer/edit/"+groupId,response,GroupBean.class);
		//restTemplate.put("http://localhost:8787/elearning/api/trainer/edit/"+groupId,response);
		//restTemplate.put("http://localhost:8787/elearning/api/trainer/edit/"+groupId,response,GroupBean.class);
		params.put("rating",s/students.size());
		restTemplate.put("http://localhost:8080/elearning/api/trainer/edit/"+groupId, response, params);
		
		
		return studDao.saveAndFlush(st);
	}

	@Override
	public List<StudentBean> getRatingClass(int groupId) {
		// TODO Auto-generated method stub
		return studDao.findByGroupId(groupId);
	}

	@Override
	public StudentBean addFeedClass(int id, int groupId, String value) {
		// TODO Auto-generated method stub
		StudentBean st=studDao.findByGroupIdAndUserId(groupId,id);
		
		st.setFeed(value);
		return studDao.saveAndFlush(st);
	}
		
	}



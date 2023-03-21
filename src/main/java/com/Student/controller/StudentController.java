package com.Student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Student.Exceptions.Empty;
import com.Student.Exceptions.LimitCross;
import com.Student.model.StudentBean;
import com.Student.service.StudentService;
import com.Trainer.Exceptions.GroupNotFound;
import com.Trainer.model.GroupBean;


@RestController
@RequestMapping("/elearning/api/student")
@CrossOrigin(origins="*")
public class StudentController {
	
	@Autowired StudentService studService;
	@GetMapping("suggestions/{id}")
	public List<GroupBean> getGroupInfo(@PathVariable Integer id) throws GroupNotFound{
		return studService.getGroups(id);
	}
	
	@PostMapping("join/{id}")
	public StudentBean joinClass(@PathVariable Integer id,@RequestBody StudentBean sb) throws LimitCross {
		return studService.joinGroup(sb);
	}
	
	@GetMapping("joinedClasses/{id}")
	public List<GroupBean> joinedGroups(@PathVariable Integer id) throws Empty{
		return studService.getClasses(id);
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteClass(@PathVariable Integer id) {
		return studService.deleteGroup(id);
	}
	
	@GetMapping("getStud/{groupId}/{id}")
	public StudentBean getStudent(@PathVariable Integer groupId,@PathVariable Integer id) {
		return studService.studInfo(id,groupId);
	}
	
	
	@GetMapping("getParticipants/{groupId}")
	public List<StudentBean> getPeopleInfo(@PathVariable int groupId){
		return studService.peopleInfo(groupId);
	}
	
	@PostMapping("addRating/{id}/{groupId}")
	public StudentBean addRatingInfo(@PathVariable int id,@PathVariable int groupId,@RequestBody String val) {
		int value=Integer.parseInt(val);
		return studService.addRatingClass(id,groupId,value);
	}
	

	@PostMapping("addFeed/{id}/{groupId}")
	public StudentBean addFeedback(@PathVariable int id,@PathVariable int groupId,@RequestBody String val) {
		
		return studService.addFeedClass(id,groupId,val);
	}
	
	@GetMapping("getRating/{groupId}")
	public List<StudentBean> getRatingInfo(@PathVariable int groupId) {
		
		return studService.getRatingClass(groupId);
	}

}

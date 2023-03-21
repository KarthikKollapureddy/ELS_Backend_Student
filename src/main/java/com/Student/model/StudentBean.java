package com.Student.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="students_info")
public class StudentBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int studId;
	public int userId;
	public int groupId;
	public int rating;
	public String feed;
	public int getStudId() {
		return studId;
	}
	public void setStudId(int studId) {
		this.studId = studId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public StudentBean(int studId, int userId, int groupId) {
		super();
		this.studId = studId;
		this.userId = userId;
		this.groupId = groupId;
	}
	public StudentBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentBean(int userId, int groupId) {
		super();
		this.userId = userId;
		this.groupId = groupId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getFeed() {
		return feed;
	}
	public void setFeed(String feed) {
		this.feed = feed;
	}
	public StudentBean(int studId, int userId, int groupId,int rating) {
		super();
		this.studId = studId;
		this.userId = userId;
		this.groupId = groupId;
		this.rating = rating;
	}
	public StudentBean(int studId, int userId, int groupId,int rating,String feed) {
		super();
		this.studId = studId;
		this.userId = userId;
		this.groupId = groupId;
		this.rating = rating;
		this.feed=feed;
	}
	
	
	

}

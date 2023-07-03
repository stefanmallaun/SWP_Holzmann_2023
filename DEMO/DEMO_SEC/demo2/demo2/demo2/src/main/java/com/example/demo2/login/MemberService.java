package com.example.demo2.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class MemberService {
	@Autowired
    MemberDao dao;
  
    public int count() {
		return dao.count();
	}
   
	public boolean insert(Member item) { 
		int rowCount = dao.insertOne(item);
		boolean result = false;
		if (rowCount > 0) {
			result = true;
		}
		return result;
	}
  
	public boolean updateOne(Member item) {
     	int rowCount = dao.updateOne(item);
		boolean result = false;
		if (rowCount > 0) {
            result = true;
		}
		return result;
	}
  
	public boolean deleteOne(String email) {
		int rowCount = dao.deleteOne(email);
		boolean result = false;
		if (rowCount > 0) {
			result = true;
		}
		return result;
	}
  
	public Member selectOne(String email) {
		return dao.selectOne(email);
	}
	
	public List<Member> selectMany() {
		return dao.selectMany();
	}
   
}

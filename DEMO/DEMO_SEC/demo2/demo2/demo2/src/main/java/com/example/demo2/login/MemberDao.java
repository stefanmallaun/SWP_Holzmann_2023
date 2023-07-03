package com.example.demo2.login;

import java.util.List;
import org.springframework.dao.DataAccessException;

public interface MemberDao {  

    public int count() 
		throws DataAccessException;
    public int insertOne(Member item) 
		throws DataAccessException;
	public Member selectOne(String email) 
		throws DataAccessException;
	public List<Member> selectMany() 
		throws DataAccessException;
	public int updateOne(Member item) 
		throws DataAccessException;
	public int deleteOne(String email) 
		throws DataAccessException;
} 


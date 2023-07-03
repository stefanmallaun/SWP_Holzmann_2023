package com.example.demo2.login;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoJdbcImpl implements MemberDao {
    @Autowired
	JdbcTemplate db;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public int count() 
	throws DataAccessException {
		//Einen Wert lesen
		int count = db.queryForObject
		("SELECT COUNT(*) FROM members", Integer.class);
		return count;
	}

	@Override
	public int insertOne(Member item) 
		throws DataAccessException {
		String pwd = passwordEncoder.encode(item.getPassword());
		int rowCount = db.update(
            "INSERT INTO members("
			+ " email, passwort, birthday, role"
			+ " ) VALUES(?, ?, ?, ?)"
			, item.getEmail()
			, pwd
            , item.getBirthday()
            , item.getRole()
            );
		return rowCount;
	}
 
	@Override
	public Member selectOne(String email) throws DataAccessException {
		// Lesen einer Zeile
   		Map<String, Object> map = 
	    db.queryForMap(
            "SELECT email, password, birthday, role" 
			+ " FROM members"
            + " WHERE email = ?", email);
		// OO Umwandeln
		Member item = new Member();
		item.setEmail((String)map.get("email"));
		item.setPassword((String)map.get("password"));
		item.setBirthday((Date)map.get("birthday"));
		item.setRole((String)map.get("role"));
		
		return item;
	}

	@Override
	public List<Member> selectMany() 
	throws DataAccessException {  
		// Mehrere Zeile lesen 
		List<Map<String, Object>> queryList = 
			db.queryForList(
                "SELECT email, password, birthday, role" 
                + " FROM members");
		// OO Umwandlung
		List<Member> list = new ArrayList<>();   
		for(Map<String, Object> map: queryList) {
            Member item = new Member();
            item.setEmail((String)map.get("email"));
            item.setPassword((String)map.get("password"));
            item.setBirthday((Date)map.get("birthday"));
            item.setRole((String)map.get("role"));      
			list.add(item);
		}
     return list;
	}

	@Override
	public int updateOne(Member item) 
		throws DataAccessException {
		String pwd = passwordEncoder.encode(item.getPassword());
		int rowCount = db.update("UPDATE members"
			+ " SET"
			+ " passwort = ?,"
            + " birthday = ?,"
            + " role = ?"
			+ " WHERE email = ?"
			, pwd
			, item.getBirthday()
			, item.getRole()
			, item.getEmail());
        return rowCount;
	}
    @Override
    public int deleteOne(String email) throws DataAccessException {
        int rowCount = db.update(
			"DELETE FROM members WHERE email = ?", email);
		return rowCount;
    }
}

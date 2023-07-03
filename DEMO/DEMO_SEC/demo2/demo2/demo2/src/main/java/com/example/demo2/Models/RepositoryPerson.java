package com.example.demo2.Models;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo2.login.Person;

@Repository
public class RepositoryPerson 
{
    // Besprechen: Autowired
    @Autowired
    JdbcTemplate db;

    @Autowired
    PasswordEncoder passwordEncoder;

    
    public Person findOne(String username) throws DataAccessException
    {
        Map<String, Object> map = db.queryForMap("SELECT username, passwd, firstname, lastname, role FROM person WHERE username = ?", username);
        Person p = new Person();
        p.setUsername((String)map.get("username"));
        p.setPassword((String)map.get("passwd"));
        p.setFirstname((String)map.get("firstname"));
        p.setLastname((String)map.get("lasname"));
        // Ergebnis der Query als Map liefern lassen
        return p;
    }       

    public List<Person> selectMany() throws DataAccessException
    {
        List<Map<String, Object>> querylist = db.queryForList("SELECT username, passwd, firstname, lastname, role FROM person");
        List<Person> list = new ArrayList<>();
        for(Map<String, Object> map : querylist)
        {
            Person p = new Person();
            p.setUsername((String)map.get("username"));
            p.setPassword((String)map.get("passwd"));
            p.setFirstname((String)map.get("firstname"));
            p.setLastname((String)map.get("lasname"));
            p.setRole((String)map.get("role"));
            list.add(p);
        }
        // Ergebnis der Query als Map liefern lassen
        return list;
    }       

    public int updateOne(Person p) throws DataAccessException
    {
        int rowCount = db.update("UPDATE person SET " +
        "passwd = ?, " + 
        "firstname = ?, " + 
        "lastname = ?, " +
        "role = ? " +
        "WHERE username = ?", 
        p.getPassword(),
        p.getFirstname(),
        p.getLastname(),
        p.getRole(),
        p.getUsername());
        return rowCount;
    }

    public int deleteOne(String username) throws DataAccessException
    {
        int rowCount = db.update("DELETE FROM person WHERE username = ?", username);
        return rowCount;
    }

    public int insertOne(Person p) throws DataAccessException
    {
        p.setRole("ROLE_STANDARD");
        int rowCount = db.update("INSERT INTO person(username, passwd, firstname, lastname, role) VALUES (?,?,?,?,?)", 
        p.getUsername(),
        passwordEncoder.encode(p.getPassword()),
        p.getFirstname(),
        p.getLastname(),
        p.getRole());
        
        return rowCount;
    }

    public int count() throws DataAccessException
    {
        int count = extracted();
        return count;
    }
    private Integer extracted() {
        return db.queryForObject("SELECT COUNT(*) FROM person", Integer.class);
    }       
}

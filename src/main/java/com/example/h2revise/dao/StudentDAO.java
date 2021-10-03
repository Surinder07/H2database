package com.example.h2revise.dao;

import com.example.h2revise.domain.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public StudentDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    public int addStudent(Student student){
        String sql =
                "insert into student values(:id, :name, :program)";
        return jdbcTemplate.update( sql, new BeanPropertySqlParameterSource(student));

    }

    public List<Student> getALlStudent(){

        return jdbcTemplate.query("Select * from student", new BeanPropertyRowMapper<>(Student.class));
    }
    public int updateStudent(Student student){
        System.out.println("Updating student..");
        String sql = "update student set name = :name where id = :id";
        return jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(student));
    }

}

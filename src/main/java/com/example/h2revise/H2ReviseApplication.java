package com.example.h2revise;

import com.example.h2revise.dao.StudentDAO;
import com.example.h2revise.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class H2ReviseApplication {

    private JdbcTemplate jdbcTemplate;

    private StudentDAO studentDAO;

    public H2ReviseApplication(JdbcTemplate jdbcTemplate, StudentDAO studentDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentDAO = studentDAO;
    }

    public static void main(String[] args) {
        SpringApplication.run(H2ReviseApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            String sql = "CREATE TABLE STUDENT (ID INT PRIMARY KEY , NAME VARCHAR(50), PROGRAM VARCHAR(50))";
            jdbcTemplate.execute(sql);
            System.out.println("Creating table");
            Student student = new Student(2, "Sachin", "Maths");
            studentDAO.addStudent(new Student(1, "Surinder", "java"));
            studentDAO.addStudent(student);
            studentDAO.addStudent(new Student(3, "Sunny", "ANgular"));

            studentDAO.getALlStudent().forEach(System.out::println);

            Thread.sleep(20000);
            student.setProgram("Automation ");
            studentDAO.updateStudent(student);
        };
    }

}

package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App  
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        StudentDao studentDao = context.getBean("studentDao",StudentDao.class);
//        Student student = new Student(2324,"amar","azamgarh");        
//        int r = studentDao.insert(student);
//        System.out.println("done" + r);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        boolean go = true;
        while(go) {
        	System.out.println("press 1 for add new student");
            System.out.println("press 2 for display all student");
            System.out.println("press 3 for get detail of single student");
            System.out.println("press 4 for delete student");
            System.out.println("press 5 for update student");
            System.out.println("press 6 for exit");
            
            try {
            	
            	int input = Integer.parseInt(br.readLine());
            	switch(input) {
            	case 1:
            		// add a new student
            		//taking input from user
            		System.out.println("enter user id");
            		int uid = Integer.parseInt(br.readLine());
            		
            		System.out.println("Enter user name");
            		String uName = br.readLine();
            		
            		System.out.println("Enter user city");
            		String uCity = br.readLine();
            		
            		//creating student object nd setting value
            		Student s = new Student();
            		s.setStudentId(uid);
            		s.setStudentName(uName);
            		s.setStudentCity(uCity);
            		
            		//saving student object to database by calling insert of student dao
            		int r = studentDao.insert(s);
            		System.out.println(r + "student add");
            		System.out.println("*****************");
            		System.out.println();
            		
            		break;
            	case 2:
            		//display all student
            		List<Student> allStudents = studentDao.getAllStudents();
            		for(Student st : allStudents) {
            			System.out.println("Id : "+ st.getStudentId());
            			System.out.println("name : "+ st.getStudentName());
            			System.out.println("City : "+ st.getStudentCity());
            			System.out.println("_______________________");
            		}
            		System.out.println("*********************");
            		break;
            	case 3:
            		//get single student data
            		System.out.println("enter user id : ");
            		int userid = Integer.parseInt(br.readLine());
            		Student student = studentDao.getStudent(userid);
            		System.out.println("Id : "+ student.getStudentId());
        			System.out.println("name : "+ student.getStudentName());
        			System.out.println("City : "+ student.getStudentCity());
        			System.out.println("_______________________");
            		
            		break;
            	case 4:
            		//delete student
            		System.out.println("enter user id : ");
            		int id = Integer.parseInt(br.readLine());
            		studentDao.deleteStudent(id);
            		System.out.println("student deleted.....");
            		
            		break; 
            	case 5:
            		//update student
            		System.out.println("enter user id of user u want to update : ");
            		int id_update = Integer.parseInt(br.readLine());
            		System.out.println("Enter user name");
            		String newName = br.readLine();
            		
            		System.out.println("Enter user city");
            		String newCity = br.readLine();
            		
            		Student S = new Student();
            		S.setStudentId(id_update);
            		S.setStudentName(newName);
            		S.setStudentCity(newCity);
            		
            		studentDao.updateStudent(S);
            		break;
            	case 6:
            		//exit
            		go = false;
            		break;
    	
            	}
            	
            }catch(Exception e){
            	System.out.println("invalid input try with another one");
            	System.out.println(e.getMessage());
            }
        	
        }
        System.out.println("thankyou for using my application");
    }
}

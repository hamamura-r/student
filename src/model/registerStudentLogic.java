package model;

import java.util.List;

public class registerStudentLogic {
	public void execute(Student student , List<Student> studentList) {
		studentList.add(0 ,student);
	}
}

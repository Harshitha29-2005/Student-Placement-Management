
package com.placement;

public class Student {

    private int studentId;
    private String studentName;
    private String email;
    private String department;
    private int graduationYear;

    public Student(int studentId, String studentName,
                   String email, String department,
                   int graduationYear) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.department = department;
        this.graduationYear = graduationYear;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public int getGraduationYear() {
        return graduationYear;
    }
}

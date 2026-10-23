
package com.placement;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        StudentDAO studentDAO = new StudentDAO();
        CompanyDAO companyDAO = new CompanyDAO();
        JobOpeningDAO jobOpeningDAO = new JobOpeningDAO();
        ApplicationDAO applicationDAO = new ApplicationDAO();

        // Login and registration menu
        boolean loggedIn = false;

        while (!loggedIn) {

            System.out.println("\n================================");
            System.out.println("          USER PORTAL");
            System.out.println("================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter username: ");
                    String username = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter password: ");
                    String password = sc.nextLine();

                    userDAO.registerUser(username, email, password);
                    break;

                case 2:
                    System.out.print("Enter email: ");
                    String loginEmail = sc.nextLine();

                    System.out.print("Enter password: ");
                    String loginPassword = sc.nextLine();

                    loggedIn = userDAO.loginUser(
                            loginEmail, loginPassword);
                    break;

                case 3:
                    System.out.println("Thank You Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        // Placement management menu after login
        while (true) {

            System.out.println("\n==========================================");
            System.out.println("   STUDENT PLACEMENT MANAGEMENT SYSTEM");
            System.out.println("==========================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Add Company");
            System.out.println("6. View All Companies");
            System.out.println("7. Add Job Opening");
            System.out.println("8. View Job Openings");
            System.out.println("9. Apply for a Job");
            System.out.println("10. View Applications");
            System.out.println("11. Update Application Status");
            System.out.println("12. Search Student by ID");
            System.out.println("13. Logout and Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number.");
                sc.nextLine();
                continue;
            }

            int option = sc.nextInt();
            sc.nextLine();

            try {
                switch (option) {

                    case 1: {
                        System.out.print("Enter student name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter email: ");
                        String studentEmail = sc.nextLine();

                        System.out.print("Enter department: ");
                        String department = sc.nextLine();

                        System.out.print("Enter graduation year: ");
                        int year = sc.nextInt();
                        sc.nextLine();

                        Student student = new Student(
                                0, name, studentEmail, department, year);

                        studentDAO.addStudent(student);
                        break;
                    }

                    case 2:
                        studentDAO.displayStudents();
                        break;

                    case 3: {
                        System.out.print("Enter student ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter new name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter new email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter new department: ");
                        String department = sc.nextLine();

                        System.out.print("Enter new graduation year: ");
                        int year = sc.nextInt();
                        sc.nextLine();

                        studentDAO.updateStudent(
                                id, name, email, department, year);
                        break;
                    }

                    case 4:
                        System.out.print("Enter student ID to delete: ");
                        studentDAO.deleteStudent(sc.nextInt());
                        sc.nextLine();
                        break;

                    case 5: {
                        System.out.print("Enter company name: ");
                        String companyName = sc.nextLine();

                        System.out.print("Enter company location: ");
                        String location = sc.nextLine();

                        companyDAO.addCompany(
                                new Company(0, companyName, location));
                        break;
                    }

                    case 6:
                        companyDAO.displayCompanies();
                        break;

                    case 7: {
                        System.out.print("Enter company ID: ");
                        int companyId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter job title: ");
                        String title = sc.nextLine();

                        System.out.print("Enter salary in LPA: ");
                        double salary = sc.nextDouble();
                        sc.nextLine();

                        jobOpeningDAO.addJobOpening(
                                new JobOpening(0, companyId, title, salary));
                        break;
                    }

                    case 8:
                        jobOpeningDAO.displayJobOpenings();
                        break;

                    case 9:
                        System.out.print("Enter student ID: ");
                        int studentId = sc.nextInt();

                        System.out.print("Enter job ID: ");
                        int jobId = sc.nextInt();
                        sc.nextLine();

                        applicationDAO.applyForJob(studentId, jobId);
                        break;

                    case 10:
                        applicationDAO.displayApplications();
                        break;

                    case 11: {
                        System.out.print("Enter application ID: ");
                        int applicationId = sc.nextInt();

                        System.out.println("1. Applied");
                        System.out.println("2. Shortlisted");
                        System.out.println("3. Selected");
                        System.out.println("4. Rejected");
                        System.out.print("Choose status: ");

                        int statusChoice = sc.nextInt();
                        sc.nextLine();

                        String status = null;

                        switch (statusChoice) {
                            case 1: status = "Applied"; break;
                            case 2: status = "Shortlisted"; break;
                            case 3: status = "Selected"; break;
                            case 4: status = "Rejected"; break;
                            default:
                                System.out.println("Invalid status.");
                        }

                        if (status != null) {
                            applicationDAO.updateApplicationStatus(
                                    applicationId, status);
                        }
                        break;
                    }

                    case 12:
                        System.out.print("Enter student ID to search: ");
                        studentDAO.searchStudentById(sc.nextInt());
                        sc.nextLine();
                        break;

                    case 13:
                        System.out.println("Logged out. Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                sc.nextLine();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

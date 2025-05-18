package HOSPITALMANAGEMENTSYSTEM;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hospital";

    private static final String username = "root";

    private static final String password = "Ajay@123";

    public HospitalManagementSystem() {
        super();
    }

    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        try {

            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection,scanner);

            while (true) {
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1.Add Patient");
                System.out.println("2.View Patient");
                System.out.println("3.View Doctors");
                System.out.println("4.Book Appointment");
                System.out.println("5.View Appointment");

                System.out.println("6.Exit");

                System.out.println("Enter Your Choice:");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        //Add Patient
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        //View Patient
                        patient.viewPatients();
                        System.out.println();
                        break;

                    case 3:
                        //view Doctor
                        doctor.viewDoctors();
                        System.out.println();
                        break;

                    case 4:
                        //Book Appointment
                       bookAppointment(patient,doctor,connection,scanner);
                        System.out.println();
                        break;
                        
                     case 5:
                    	 viewAppointments(connection);
                    	System.out.println();
                        break;

                    case 6:
                    	
                    	System.out.println("Thank You....");
                        return;
                    default:
                        System.out.println("Enter Valid Choice");
                        break;
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner) {
        System.out.println("Enter Patient Id: ");
        int patientId = scanner.nextInt();
        System.out.println("Enter Doctor Id ");
        int doctorId = scanner.nextInt();
        System.out.println("Enter appointment date(YYYY-MM-DD) :");
        String appointmentDate = scanner.next();

        if (patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)) {
            if (checkDoctorAvailability(doctorId, appointmentDate, connection)) {
                try {
                	String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointmentDate);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Appointment Booked!");
                    } else {
                        System.out.println("Failed to Book Appointment!");
                    }
                } catch (SQLException e) {
                	System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Doctor not available on this date !!");
            }
        } else {
            System.out.println("Either doctor or patient doesn't exist !!!");
        }


}

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection) {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id= ? AND appointment_date= ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
        return false;
    }
    
    public static void viewAppointments(Connection connection) {
        String query = "SELECT * FROM appointments";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSets = preparedStatement.executeQuery();

            System.out.println("Appointments: ");
            System.out.println("+----------------+------------+-----------+------------------+");
            System.out.println("| appointment_id | patient_id | doctor_id | appointment_date |");
            System.out.println("+----------------+------------+-----------+------------------+");

            while (resultSets.next()) {
                int id = resultSets.getInt("appointment_id");
                int patientId = resultSets.getInt("patient_id");
                int doctorId = resultSets.getInt("doctor_id");
                String appointmentDate = resultSets.getString("appointment_date");

                System.out.printf("| %-14d | %-10d | %-9d | %-16s |\n", id, patientId, doctorId, appointmentDate);
            }
            System.out.println("+----------------+------------+-----------+------------------+");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}

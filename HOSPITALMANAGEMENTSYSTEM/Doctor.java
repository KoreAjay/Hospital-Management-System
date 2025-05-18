package HOSPITALMANAGEMENTSYSTEM;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection connection;
    private Scanner scanner;

    public Doctor(Connection connection, Scanner scanner)
    {
        this.connection = connection;
        this.scanner = scanner;
    }


    public void viewDoctors()
    {
        String query="select * from doctors";
        try
        {
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            ResultSet resultSets=preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+--------------------+---------------------+");
            System.out.println("| Doctor Id  | Name               | Specialization      |");
            System.out.println("+------------+--------------------+---------------------+");

            while (resultSets.next())
            {
                int id= resultSets.getInt("id");
                String name=resultSets.getString("name");
                String specialization=resultSets.getString("specialization");

                System.out.printf("|%-12s|%-20s|%-21s|\n", id, name, specialization);
                System.out.println("+------------+--------------------+---------------------+");


            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    public boolean getDoctorById(int id)
    {
        String query="SELECT * FROM doctors WHERE id= ? ";
        try
        {
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();

            if(resultSet.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}

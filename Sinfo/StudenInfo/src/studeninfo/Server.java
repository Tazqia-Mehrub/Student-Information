/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studeninfo;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author touhe
 */
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("Sever Running...");
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket socket = serverSocket.accept();
            //received data
            Scanner scanner = new Scanner(socket.getInputStream());
            String username = scanner.next();
            String password = scanner.next();
            //checking the database
            Connection connection = MysqlConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from students where student_id = ?"
                    + " and password = ?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            //send data
            PrintStream printStream = new PrintStream(socket.getOutputStream());
          
            if(resultSet.next()){
               printStream.println(1);
            }else{
               printStream.println(0);
            }
                              
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

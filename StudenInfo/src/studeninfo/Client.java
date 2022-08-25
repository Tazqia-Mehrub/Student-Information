/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studeninfo;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author touhe
 */
public class Client {
    int sendData(String username, String password){
        int loginStatus=0;
        try {
            System.out.println("Client Sending data to server.");
            Socket socket = new Socket("127.0.0.1",6666);
            //sending data 
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            printStream.println(username);
            printStream.println(password);
            
           
           
            //receiving data
            Scanner scanner = new Scanner(socket.getInputStream());
            loginStatus = scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginStatus;
    }
}

package com.balazs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",3000)){
            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(),true);

            Scanner scanner = new Scanner(System.in);
            String line;
            String res;
            int counter = 0;
            do{
                if(counter == 0){
                    System.out.println("Enter your name: ");
                    line = scanner.nextLine();
                    stringToEcho.println(line);
                    counter = 1;
                }else if(counter == 1){
                    System.out.println("Enter your partners name: ");
                    line = scanner.nextLine();
                    stringToEcho.println(line);
                    counter = 2;
                }
                else{
                    System.out.println("Type in your message: ");
                    line = scanner.nextLine();
                    stringToEcho.println(line);
                    if(line.equals("exit")){
                        break;
                    }else{
                        res = echoes.readLine();
                        System.out.println(res);
                    }
                }


            }while(true);
        }catch(Exception e){
            System.out.println("Client Error "+e.getMessage());
        }
    }
}

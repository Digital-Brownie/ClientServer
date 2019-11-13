package com.exam.Client;

import com.exam.Models.Student;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client
{
    Socket socket;

    ObjectOutputStream out;
    ObjectInputStream in;
    public void Start()
    {
        try{
            socket = new Socket("localhost", 8080);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            Student student = new Student(1, "Alex");
            out.writeObject(student);

            Student student1 = (Student) in.readObject();

            System.out.println("name:" + student1.getName() + "id:" + student1.getId());
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

}

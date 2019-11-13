package com.exam.server;

import com.exam.Models.Student;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server
{
    private int portNumber;

    static int numClients = 0;

    Vector<ClientHandler> clientHandlers = new Vector<>();

    ServerSocket serverSocket;
    Socket clientSocket;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    InetAddress inetAddress;

    public Server(int portNumber) {
        this.portNumber = portNumber;
    }

    public void Start() throws IOException {
        serverSocket  = new ServerSocket(portNumber);

        while (numClients < 10)
        {
            System.out.println("Server is listening");

            clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            ClientHandler clientHandler = new ClientHandler(clientSocket);

            new Thread(clientHandler).start();

            clientHandlers.add(clientHandler);

            numClients++;
        }
    }


    private class ClientHandler implements Runnable
    {
        ClientHandler(Socket clientSocket)
        {
            this.clientSocket = clientSocket;
//            this.outputStream = outputStream;
        }
        Socket clientSocket;
        ObjectOutputStream outputStream;

        @Override
        public void run() {
            try {
                System.out.println("Run Invoked");
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                Student student = (Student) input.readObject();

                System.out.println("name:" + student.getName() + ",Id: " + student.getId());

                out.writeObject(new Student(4, "Dylan"));

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}



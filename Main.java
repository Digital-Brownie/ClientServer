package com.exam;

import com.exam.server.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here

        try {
            new Server(8080).Start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

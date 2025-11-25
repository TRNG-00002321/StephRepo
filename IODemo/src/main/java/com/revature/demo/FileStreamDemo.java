package com.revature.demo;

import java.io.*;

public class FileStreamDemo
{
    static FileInputStream fis;
    static FileOutputStream fos;
    static void main(String[] args) {
        try{
            fis = new FileInputStream("example.txt");
            fos = new FileOutputStream("output1.txt");
            int c;

            while((c=fis.read())!=-1)
            {
                System.out.write(c);
                fos.write(c);
            }
            //c = fis.read();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

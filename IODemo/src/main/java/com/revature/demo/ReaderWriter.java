package com.revature.demo;
import java.io.*;


public class ReaderWriter
{
    static void main(String[] args)
    {
        //try catch with resources and Buffered methods (same outputs as without but this faster)
        // FileReader/Writer go through files by character instead of bytes (streams go through bytes)
        try (BufferedReader fr = new BufferedReader(new FileReader("example.txt"));
             BufferedWriter fw = new BufferedWriter(new FileWriter("output1.txt"));)
        {
            String c;
            while((c=fr.readLine())!= null)
            {
                System.out.print(c);
                fw.write(c);
            }
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }


//        //try catch with resources
//        try (FileReader fr = new FileReader("example.txt");
//             FileWriter fw = new FileWriter("output1.txt");)
//        {
//            int c;
//
//            while((c=fr.read())!=-1)
//            {
//                System.out.write(c);
//                fw.write(c);
//            }
//        }
//        catch(IOException e){
//            throw new RuntimeException(e);
//        }



    }
}

package com.abhi.Assignment1.service;

import com.abhi.Assignment1.dto.AccountDto;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AccountFileService {

    public static String Filename="cusAccout.txt";

//     public  static String Filename = "/home/abhi/Desktop/cusAccount.txt";
    public void generateFile() throws IOException {
        Path file = Files.createFile(Paths.get(Filename));
        System.out.println("file:"+file);
    }
    public void writeAccount(List<List<String>>acc) throws IOException {
        try(BufferedWriter bufferedWriter=Files.newBufferedWriter(Paths.get(Filename))){
            for(List<String> i:acc) {
                bufferedWriter.write(i.toString());
                bufferedWriter.write(System.lineSeparator());
            }
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("file write failed");

        }

    }
}

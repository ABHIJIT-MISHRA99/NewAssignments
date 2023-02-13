package com.abhi.Assignment1.main;

import com.abhi.Assignment1.service.AccountExcelFile;
import com.abhi.Assignment1.service.AccountFileService;
import com.abhi.Assignment1.service.CustomerNameGeneratorService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class GenerateAccountFileMain {
    @Autowired
    private AccountFileService accountFileService;
    @Autowired
    private CustomerNameGeneratorService customerNameGeneratorService;
    @Autowired
    private AccountExcelFile accountExcelFile;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static List<String> acclist;


    @PostConstruct
    public void createAccountFile() throws IOException {

        List<List<String>> l = new ArrayList<>();
        accountFileService.generateFile();
        for (int i = 0; i < 5; i++) {
            List<String> acclist = new ArrayList<>();
            // name
            String generateName = customerNameGeneratorService.generateName();
            // account id
            String accountId = String.format("%012d", i);
            // account balance
            Random rd = new Random();
            float input = rd.nextFloat();
            float balance = Float.parseFloat(df.format(input));
            // create date
            LocalDate n = LocalDate.now();
            acclist.add(generateName);
            acclist.add(accountId);
            acclist.add(String.valueOf(balance));
            acclist.add(String.valueOf(n));


            l.add(acclist);
            writlefile(l);
            writeexcel(l,i);
        }
    }

    private void writeexcel(List<List<String>> l, int i) throws IOException {
        List<List<String>> k=l;
        accountExcelFile.writeExcel(k,i);
    }

    private void writlefile(List<List<String>> acclist) throws IOException {
        List<List<String>> k = acclist;

        accountFileService.writeAccount(k);

    }
}

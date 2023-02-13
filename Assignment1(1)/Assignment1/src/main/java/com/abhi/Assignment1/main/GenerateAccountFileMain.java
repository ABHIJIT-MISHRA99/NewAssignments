package com.abhi.Assignment1.main;

import com.abhi.Assignment1.dto.AccountDto;
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
    private static final DecimalFormat df=new DecimalFormat("0.00");



    @PostConstruct
    public void createAccountFile() throws IOException {
        accountFileService.generateFile();
        for (int i = 1; i < 100; i++) {
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
            List<String>acclist = new ArrayList<>();
            acclist.add(generateName);
            acclist.add(accountId);
            acclist.add(String.valueOf(balance));
            acclist.add(String.valueOf(n));
            writlefile(acclist);
        }
    }

    private void writlefile(List<String> acclist) throws IOException {
        List<String> k=acclist;
        List<String>a=new ArrayList<>();
        for(String s:k)
        {
            a.add(s);
        }
        accountFileService.writeAccount(a);

    }
}

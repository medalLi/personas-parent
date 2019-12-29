package com.desheng.personas.mock.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IpUtil {

    static List<String> ipList = new ArrayList<>();
    static {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("mock-data/data/ip.data"));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\s+");
                if(fields == null || fields.length != 3) {
                    continue;
                }
                ipList.add(fields[0]);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static Random random = new Random();

    public static String randomIp() {
        return ipList.get(random.nextInt(ipList.size()));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(randomIp());
        }
    }
}

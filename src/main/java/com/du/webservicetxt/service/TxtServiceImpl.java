package com.du.webservicetxt.service;


import javax.jws.WebService;
import java.io.*;

@WebService(
        targetNamespace = "http://service.webservicetxt.du.com",
        name = "txt",
        serviceName = "TxtService",
        portName = "txtName",
        endpointInterface = "com.du.webservicetxt.service.TxtService"
)
public class TxtServiceImpl implements TxtService {

    public static BufferedReader bufferedReader;
    private static String path = "D:/test/test.txt";
    private static File fileName = new File(path);
    private static String readStr = "";

    @Override
    public  String readTxt() {
        String read;
        FileReader fileReader;
        try{
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            try{
                while ((read = bufferedReader.readLine())!=null){
                    readStr = readStr + read + "\r\n";
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("\r\n" + readStr);
        return readStr;
    }

    @Override
    public void writeTxt(String newStr) {
        String fileIn = newStr + "\r\n" +readStr + "\r\n";
        RandomAccessFile mm = null;
        try{
            mm = new RandomAccessFile(fileName,"rw");
            mm.writeBytes(fileIn);
        }catch (IOException e1){
            e1.printStackTrace();
        }finally {
            if (mm!=null){
                try{
                    mm.close();
                }catch (IOException e2){
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override
    public void replaceTxt(String oldStr, String newStr) {
        String temp = "";
        try{
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            for (int j = 1;(temp =br.readLine()) !=null
                    && !temp.equals(oldStr); j++
            ){
                buf = buf.append(temp);
                buf = buf.append(System.getProperty("line.separator"));
            }

            buf = buf.append(newStr);

            while ((temp = br.readLine()) !=null){
                buf = buf.append(System.getProperty("line.separator"));
                buf = buf.append(temp);
            }

            br.close();
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw  = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getTxt() {
        System.out.println("你好!");
    }


    public static void main(String[] args) {
        TxtServiceImpl txtService = new TxtServiceImpl();
        System.out.println(txtService.readTxt());
    }
}

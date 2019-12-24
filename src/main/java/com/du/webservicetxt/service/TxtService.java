package com.du.webservicetxt.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.File;

@WebService(targetNamespace = "http://service.webservicetxt.du.com",name = "txt")
public interface TxtService {



    @WebMethod
    public void getTxt();

    @WebMethod
    public String readTxt();

    @WebMethod
    public void writeTxt(String newStr);

    @WebMethod
    public void replaceTxt(String oldStr,String newStr);
}

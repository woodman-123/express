package com.heima.express.message.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.17
 * 2020-01-09T18:46:37.082+08:00
 * Generated source version: 3.1.17
 * 
 */
@WebService(targetNamespace = "http://service.message.express.heima.com/", name = "MsgService")
//@XmlSeeAlso({ObjectFactory.class})
public interface MsgService {

    @WebMethod
    @RequestWrapper(localName = "sendCheckSms", targetNamespace = "http://service.message.express.heima.com/", className = "com.heima.express.message.client.SendCheckSms")
    @ResponseWrapper(localName = "sendCheckSmsResponse", targetNamespace = "http://service.message.express.heima.com/", className = "com.heima.express.message.client.SendCheckSmsResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String sendCheckSms(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    ) throws Exception;

    @WebMethod
    @RequestWrapper(localName = "sendQjCms", targetNamespace = "http://service.message.express.heima.com/", className = "com.heima.express.message.client.SendQjCms")
    @ResponseWrapper(localName = "sendQjCmsResponse", targetNamespace = "http://service.message.express.heima.com/", className = "com.heima.express.message.client.SendQjCmsResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String sendQjCms(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    ) throws Exception;
}
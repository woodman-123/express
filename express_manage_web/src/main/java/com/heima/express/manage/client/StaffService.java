package com.heima.express.manage.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.heima.express.common.DataGridResult;

/**
 * This class was generated by Apache CXF 3.1.17
 * 2020-01-06T22:04:58.600+08:00
 * Generated source version: 3.1.17
 * 
 */
@WebService(targetNamespace = "http://service.manage.express.heima.com/", name = "StaffService")
//@XmlSeeAlso({ObjectFactory.class})
public interface StaffService {

    @WebMethod
    @RequestWrapper(localName = "findStaffByInput", targetNamespace = "http://service.manage.express.heima.com/", className = "com.heima.express.manage.client.FindStaffByInput")
    @ResponseWrapper(localName = "findStaffByInputResponse", targetNamespace = "http://service.manage.express.heima.com/", className = "com.heima.express.manage.client.FindStaffByInputResponse")
    @WebResult(name = "return", targetNamespace = "")
    public DataGridResult findStaffByInput(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.Integer arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.Integer arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        java.lang.String arg6,
        @WebParam(name = "arg7", targetNamespace = "")
        java.lang.Integer arg7,
        @WebParam(name = "arg8", targetNamespace = "")
        java.lang.Integer arg8
    );

    @WebMethod
    @RequestWrapper(localName = "addStaff", targetNamespace = "http://service.manage.express.heima.com/", className = "com.heima.express.manage.client.AddStaff")
    @ResponseWrapper(localName = "addStaffResponse", targetNamespace = "http://service.manage.express.heima.com/", className = "com.heima.express.manage.client.AddStaffResponse")
    @WebResult(name = "return", targetNamespace = "")
    public int addStaff(
        @WebParam(name = "arg0", targetNamespace = "")
        com.heima.express.manage.client.BcStaff arg0
    );

    @WebMethod
    @RequestWrapper(localName = "delStaffsByCheck", targetNamespace = "http://service.manage.express.heima.com/", className = "com.heima.express.manage.client.DelStaffsByCheck")
    @ResponseWrapper(localName = "delStaffsByCheckResponse", targetNamespace = "http://service.manage.express.heima.com/", className = "com.heima.express.manage.client.DelStaffsByCheckResponse")
    @WebResult(name = "return", targetNamespace = "")
    public int delStaffsByCheck(
        @WebParam(name = "arg0", targetNamespace = "")
        int[] ids
    );
}

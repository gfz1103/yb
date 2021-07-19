/**
 * SendRecv_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.buit.his.gpo.ws.wsdl;

public interface SendRecv_Service extends javax.xml.rpc.Service {
    public String getdispatcherAddress();

    public com.buit.his.gpo.ws.wsdl.DispatcherService getdispatcher() throws javax.xml.rpc.ServiceException;

    public com.buit.his.gpo.ws.wsdl.DispatcherService getdispatcher(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}

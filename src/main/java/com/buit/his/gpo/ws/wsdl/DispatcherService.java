/**
 * DispatcherService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.buit.his.gpo.ws.wsdl;

public interface DispatcherService extends java.rmi.Remote {
    public String sendRecv(String sUser, String sPwd, String sJgbm, String sVersion, String sXxlx, String sSign, String xmlData) throws java.rmi.RemoteException;
}

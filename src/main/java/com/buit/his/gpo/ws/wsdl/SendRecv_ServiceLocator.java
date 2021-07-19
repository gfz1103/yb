/**
 * SendRecv_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.buit.his.gpo.ws.wsdl;

public class SendRecv_ServiceLocator extends org.apache.axis.client.Service
        implements com.buit.his.gpo.ws.wsdl.SendRecv_Service {

    //private YgptConfingure  ygptConfingure;




    public SendRecv_ServiceLocator() {

        //this.ygptConfingure= SpringUtil.getBean(YgptConfingure.class);

    }


    public SendRecv_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SendRecv_ServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for dispatcher
    //private java.lang.String dispatcher_address = "http://192.168.34.71/ysxtqx-wscd/services/dispatcher";
    private String dispatcher_address = "http://172.22.140.254/ysxtqx-wscd/services/dispatcher";

    public String getdispatcherAddress() {

        System.out.println("getdispatcherAddress-----1111");
        dispatcher_address="http://172.22.140.254/ysxtqx-wscs/services/dispatcher";
        System.out.println(dispatcher_address);

        return dispatcher_address;
    }

    // The WSDD service name defaults to the port name.
    private String dispatcherWSDDServiceName = "dispatcher";

    public String getdispatcherWSDDServiceName() {
        return dispatcherWSDDServiceName;
    }

    public void setdispatcherWSDDServiceName(String name) {
        dispatcherWSDDServiceName = name;
    }

    public DispatcherService getdispatcher() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            //dispatcher_address="http://"+ygptConfingure.getIp()+"/ysxtqx-ws/services/dispatcher";//正式地址
            //dispatcher_address="http://172.22.140.254/ysxtqx-wscs/services/dispatcher";//测试地址
            dispatcher_address="http://172.22.140.254/ysxt-wscs/service/mainservice?wsdl";//测试地址
            System.out.println(dispatcher_address);
            endpoint = new java.net.URL(dispatcher_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getdispatcher(endpoint);
    }

    public DispatcherService getdispatcher(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            SendRecvSoapBindingStub _stub = new SendRecvSoapBindingStub(portAddress, this);
            _stub.setPortName(getdispatcherWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setdispatcherEndpointAddress(String address) {
        dispatcher_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (DispatcherService.class.isAssignableFrom(serviceEndpointInterface)) {
                SendRecvSoapBindingStub _stub = new SendRecvSoapBindingStub(new java.net.URL(dispatcher_address), this);
                _stub.setPortName(getdispatcherWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("dispatcher".equals(inputPortName)) {
            return getdispatcher();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.framework.shys.wondersgroup.com/", "SendRecv");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.framework.shys.wondersgroup.com/", "dispatcher"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("dispatcher".equals(portName)) {
            setdispatcherEndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

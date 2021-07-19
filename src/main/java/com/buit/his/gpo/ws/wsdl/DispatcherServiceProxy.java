package com.buit.his.gpo.ws.wsdl;

public class DispatcherServiceProxy implements com.buit.his.gpo.ws.wsdl.DispatcherService {
  private String _endpoint = null;
  private com.buit.his.gpo.ws.wsdl.DispatcherService dispatcherService = null;

  public DispatcherServiceProxy() {
    _initDispatcherServiceProxy();
  }

  public DispatcherServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initDispatcherServiceProxy();
  }

  private void _initDispatcherServiceProxy() {
    try {
      dispatcherService = (new com.buit.his.gpo.ws.wsdl.SendRecv_ServiceLocator()).getdispatcher();
      if (dispatcherService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)dispatcherService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)dispatcherService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }

    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }

  public String getEndpoint() {
    return _endpoint;
  }

  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (dispatcherService != null)
      ((javax.xml.rpc.Stub)dispatcherService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

  }

  public com.buit.his.gpo.ws.wsdl.DispatcherService getDispatcherService() {
    if (dispatcherService == null)
      _initDispatcherServiceProxy();
    return dispatcherService;
  }

  public String sendRecv(String sUser, String sPwd, String sJgbm, String sVersion, String sXxlx, String sSign, String xmlData) throws java.rmi.RemoteException{
    if (dispatcherService == null)
      _initDispatcherServiceProxy();
    return dispatcherService.sendRecv(sUser, sPwd, sJgbm, sVersion, sXxlx, sSign, xmlData);
  }


}
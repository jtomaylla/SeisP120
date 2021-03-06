package org.ses.android.soap.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.SocketException;

public class FormList1Task extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... params) {

        String resul= "";

        String urlserver = params[3];
        final String NAMESPACE = urlserver+"/";
        final String URL=NAMESPACE+"WSSEIS/WSParticipante.asmx";
        final String METHOD_NAME = "ListadoFormatos1";
        final String SOAP_ACTION = NAMESPACE+METHOD_NAME;
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("CodigoUsuario", params[0]);
        request.addProperty("CodigoLocal", params[1]);
        request.addProperty("CodigoProyecto", params[2]);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(URL);
        transporte.debug = true;
        try
        {
            transporte.call(SOAP_ACTION, envelope);

//			SoapObject resSoap =(SoapObject)envelope.getResponse();
//			SoapObject crfs = (SoapObject)resSoap.getProperty(0);
            SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
            resul = resultado_xml.toString();


        }
        catch (SocketException ex) {
            Log.e("Error : " , "Error on soapPrimitiveData() " + ex.getMessage());
            ex.printStackTrace();
            resul = null;
        } catch (Exception e) {
            Log.e("Error : " , "Error on soapPrimitiveData() " + e.getMessage());
            e.printStackTrace();
            resul = null;
        }

        return resul;
    }
}

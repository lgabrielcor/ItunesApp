package controler.aplicacion;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.Aplicacion;


/**
 * Created by luisgabrielcorredorcombita on 24/06/16.
 */
public class JsonServicioClienteAplicacion extends AsyncTask<URL, Void, List<Aplicacion>> {

    HttpURLConnection con = null;

    @Override
    protected List<Aplicacion> doInBackground(URL... urls) {
        List aplicaciones = null;

        // Establecer la conexión
        try {
            con = (HttpURLConnection)urls[0].openConnection();
            con.setConnectTimeout(15000);
            con.setReadTimeout(10000);

            int statusCode = con.getResponseCode();

            if(statusCode!= 200){
                aplicaciones = new ArrayList();
                aplicaciones.add(new Aplicacion());

            }else{

                InputStream in = new BufferedInputStream(con.getInputStream());

                //llamar el parser
                GsonAplicacionParser parser = new GsonAplicacionParser();

                aplicaciones = parser.readJsonStream(in);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }


        return aplicaciones;
    }
}

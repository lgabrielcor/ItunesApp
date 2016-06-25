package controler.categoria;

import android.os.AsyncTask;

import model.Categoria;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by luisgabrielcorredorcombita on 24/06/16.
 */
public class JsonServicioClienteCategoria extends AsyncTask<URL, Void, List<Categoria>> {

    HttpURLConnection con = null;

    @Override
    protected List<Categoria> doInBackground(URL... urls) {
        List categorias = null;

        // Establecer la conexión
        try {
            con = (HttpURLConnection)urls[0].openConnection();
            con.setConnectTimeout(15000);
            con.setReadTimeout(10000);

            int statusCode = con.getResponseCode();

            if(statusCode!= 200){
                categorias = new ArrayList();
                categorias.add(new Categoria());

            }else{

                InputStream in = new BufferedInputStream(con.getInputStream());

                //llamar el parser
                GsonCategoriaParser parser = new GsonCategoriaParser();

                categorias = parser.readJsonStream(in);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }


        return null;
    }
}

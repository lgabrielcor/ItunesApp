package view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.MalformedURLException;
import java.net.URL;

import app.lugcor.co.com.itunesapp.R;
import controler.aplicacion.JsonServicioClienteAplicacion;
import controler.categoria.JsonServicioClienteCategoria;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            URL url = new URL("https://itunes.apple.com/co/rss/topfreeapplications/limit=10/genre=6018/json");//https://itunes.apple.com/co/rss/topfreeapplications/limit=10/genre=6018/json
            //"https://itunes.apple.com/WebObjects/MZStoreServices.woa/ws/genres?id=36"

            JsonServicioClienteAplicacion json = new JsonServicioClienteAplicacion();
            json.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
}

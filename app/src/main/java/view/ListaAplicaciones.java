package view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import app.lugcor.co.com.itunesapp.R;
import controler.aplicacion.JsonServicioClienteAplicacion;
import controler.aplicacion.AdaptadorAplicaciones;

import model.Aplicacion;


public class ListaAplicaciones extends AppCompatActivity {

    ListView listaAplicaciones;
    ArrayAdapter adaptadorAplicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aplicaciones);

        try {

            URL url = new URL("https://itunes.apple.com/co/rss/topfreeapplications/limit=10/genre=6018/json");//https://itunes.apple.com/co/rss/topfreeapplications/limit=10/genre=6018/json
            //"https://itunes.apple.com/WebObjects/MZStoreServices.woa/ws/genres?id=36"
            JsonServicioClienteAplicacion json = new JsonServicioClienteAplicacion();
            json.execute(url);
            List<Aplicacion> aplicacionesdts = json.get();

            adaptadorAplicaciones = new AdaptadorAplicaciones(this, aplicacionesdts);

            listaAplicaciones.setAdapter(adaptadorAplicaciones);
            listaAplicaciones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getBaseContext(), AplicacionDetalle.class);
                    Log.d("evento generado", "evento");
                    startActivity(intent);
                }
            });
            listaAplicaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), AplicacionDetalle.class);
                    Log.d("evento geneado"+position, "evento");
                    startActivity(intent);
                }
            });

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

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
import controler.categoria.AdaptadorCategorias;
import controler.categoria.JsonServicioClienteCategoria;
import model.Categoria;

public class ListaCategorias extends AppCompatActivity {

    ListView listaCategorias;
    ArrayAdapter adaptadorCategorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaCategorias = (ListView)findViewById(R.id.listViewCategoria);

        try {
            URL url = new URL("https://itunes.apple.com/WebObjects/MZStoreServices.woa/ws/genres?id=36");//https://itunes.apple.com/co/rss/topfreeapplications/limit=10/genre=6018/json
            //"https://itunes.apple.com/WebObjects/MZStoreServices.woa/ws/genres?id=36"

            JsonServicioClienteCategoria json = new JsonServicioClienteCategoria();
            json.execute(url);
            List<Categoria> categoriasdts = json.get();
            adaptadorCategorias = new AdaptadorCategorias(this, categoriasdts);

            listaCategorias.setAdapter(adaptadorCategorias);
            listaCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), ListaAplicaciones.class);
                    Log.d("evento geneado"+position, "evento");
                    startActivity(intent);
                }
            });

            listaCategorias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("evento geneado", "evento");
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}

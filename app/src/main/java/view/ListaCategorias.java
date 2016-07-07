package view;

import android.app.Activity;
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
import controler.util.EstadoInternet;
import dao.crudCache;
import model.Categoria;

public class ListaCategorias extends Activity {

    ListView listaCategorias;
    ArrayAdapter adaptadorCategorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        listaCategorias = (ListView)findViewById(R.id.listViewCategorias2);

        try {
            final List<Categoria> categoriasdts;
            if(EstadoInternet.isOnline(getBaseContext())) {
                URL url = new URL("https://itunes.apple.com/WebObjects/MZStoreServices.woa/ws/genres?id=36");//https://itunes.apple.com/co/rss/topfreeapplications/limit=10/genre=6018/json
                //"https://itunes.apple.com/WebObjects/MZStoreServices.woa/ws/genres?id=36"

                JsonServicioClienteCategoria json = new JsonServicioClienteCategoria();
                json.setContext(getBaseContext());
                json.execute(url);
                categoriasdts = json.get();
            }
            else{
                categoriasdts = new crudCache(getBaseContext()).getObjectsCategoria();
            }
            adaptadorCategorias = new AdaptadorCategorias(this, categoriasdts);

            listaCategorias.setAdapter(adaptadorCategorias);

            listaCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        Intent intent = new Intent(getBaseContext(), ListaAplicaciones.class);
                        intent.putExtra("categoria", categoriasdts.get(position).getCodigo());
                        startActivity(intent);
                    }catch(Exception e){

                        e.printStackTrace();
                    }
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

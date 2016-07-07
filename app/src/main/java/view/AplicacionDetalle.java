package view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import app.lugcor.co.com.itunesapp.R;
import model.Aplicacion;

public class AplicacionDetalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplicacion_detalle);

        Bundle extras = getIntent().getExtras();

        TextView nombre = (TextView)findViewById(R.id.nombre);
        TextView actualizacion = (TextView)findViewById(R.id.actualizacion);
        TextView resumen = (TextView)findViewById(R.id.resumen);
        TextView categoria = (TextView)findViewById(R.id.categoria);
        TextView precio = (TextView)findViewById(R.id.precio);
        ImageView imagen = (ImageView)findViewById(R.id.imagen);


        Aplicacion aplicacion = new Aplicacion();
        if(extras != null){
            aplicacion.setNombre( extras.getString("nombre"));
            aplicacion.setUltimaActualizacion( extras.getString("actualizacion"));
            aplicacion.setResumen(extras.getString("resumen"));
            aplicacion.setCategoria(extras.getString("categoria"));
            aplicacion.setPrecio(extras.getString("precio"));
            aplicacion.setImagen(extras.getString("imagen"));
        }

        nombre.setText(aplicacion.getNombre());
        actualizacion.setText(aplicacion.getUltimaActualizacion());
        resumen.setText(aplicacion.getResumen());
        categoria.setText(aplicacion.getCategoria());
        precio.setText(aplicacion.getPrecio());

        URL url = null;
        try {
            url = new URL(aplicacion.getImagen());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imagen.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

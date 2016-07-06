package controler.aplicacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import app.lugcor.co.com.itunesapp.R;
import model.Aplicacion;
import model.Categoria;

/**
 * Created by luisgabrielcorredorcombita on 25/06/16.
 */
public class AdaptadorAplicaciones extends ArrayAdapter
{
    public AdaptadorAplicaciones(Context context, List objects) {

        super(context, android.R.layout.two_line_list_item, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)getContext() .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = convertView;

        if (null == convertView) { //Si no existe, entonces inflarlo
             v = inflater.inflate( R.layout.item_de_aplicacion, parent, false);
        }
        //llenar los campos
        TextView nombreAplicacion = (TextView)v.findViewById(R.id.nombre_aplicacion);
        TextView precio = (TextView)v.findViewById(R.id.precio_aplicacion);
        TextView ultimaActualizacion = (TextView)v.findViewById(R.id.ultima_actualizacion);

        Aplicacion item = (Aplicacion)getItem(position);

        /*
        * TODO: falta categoria, imagen, imegen byte, resumen para el adapter array
        * */

        nombreAplicacion.setText(item.getNombre());
        precio.setText(item.getPrecio());
        ultimaActualizacion.setText(item.getUltimaActualizacion());


        return v;
    }
}

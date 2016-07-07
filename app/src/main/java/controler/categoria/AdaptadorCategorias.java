package controler.categoria;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.lugcor.co.com.itunesapp.R;
import model.Categoria;

/**
 * Created by luisgabrielcorredorcombita on 25/06/16.
 */
public class AdaptadorCategorias extends ArrayAdapter
{
    private static LayoutInflater inflater = null;

    public AdaptadorCategorias(Context context, List objects) {
        super(context, android.R.layout.two_line_list_item, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)getContext() .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = convertView;


        if (null == convertView) { //Si no existe, entonces inflarlo
             view = inflater.inflate( R.layout.item_lista, parent, false);
        }

        TextView categoria = (TextView)view.findViewById(R.id.categoria);
        TextView categoriaCodigo = (TextView)view.findViewById(R.id.categoriacodigo);

        Categoria item = (Categoria)getItem(position);

        categoria.setText(item.getNombre());
        categoriaCodigo.setText(item.getCodigo());

        return view;
    }
}

package controler.aplicacion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import app.lugcor.co.com.itunesapp.R;
import model.Aplicacion;
import model.Categoria;

/**
 * Created by luisgabrielcorredorcombita on 25/06/16.
 */
public class AdaptadorAplicaciones extends ArrayAdapter
{
    private byte[] byteImage;
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
        ImageView imagen = (ImageView)v.findViewById(R.id.image_aplicacion);

        Aplicacion item = (Aplicacion)getItem(position);

        nombreAplicacion.setText(item.getNombre());
        precio.setText(item.getPrecio());
        ultimaActualizacion.setText("Ultima Versión: "+item.getUltimaActualizacion());
        URL url = null;
        try {
            url = new URL(item.getImagen());
            InputStream ips = url.openConnection().getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(ips);

            setByteImage(readBytes(ips));

            imagen.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return v;
    }

    public byte[] getByteImage() {
        return byteImage;
    }

    public void setByteImage(byte[] byteImage) {
        this.byteImage = byteImage;
    }

    public byte[] readBytes(InputStream inputStream) throws IOException {
        // this dynamically extends to take the bytes you read
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }
}


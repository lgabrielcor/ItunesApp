package controler.aplicacion;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Aplicacion;

/**
 * Created by luisgabrielcorredorcombita on 24/06/16.
 */
public class GsonAplicacionParser {
    public List readJsonStream(InputStream in) throws IOException {

        Gson gson = new Gson();
        Aplicacion aplicacion;
        List aplicaciones = new ArrayList();

        try {
            JSONObject reader = new JSONObject(convertStreamToString(in));
            JSONArray entry = reader.optJSONObject("feed").getJSONArray("entry");


            for (int i =0; i< entry.length(); i++) {
                aplicacion = new Aplicacion();
                extraerAplicacion(aplicacion, entry, i);

                aplicaciones.add(aplicacion);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return aplicaciones;
    }

    private void extraerAplicacion(Aplicacion aplicacion, JSONArray entry, int i) throws JSONException {
        JSONObject jsonObject = entry.getJSONObject(i);

        aplicacion.setNombre(jsonObject.getJSONObject("im:name").optString("label"));
        aplicacion.setImagen(jsonObject.getJSONArray("im:image").getJSONObject(0).optString("label"));
        aplicacion.setResumen(jsonObject.getJSONObject("summary").optString("label"));


        aplicacion.setPrecio(jsonObject.getJSONObject("im:price").getJSONObject("attributes").optString("currency")+
                jsonObject.getJSONObject("im:price").getJSONObject("attributes").optString("amount"));

        aplicacion.setCategoria(jsonObject.getJSONObject("category").getJSONObject("attributes").optString("label"));

        aplicacion.setUltimaActualizacion(jsonObject.getJSONObject("im:releaseDate").optString("label"));
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}

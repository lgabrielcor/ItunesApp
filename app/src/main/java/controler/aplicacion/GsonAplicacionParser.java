package controler.aplicacion;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.Aplicacion;

/**
 * Created by luisgabrielcorredorcombita on 24/06/16.
 */
public class GsonAplicacionParser {
    public List readJsonStream(InputStream in) throws IOException {

        Gson gson = new Gson();

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List aplicaciones = new ArrayList();
        reader.beginObject();

        while (reader.hasNext()) {
            //busca los subgeneros


            String subgenres = reader.nextName();
            if (subgenres.contains("subgenres") && !subgenres.isEmpty()) {
                //recorre los objetos
                while (reader.hasNext()) {
                    Aplicacion aplicacion = new Aplicacion();
                    //categoria.setCodigo(String.valueOf(reader.nextInt()));
                    //categoria.setCodigo("0");
                    //categoria.setNombre("prueba");
                    aplicaciones.add(aplicacion);
                }

                //Categoria categoria = gson.fromJson(reader, Categoria.class);

            }

        }
        reader.endArray();
        reader.close();

        return aplicaciones;
    }
}

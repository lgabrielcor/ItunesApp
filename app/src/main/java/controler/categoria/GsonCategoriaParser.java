package controler.categoria;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;

/**
 * Created by luisgabrielcorredorcombita on 24/06/16.
 */
public class GsonCategoriaParser {
    public List readJsonStream(InputStream in) throws IOException {

        Gson gson = new Gson();

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List categorias = new ArrayList();
        reader.beginObject();

        while (reader.hasNext()) {
            //busca los subgeneros


            String subgenres = reader.nextName();
            if (subgenres.contains("subgenres") && !subgenres.isEmpty()) {
                //recorre los objetos
                while (reader.hasNext()) {
                    Categoria categoria = new Categoria();
                    //categoria.setCodigo(String.valueOf(reader.nextInt()));
                    categoria.setCodigo("0");
                    categoria.setNombre("prueba");
                    categorias.add(categoria);
                }

                //Categoria categoria = gson.fromJson(reader, Categoria.class);

            }

        }
        reader.endObject();
        reader.close();

        return categorias;
    }
}

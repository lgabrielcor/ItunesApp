package controler.categoria;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dao.crudCache;
import model.Categoria;

/**
 * Created by luisgabrielcorredorcombita on 24/06/16.
 */
public class GsonCategoriaParser
{
    Context context;
    public GsonCategoriaParser(Context context){
        this.context=context;
    }
    public List readJsonStream(InputStream in) throws IOException {


        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List categorias = new ArrayList();
        reader.beginObject();

        while (reader.hasNext()) {

            String name = reader.nextName();

            reader.beginObject();
            while(reader.hasNext()){

                name = reader.nextName();
                String value="";
                if(name.equals("name")||name.equals("id")||name.equals("url")){
                    value = reader.nextString();
                }
                else if(name.equals("rssUrls")||name.equals("chartUrls")) {
                    reader.skipValue();
                }

                if (name.contains("subgenres")) {
                    //recorre los objetos
                    reader.beginObject();
                    while (reader.hasNext()) {

                        Categoria categoria = new Categoria();

                        categoria.setCodigo(reader.nextName());


                        reader.beginObject();
                        while (reader.hasNext()){
                            name = reader.nextName();
                            if(name.equals("name")){
                                categoria.setNombre(reader.nextString());
                            }else {
                                reader.skipValue();
                            }
                        }
                        reader.endObject();
                        categorias.add(categoria);
                    }
                    reader.endObject();
                }
            }
            reader.endObject();
        }
        reader.endObject();
        reader.close();

        return categorias;
    }
}

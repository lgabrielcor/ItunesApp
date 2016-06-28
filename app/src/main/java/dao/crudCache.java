package dao;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import model.Aplicacion;
import model.Categoria;


/**
 * Created by luis.corredor on 27/06/2016.
 */
public class crudCache extends SQLiteOpenHelper  implements iCrud{
    private static final java.lang.String SQL_CREATE_ENTRIES = "" ;
    private static final java.lang.String SQL_DELETE_ENTRIES = "";

    String creadorCategoria= "CREATE TABLE `categoria` (\n" +
            "\t`idCategoria`\tTEXT NOT NULL,\n" +
            "\t`nombreCategoria`\tTEXT NOT NULL\n" +
            ");";

    String creadorAplicacion ="CREATE TABLE `aplicacion` (\n" +
            "\t`idAplicacion`\tTEXT NOT NULL,\n" +
            "\t`nombre`\tTEXT NOT NULL,\n" +
            "\t`resumen`\tTEXT NOT NULL,\n" +
            "\t`precio`\tTEXT NOT NULL,\n" +
            "\t`nombreImagen`\tTEXT NOT NULL,\n" +
            "\t`actualizacion`\tTEXT NOT NULL,\n" +
            "\t`categoria`\tTEXT NOT NULL,\n" +
            "\t`imagen`\tBLOB NOT NULL\n" +
            ");";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "cache.sqlite";

    public crudCache(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public boolean insertDataCategoria(List<Categoria> objects) {
        SQLiteDatabase db = getWritableDatabase();
        boolean result= false;
        for (Categoria obj:objects)
        {
            if(db != null){
                ContentValues valores = new ContentValues();
                valores.put("idCategoria", obj.getCodigo());
                valores.put("nombreCategoria", obj.getNombre());
                final long categoria = db.insert("categoria", null, valores);
                db.close();

                result= categoria>-1?true:false;
            }

        }

        return result;
    }

    @Override
    public boolean insertDataAplicacion(List<Aplicacion> objects) {
        SQLiteDatabase db = getWritableDatabase();
        boolean result= false;
        for (Aplicacion obj:objects)
        {
            if(db != null){
                ContentValues valores = new ContentValues();
                valores.put("idAplicacion", obj.getNombre());
                valores.put("nombre", obj.getNombre());
                valores.put("resumen", obj.getResumen());
                valores.put("actualizacion", obj.getUltimaActualizacion());
                valores.put("precio", obj.getPrecio());
                valores.put("nombreImagen", obj.getNombre());
                valores.put("imagen", getLogoImage(obj.getNombre()));


                final long categoria = db.insert("aplicacion", null, valores);

                db.close();

                result= categoria>-1?true:false;
            }

        }

        return result;
    }

    private byte[] getLogoImage(String url) {

        byte[] data = new byte[64];
        try {
            URL imageUrl = new URL(url);
            URLConnection ucon = imageUrl.openConnection();

            InputStream is = ucon.getInputStream();

            BufferedInputStream bis = new BufferedInputStream(is);

            ByteArrayOutputStream baf = new ByteArrayOutputStream();

            int current = 0;
            while ((current = bis.read(data,0,data.length)) != -1) {
                baf.write(data,0,current);
            }

            return baf.toByteArray();
        } catch (Exception e) {
            Log.d("ImageManager", "Error: " + e.toString());
        }
        return data;
    }

    @Override
    public List<Categoria> getObjectsCategoria() {
        SQLiteDatabase db = getReadableDatabase();
        List<Categoria> lista_categorias = new ArrayList<Categoria>();

        String[] valores_recuperar = {"idCategoria", "nombreCategoria"};

        Cursor c = db.query("categoria", valores_recuperar,
                null, null, null, null, null, null);

        c.moveToFirst();

        do {
            Categoria categoria = new Categoria();
            categoria.setCodigo(c.getString(0));
            categoria.setNombre(c.getString(1));
            lista_categorias.add(categoria);
        } while (c.moveToNext());
        db.close();
        c.close();
        return lista_categorias;
    }

    @Override
    public List<Aplicacion> getObjectsAplicacion(String categoria) {

        SQLiteDatabase db = getReadableDatabase();
        List<Aplicacion> lista_aplicacion = new ArrayList<Aplicacion>();

        String[] valores_recuperar = {"idAplicacion", "nombre","resumen", "precio","nombreImagen", "actualizacion","categoria", "imagen"};

        String[] args = new String[] {categoria};

        Cursor c = db.query("aplicacion", valores_recuperar,
                "categoria=?", args, null, null, null, null);

        c.moveToFirst();

        do {
            Aplicacion aplicacion = new Aplicacion();

            aplicacion.setNombre(c.getString(0));
            aplicacion.setNombre(c.getString(1));
            aplicacion.setResumen(c.getString(2));
            aplicacion.setPrecio(c.getString(3));
            aplicacion.setNombre(c.getString(4));
            aplicacion.setUltimaActualizacion(c.getString(5));
            aplicacion.setCategoria(c.getString(6));
            aplicacion.setImagebyte(c.getBlob(7));

            lista_aplicacion.add(aplicacion);
        } while (c.moveToNext());
        db.close();
        c.close();
        return lista_aplicacion;
    }

    @Override
    public void resetData() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + creadorCategoria);
        db.execSQL("DROP TABLE IF EXISTS " + creadorAplicacion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creadorCategoria+creadorAplicacion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + creadorCategoria);
        db.execSQL("DROP TABLE IF EXISTS " + creadorAplicacion);
        onCreate(db);
    }
}

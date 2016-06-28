package dao;

import java.util.List;

import model.Aplicacion;
import model.Categoria;

/**
 * Created by luis.corredor on 27/06/2016.
 */
public interface iCrud {
    boolean insertDataCategoria(List<Categoria> objects);
    boolean insertDataAplicacion(List<Aplicacion> objects);
    List<Categoria> getObjectsCategoria();
    List<Aplicacion> getObjectsAplicacion(String categoria);
    void resetData();
}

package dao;

import java.util.List;

/**
 * Created by luis.corredor on 27/06/2016.
 */
public interface iCrud {
    boolean createDb();
    boolean resetDb();
    boolean insertData(List objects);
    List getObjects();
}

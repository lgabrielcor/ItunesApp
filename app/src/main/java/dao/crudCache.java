package dao;

import java.util.List;

/**
 * Created by luis.corredor on 27/06/2016.
 */
public class crudCache implements iCrud {
    @Override
    public boolean createDb() {
        return false;
    }

    @Override
    public boolean resetDb() {
        return false;
    }

    @Override
    public boolean insertData(List objects) {
        return false;
    }

    @Override
    public List getObjects() {
        return null;
    }
}

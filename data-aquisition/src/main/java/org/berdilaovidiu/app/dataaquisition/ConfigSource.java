package org.berdilaovidiu.app.dataaquisition;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 11/27/11
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigSource {

    public List<DataSource> getDataSources();


    public void saveDataSource(DataSource newDataSource);
}

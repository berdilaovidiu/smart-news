package org.berdilaovidiu.app.dataaquisition;

import org.berdilaovidiu.app.NewsItemHandler;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 11/27/11
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataConnectionManager {
    ConfigSource configSource;

    public DataConnectionManager(ConfigSource configSource) {
        this.configSource = configSource;
    }

    public void subscribeToAllSources(NewsItemHandler handler){
        for(DataSource source :configSource.getDataSources()){
            source.getDataConnection().subscribe(handler);
        }
    }

    public void unsubscribeFromAllSources(){
         for(DataSource source :configSource.getDataSources()){
            source.getDataConnection().unsubscribe();
        }
    }
}


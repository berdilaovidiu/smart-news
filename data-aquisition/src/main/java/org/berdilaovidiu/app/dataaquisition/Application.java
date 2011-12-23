package org.berdilaovidiu.app.dataaquisition;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 11/27/11
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Application {

    public static void main(String[] args) {
        String configFileName = "datasources.xml";
        ConfigSource configSource = new XMLConfigSource(configFileName);
        DataConnectionManager connectionManager = new DataConnectionManager(configSource);
//        connectionManager.subscribeToAllSources();
    }
}

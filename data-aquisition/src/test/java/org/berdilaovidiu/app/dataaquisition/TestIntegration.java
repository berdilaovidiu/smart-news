package org.berdilaovidiu.app.dataaquisition;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 11/28/11
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestIntegration {
    public static void main(String[] args){
        String configFileName = "datasource.xml";
        ConfigSource configSource = new XMLConfigSource(configFileName);
        DataConnectionManager connectionManager = new DataConnectionManager(configSource);
        connectionManager.subscribeToAllSources(new DummyNewsItemHandler());
    }
}

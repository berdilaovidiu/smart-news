package org.berdilaovidiu.app;

import org.berdilaovidiu.app.dataaquisition.ConfigSource;
import org.berdilaovidiu.app.dataaquisition.DataConnectionManager;
import org.berdilaovidiu.app.dataaquisition.XMLConfigSource;
import org.ontoware.text2onto.corpus.CorpusException;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 1/12/12
 * Time: 12:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class IntegrationTest {
    public static void main(String[] args) throws IOException, CorpusException {
        final ConcreteNewsItemHandler newsHandler = new ConcreteNewsItemHandler();

        Runnable runnable = new Runnable() {
            private DataConnectionManager connectionManager;

            {
                String configFileName = "datasource.xml";
                ConfigSource configSource = new XMLConfigSource(configFileName);
                connectionManager = new DataConnectionManager(configSource);
            }

            @Override
            public void run() {
                while (true) {
                    connectionManager.subscribeToAllSources(newsHandler);
                }

            }
        };

        Thread aquisitionThread = new Thread(runnable);
        aquisitionThread.start();
    }
}

package org.berdilaovidiu.app;

import org.ontoware.text2onto.corpus.CorpusException;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 1/12/12
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class KnowledgeBase {
    private NewsItemHandler itemHandler;
    private boolean isRunning = false;
    public KnowledgeBase() throws IOException, CorpusException {
        itemHandler = new ConcreteNewsItemHandler();
    }
    
    public void start(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        
    }
    
    public void stop(){
        
    }
}

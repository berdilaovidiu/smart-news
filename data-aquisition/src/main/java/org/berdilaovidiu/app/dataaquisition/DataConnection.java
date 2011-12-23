package org.berdilaovidiu.app.dataaquisition;

import org.berdilaovidiu.app.NewsItemHandler;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 11/27/11
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DataConnection {

    public void subscribe(NewsItemHandler handler);

    public void unsubscribe();
}

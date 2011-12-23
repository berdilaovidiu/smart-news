package org.berdilaovidiu.app.dataaquisition;

import org.berdilaovidiu.app.NewsItem;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 11/27/11
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DataHandler {
    public void handle(NewsItem document);
}

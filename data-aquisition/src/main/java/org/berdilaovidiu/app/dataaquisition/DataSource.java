package org.berdilaovidiu.app.dataaquisition;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 11/27/11
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DataSource {

    public String getName();

    public String getDescription();

    public DataConnection getDataConnection();

    public String getCategory();
}

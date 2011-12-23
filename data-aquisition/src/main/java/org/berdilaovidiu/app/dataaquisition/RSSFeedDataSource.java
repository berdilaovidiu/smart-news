package org.berdilaovidiu.app.dataaquisition;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 11/27/11
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class RSSFeedDataSource implements DataSource{
    private String name;
    private String description;
    private DataConnection dataConnection;
    private String category;

    public RSSFeedDataSource(String name, String description, DataConnection dataConnection, String category) {
        this.name = name;
        this.description = description;
        this.dataConnection = dataConnection;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public DataConnection getDataConnection() {
        return dataConnection;
    }

    public String getCategory() {
        return category;
    }
}

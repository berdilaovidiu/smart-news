package org.berdilaovidiu.app.dataaquisition;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 11/27/11
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class XMLConfigSource implements ConfigSource {
    public static final String DATA_SOURCE_TAG = "datasource";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CATEGORY_ATTRIBUTE = "category";
    public static final String DESCRIPTION_TAG = "description";
    public static final String CONNECTION_TAG = "connection";
    public static final String CONNECTION_TYPE_ATTRIBUTE = "type";
    public static final String RSS_RESOURCE_ATTRIBUTE = "link";

    private Element rootNode;

    public XMLConfigSource(String configFile) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(configFile);

        try {
            Document document = builder.build(xmlFile);
            rootNode = document.getRootElement();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
    }


    public List<DataSource> getDataSources() {
        List<DataSource> result = new ArrayList<DataSource>();
        for (Object dataSource : rootNode.getChildren(DATA_SOURCE_TAG)) {
            result.add(getDataSourceFromElement((Element) dataSource));
        }
        return result;
    }

    private DataSource getDataSourceFromElement(Element dataSource) {
        String name = dataSource.getAttribute(NAME_ATTRIBUTE).getValue();
        String category = dataSource.getAttribute(CATEGORY_ATTRIBUTE).getValue();
        String description = dataSource.getChild(DESCRIPTION_TAG).getText();

        Element connectionElement = dataSource.getChild(CONNECTION_TAG);
        DataSourceType type = DataSourceType.valueOf(connectionElement.getAttribute(CONNECTION_TYPE_ATTRIBUTE).getValue());
        DataConnection connection = null;
        switch (type) {
            case RSS:
                connection = getRSSDataConnection(connectionElement);
                break;
            case EMAIL:
                connection = getEmailDataConnection(connectionElement);
                break;
            case IM:
                connection = getIMDataConnection(connectionElement);
                break;

        }
        return new RSSFeedDataSource(name, description, connection, category);
    }

    private DataConnection getRSSDataConnection(Element element) {
        String resource = element.getAttribute(RSS_RESOURCE_ATTRIBUTE).getValue();
        RSSFeedDataConnection connection = new RSSFeedDataConnection(resource);
        return connection;
    }

    private DataConnection getEmailDataConnection(Element element) {
        return null;
    }

    private DataConnection getIMDataConnection(Element element) {
        return null;
    }

    public void saveDataSource(DataSource newDataSource) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

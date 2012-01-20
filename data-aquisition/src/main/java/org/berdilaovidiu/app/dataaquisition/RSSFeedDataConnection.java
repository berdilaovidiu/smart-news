package org.berdilaovidiu.app.dataaquisition;

import org.apache.commons.feedparser.*;
import org.apache.commons.feedparser.network.NetworkException;
import org.apache.commons.feedparser.network.ResourceRequest;
import org.apache.commons.feedparser.network.ResourceRequestFactory;
import org.berdilaovidiu.app.NewsItem;
import org.berdilaovidiu.app.NewsItemHandler;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 11/27/11
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class RSSFeedDataConnection implements DataConnection {
    private String resource;
    private FeedParser parser;

    public RSSFeedDataConnection(String resource) {
        //create a new FeedParser...
        try {
            parser = FeedParserFactory.newFeedParser();
            this.resource = resource;
        } catch (FeedParserException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void subscribe(final NewsItemHandler handler) {
        System.out.println("Fetching resource:" + resource);


        //create a listener for handling our callbacks
        FeedParserListener listener = new DefaultFeedParserListener() {
            public void onChannel(FeedParserState state,
                                  String title,
                                  String link,
                                  String description) throws FeedParserException {

                System.out.println("Found a new channel: " + title);

            }

            public void onItem(final FeedParserState state,
                               final String title,
                               final String link,
                               String description,
                               String permalink) throws FeedParserException {

                NewsItem item = new NewsItem() {
                    public String getName() {
                        return title;
                    }

                    @Override
                    public URI getURI() {
                        URI result = null;
                        try {
                            result = new URI(link);
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                        return result;
                    }

                    @Override
                    public String getContent() {
                        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
                        return outputter.outputString(state.current.getContent());
                    }
                };
                handler.handleNews(item);
            }

            public void onCreated(FeedParserState state, Date date) throws FeedParserException {
                System.out.println("Which was created on: " + date);
            }

        };

        //use the FeedParser network IO package to fetch our resource URL
        ResourceRequest request = null;
        try {
            request = ResourceRequestFactory.getResourceRequest(resource);
            //grab our input stream
            InputStream is = request.getInputStream();

            //start parsing our feed and have the above onItem methods called
            parser.parse(listener, is, resource);
        } catch (NetworkException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FeedParserException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void unsubscribe() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}

package org.berdilaovidiu.app.dataaquisition;

import org.berdilaovidiu.app.NewsItem;
import org.berdilaovidiu.app.NewsItemHandler;
import org.berdilaovidiu.app.NewsItemSection;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 11/28/11
 * Time: 8:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class DummyNewsItemHandler implements NewsItemHandler {
    public void handleNews(NewsItem item) {
        System.out.println("ITEM NAME : " + item.getName());
        for (NewsItemSection section : item.getSections()) {
            System.out.println("Section : " + section.getTitle());
            System.out.println("Content : " + section.getContent());
        }
        System.out.println("\n\n\n\n");
    }
}

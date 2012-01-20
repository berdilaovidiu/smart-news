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
        System.out.println("URI : " + item.getURI());
        System.out.println("Content : " + getText(item.getContent()));

        System.out.println("\n\n\n\n");
    }

    private String getText(String sPage) {
        sPage = sPage.replaceAll("<(script|SCRIPT)[^>]*>[^(</)]+</(script|SCRIPT)>", " ");
        sPage = sPage.replaceAll("<(style|STYLE)[^>]*>[^(</)]+</(style|STYLE)>", " ");
        sPage = sPage.replaceAll("<[^>]*>", " ");
        sPage = sPage.replaceAll("&amp;", "and");
        sPage = sPage.replaceAll("&#[a-z\\d]", "");
        sPage = sPage.replaceAll("&[a-z]+;", " ");
        sPage = sPage.replaceAll("\\s+", " ");
        sPage = sPage.replaceAll("[ \t\n\f\r]{2,}", "\n");

        return sPage;
    }
}

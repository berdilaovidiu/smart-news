package org.berdilaovidiu.app;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 11/27/11
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface NewsItem {

    public String getName();

    public List<NewsItemSection> getSections();

    public NewsItemSection getSection(String title);
}

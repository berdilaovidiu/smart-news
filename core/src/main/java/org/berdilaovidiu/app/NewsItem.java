package org.berdilaovidiu.app;

import java.net.URI;
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

    public URI getURI();

    public String getContent();

}

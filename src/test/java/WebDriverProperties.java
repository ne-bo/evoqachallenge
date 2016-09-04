import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

import java.net.URL;

/**
 * Created by natasha on 8/29/16.
 */

@Resource.Classpath("webdriver.properties")
public class WebDriverProperties {
    @Property("webdriver.server")
    private URL server;

    public WebDriverProperties() {
        PropertyLoader.populate(this);
    }

    public URL getServer() {
        return server;
    }
}

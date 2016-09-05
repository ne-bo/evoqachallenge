package matchers;


import org.hamcrest.Matcher;
import ru.yandex.qatools.htmlelements.matchers.decorators.MatcherDecoratorsBuilder;

import static java.util.concurrent.TimeUnit.SECONDS;
import static ru.yandex.qatools.htmlelements.matchers.MatcherDecorators.timeoutHasExpired;
import static ru.yandex.qatools.htmlelements.matchers.decorators.MatcherDecoratorsBuilder.should;
import static utils.Constants.DEFAULT_TIMEOUT;

/**
 * Created by natasha on 9/3/16.
 */

/**
 * This matcher is not used in test now. But it was useful for debug. So i decided not to remove it's code.
 */

public class WithTimeout {

    public static <T> MatcherDecoratorsBuilder withWaitFor(Matcher<? super T> matcher, int seconds) {
        return should(matcher).whileWaitingUntil((timeoutHasExpired(SECONDS.toMillis(seconds))));
    }

    public static <T> MatcherDecoratorsBuilder withWaitFor(Matcher<? super T> matcher) {
        return should(matcher).whileWaitingUntil((timeoutHasExpired(SECONDS.toMillis(DEFAULT_TIMEOUT))));
    }
}

package steps;

import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.Thread.sleep;

/**
 * Created by natasha on 8/31/16.
 */
public class CommonSteps {


    @Step
    public CommonSteps waitForSeconds(int seconds) {
        try {
            sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
}

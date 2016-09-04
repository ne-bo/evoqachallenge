package utils;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static utils.Constants.*;

/**
 * Created by natasha on 8/31/16.
 */
public class LocalisationUtils {

    public static String getStringForElectronics(Constants.Language language) {
        return translations.get(language).get(STR_ELECTRONICS);
    }

    public static String getStringForWork(Constants.Language language) {
        return translations.get(language).get(STR_WORK);
    }

    public static String getBlockNameInEnglish(final String blockName, Constants.Language initialLanguage) {
        return translations.get(initialLanguage).entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), blockName))
                .map(Map.Entry::getKey)
                .collect(Collectors.<String>toList()).get(0);
    }

    public static String getStringForPrice(Constants.Language language) {
        return translations.get(language).get(STR_PRICE);
    }

    public static String getStringForSale(Constants.Language language) {
        return translations.get(language).get(STR_SALE);
    }
}

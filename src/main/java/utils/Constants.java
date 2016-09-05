package utils;

import com.google.common.collect.ImmutableMap;
import pages.Electronics;
import pages.Work;

import java.util.Map;

import static utils.Constants.Language.LV;
import static utils.Constants.Language.RU;
import static utils.Constants.SearchFormFields.*;

/**
 * Created by natasha on 8/31/16.
 */
public class Constants {

    public enum Language {RU, LV}

    public static final String STR_ELECTRONICS = "Electronics";
    public static final String STR_WORK = "Work";
    public static final String STR_PRICE = "Price";
    public static final String STR_SALE = "Sale";

    public static final String EMPTY_STRING = "";

    public static final String NOT_AN_ITEM = "Not an item";


    public static final Map<String, Class> blocksClasses = ImmutableMap.<String, Class>builder()
            .put(STR_ELECTRONICS, Electronics.class)
            .put(STR_WORK, Work.class)
            .build();

    public static final Map<Language, Map<String, String>> translations =
            ImmutableMap.<Language, Map<String, String>>builder()
                    .put(RU, ImmutableMap.<String, String>builder()
                            .put(STR_ELECTRONICS, "Электротехника")
                            .put(STR_WORK, "Работа и бизнеc")
                            .put(STR_PRICE, "Цена")
                            .put(STR_SALE, "Продажа")
                            .build())
                    .put(LV, ImmutableMap.<String, String>builder()
                            .put(STR_ELECTRONICS, "Elektrotehnika")
                            .put(STR_WORK, "Darbs un bizness")
                            .put(STR_PRICE, "Cena")
                            .put(STR_SALE, "Pārdod")
                            .build())
                    .build();

    public static final int DEFAULT_TIMEOUT = 1;

    public enum SearchFormFields {KEYWORD, MIN_PRICE, MAX_PRICE}

    public static final Map<Constants.SearchFormFields, String> DEFAULT_SEARCH_DATA =
            ImmutableMap.<Constants.SearchFormFields, String>builder()
                    .put(KEYWORD, EMPTY_STRING)
                    .put(MIN_PRICE, EMPTY_STRING)
                    .put(MAX_PRICE, EMPTY_STRING)
                    .build();

    public enum FiltersForSearchResults {MODE, REGION, TYPE_OF_DEAL}

}

package com.igreendata.account.util;

/**
 * AccountConstant for contains static fields for application.
 * @author Dulip Chandana
 *
 */
public final class AccountConstant {

    /**page definition for swagger query param**/
    public static final String PAGE = "page";

    /**data type for swagger query param**/
    public static final String INTEGER = "int";

    /**query string for swagger query param**/
    public static final String QUERY = "query";

    /**size String for swagger query param**/
    public static final String SIZE = "size";

    /**String data type for swagger query param**/
    public static final String STRING = "string";

    /**sort query parameter in swagger**/
    public static final String SORT = "sort";

    /**sort param description in swagger**/
    public static final String SORT_DESC = "Sorting criteria in the format: property(,asc|desc). " +
            "Default sort order is ascending. " +
            "Multiple sort criteria are supported.";

    /**page description in swagger query param**/
    public static final String PAGE_DESC ="Results page you want to retrieve (0..N)";

    /**page size description in swagger param**/
    public static final String SIZE_DESC = "Number of records per page.";

    /**time stamp in controller adviser**/
    public static final String TIME_STAMP ="timestamp";

    /**Message in controller adviser**/
    public static final String MESSAGE = "message";
}

package com.techbooker.shop.util.contance;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerConstance {
    public static final String API_V1 = "/api/v1";

    //Shop data APIs
    public static final String SHOP = "/shop";
    public static final String SHOP_BY_ID = "/shop/{id}";
    public static final String BRANCH = "/branch";
    public static final String BRANCH_BY_ID = "/branch/{id}";

    //Check validation
    public static final String BRANCH_VALIDATION = "/valid-branch";
    public static final String SHOP_VALIDATION = "/valid-shop";
}

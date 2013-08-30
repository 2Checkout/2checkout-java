package com.twocheckout;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;

public abstract class TwocheckoutReturn {

    public static Boolean check(HashMap<String, String> args, String secret) {
        Boolean response;
        String hashString = secret+args.get("sid")+args.get("order_number")+args.get("total");
        String hash = DigestUtils.md5Hex( hashString ).toUpperCase();
        if (args.get("key").equals(hash)) {
            response = true;
        } else {
            response = false;
        }
        return response;
    }

}

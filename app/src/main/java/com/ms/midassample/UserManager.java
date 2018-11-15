package com.ms.midassample;

import com.ms.midas.Midas;
import com.ms.midas.MidasUser;

/**
 * @author Toby
 */
public class UserManager {
    static void doLogin(String userName, String password) {
        //simulate user login
        SPUtils.setUser(userName);
        //get from your server
        String userToken = "TOKEN|F2672290072543C1801B35A16FACA528";
        //also get from your server
        String midasUserId = "123456";

        MidasUser user = new MidasUser.Builder()
                .midasUserId(midasUserId)
                .userToken(userToken)
                .nickName("Toby")
                .age(18)
                .avatar("https://avatars0.githubusercontent.com/u/24515142?s=200&v=4")
                .gender(MidasUser.MALE)
                .build();
        Midas.bindUser(user);

    }

    static void doLogout() {
        //simulate user logout
        SPUtils.clearUser();
        Midas.unbindUser();
    }
}

package com.ghstk.test;

import com.ghstk.domain.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 2020/12/3 9:34
 */
public class AnyTest {

    @Test
    public void t1() {
        Gson gson = new Gson();
        User user1 = new User("myName1", "12345678", "mName1@666.com");
        User user2 = new User("myName2", "12345678", "mName2@666.com");

        Map<String, User> map = new HashMap<>();
        map.put("u1", user1);
        map.put("u2", user2);

        String s = gson.toJson(map);
//        System.out.println(s);

        Map<String, User> map2 = gson.fromJson(s, new TypeToken<Map<String, User>>() {
        }.getType());

        map2.forEach((key, value) -> System.out.println(value));

    }

}

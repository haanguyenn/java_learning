package test_data;

import com.google.gson.Gson;

public class TestGson {
    public static void main(String[] args) {
        //Convert Json-> object
        String jsonObject = "{\n" +
                "  \"username\": \"hello@gmail.com\";\n" +
                "  \"password\": \"12345678\"\n" +
                "}";
        Gson gson = new Gson();
        LoginCreds loginCreds = gson.fromJson(jsonObject,LoginCreds.class);
        System.out.println(loginCreds);

        //Convert obj -> json
        System.out.println(gson.toJson(loginCreds));


    }
}

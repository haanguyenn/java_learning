package test_data.authentication;

import com.google.gson.Gson;
import test_data.LoginCreds;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {
    public static LoginCreds[] buildLoginCreds(String filePath) {
        LoginCreds[] loginCreds = new LoginCreds[]{};
        String absoluteFilePath = System.getProperty("user.dir") + filePath;
        try (Reader reader = Files.newBufferedReader(Paths.get(absoluteFilePath))) {
            Gson gson = new Gson();

            //Convert json -> loginCred[]
            loginCreds = gson.fromJson(reader, LoginCreds[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginCreds;
    }
//
//    public static void main(String[] args) {
//        String jsonPath = "/src/main/resources/test-data/loginCredsArray.json";
//        LoginCreds[] loginCreds = DataObjectBuilder.buildLoginCreds(jsonPath);
//        for (LoginCreds loginCred : loginCreds) {
//            System.out.println(loginCred);
//        }
//    }
}

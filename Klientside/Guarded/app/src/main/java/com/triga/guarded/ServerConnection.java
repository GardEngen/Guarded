package com.triga.guarded;

import android.os.StrictMode;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gard on 28.10.2016.
 */

public class ServerConnection implements Serializable{

    private String ipAdress;

    public ServerConnection()
    {
        ipAdress = "158.38.193.16";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    //Add user to database
    public void addUserService (String firstName, String lastName, String password, Integer phonenumber, String familyCode, Boolean guardian)
    {

        System.out.println("step2");
        String url = "http://" + ipAdress + ":8080/guardedServer/services/guarded/add/appuser?firstName=" + firstName + "&lastName=" + lastName + "&password=" +
                password + "&phoneNumber=" + phonenumber + "&familyCode=" + familyCode + "&guardian=" + guardian;
        System.out.println(url);

        String USER_AGENT = "Mozilla/5.0";
        URL obj = null;
        try {
            System.out.println("step3");
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            System.out.println("step4");
            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Get all AppUseres in database and return a List of all the AppUsers
    public List<AppUser> getAllUsersService(){
        String json = null;
        try {
            json = readUrl("http://" + ipAdress + ":8080/guardedServer/services/guarded/getall/appusers");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Type listType = new TypeToken<ArrayList<AppUser>>(){}.getType();
        List<AppUser> appUsers = new Gson().fromJson(json, listType);

        System.out.println("Appusers: "+ json);
        System.out.println("HER ER MINE Appusers:: "+ appUsers);
        return appUsers;

    }


    //read Url
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

}

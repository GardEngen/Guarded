package com.triga.guarded;

import android.os.StrictMode;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

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
}

package org.orcid.mailgun;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

class MailgunAuth 
{
    public JsonNode sendTestMessage() throws UnirestException, FileNotFoundException, IOException, JSONException {
        Properties config = new Properties();
        config.load(new FileInputStream("/home/orcid_tomcat/conf/orcid.properties"));
        HttpResponse<JsonNode> request = Unirest.post(config.getProperty("com.mailgun.notify.apiUrl"))
                .basicAuth("api", config.getProperty("com.mailgun.apiKey"))
                .queryString("from", "me@notify.orcid.org")
                .queryString("to", "you@ost.orcid.org")
                .queryString("subject", "check")
                .queryString("text", "mailgun checking...")
                .queryString("o:testmode", true)
                .asJson();
        return request.getBody();
    }
}

public class Check
{
    public static void main( String[] args )
    {
        try {
                JsonNode r = new MailgunAuth().sendTestMessage();
                JSONObject firstval = r.isArray()?r.getArray().getJSONObject(0):r.getObject();
                if(firstval.getString("message").indexOf("Great job") != -1) {
                        System.out.println("OK: Test email sent.");
                        System.exit(0);
                }
                System.out.println("WARN: No [message] found in JSON response. " + firstval.toString());
        } catch(Exception e){
            System.out.print("ERROR: " + e.getMessage());
        }
    }
}


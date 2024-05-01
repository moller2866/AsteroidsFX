package dk.sdu.mmmi.cbse.scoresystem;


import dk.sdu.mmmi.cbse.common.services.IScoreService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ScoreSystem implements IScoreService {


    @Override
    public Long addToTotalScore(Long point) {
        String url = "http://localhost:8080/addscore?point=" + point;
        return sendGetRequest(url);
    }

    @Override
    public Long getTotalScore() {
        String url = "http://localhost:8080/score";
        return sendGetRequest(url);
    }

    private Long sendGetRequest(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return Long.parseLong(response.toString());
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error sending GET request: " + e.getMessage());
        }
        return null;
    }
    public static void main(String[] args) {
        ScoreSystem scoreSystem = new ScoreSystem();
        System.out.println(scoreSystem.addToTotalScore(10L));
        System.out.println(scoreSystem.addToTotalScore(10L));
        System.out.println(scoreSystem.getTotalScore());
    }
}

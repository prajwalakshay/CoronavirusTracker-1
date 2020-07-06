package maa.coronavirustracker.Models;

public class LogData {

    private String News;
    private String Time;

    public LogData(String news, String time) {
        News = news;
        Time = time;
    }

    public String getNews() {
        return News;
    }

    public String getTime() {
        return Time;
    }
}

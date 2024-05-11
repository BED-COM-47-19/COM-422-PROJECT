

package com.example.teachandlearn.Student.Form4.Documents;

public class Form4VideoList {
    private String title;
    private String videoUrl;

    public Form4VideoList() {
        // Default constructor required for calls to DataSnapshot.getValue(Video.class)
    }

    public Form4VideoList(String title, String videoUrl) {
        this.title = title;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
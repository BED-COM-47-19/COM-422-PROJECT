

package com.example.teachandlearn.Student.Form2.Documents;

public class Form2VideoList {
    private String title;
    private String videoUrl;

    public Form2VideoList() {
        // Default constructor required for calls to DataSnapshot.getValue(Video.class)
    }

    public Form2VideoList(String title, String videoUrl) {
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

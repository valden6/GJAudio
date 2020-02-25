package com.gjinc.gjaudio;

import java.util.Locale;

public class AudioFile {

    private  String title;
    private  String filePath;
    private  String artist;
    private  String album;
    private  String genre;
    private int year;
    private int duration;

    public AudioFile(String title, String filePath, String artist, String album, String genre, int year, int duration) {
        this.title = title;
        this.filePath = filePath;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.year = year;
        this.duration = duration;
    }

    public AudioFile() {
        this.title = "Titre";
        this.filePath = "...";
        this.artist = "Artiste";
        this.album = "Album";
        this.genre = "Genre";
        this.year = 0000;
        this.duration = 0;
    }

    @Override
    public String toString() {
        return "AudioFile{" +
                "title='" + title + '\'' +
                ", filePath='" + filePath + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                '}';
    }

    public  String  getTitle()  {
        return  title;
    }

    public  void  setTitle(String  title)  {
        this.title  =  title;
    }

    public  String  getFilePath()  {
        return  filePath;
    }

    public  void  setFilePath(String  filePath)  {
        this.filePath  =  filePath;
    }

    public  String  getArtist()  {
        return  artist;
    }

    public  void  setArtist(String  artist)  {
        this.artist  =  artist;
    }

    public  String  getAlbum()  {
        return  album;
    }

    public  void  setAlbum(String  album)  {
        this.album  =  album;
    }

    public  String  getGenre()  {
        return  genre;
    }

    public  void  setGenre(String  genre)  {
        this.genre  =  genre;
    }

    public  int  getYear()  {
        return  year;
    }

    public  void  setYear(int  year)  {
        this.year  =  year;
    }

    public  int  getDuration()  {
        return  duration;
    }

    public  String  getDurationText()  {

        this.duration = this.duration/1000;

        int  second  =  duration  %  60;
        int  durationMinute  =  (duration  -  second)  /  60;
        int  minute  =  durationMinute  %  60;
        int  hour  =  (durationMinute  -  minute)  /  60;

        if(hour  >  0)
            return  String.format(Locale.getDefault(),"%02d:%02d:%02d",hour,minute,second);
        return  String.format(Locale.getDefault(),"%02d:%02d",minute,second);
    }

    public  void  setDuration(int  duration)  {
        this.duration  =  duration;
    }
}
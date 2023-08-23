package com.itheima.moves;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * 电影类
 */


public class Movie implements Serializable , Comparator {
    private String name;//电影片名
    private  String actor;//主演
    private  double  score;//评分
    private  double  time;//分钟
    private  double price;//票价
    private  int  number;//余票数
    private Date startTime;//放映时间

    public Movie() {
    }

    public Movie(String name, String actor, double score, double time, double price, int number, Date startTime) {
        this.name = name;
        this.actor = actor;
        this.score = score;
        this.time = time;
        this.price = price;
        this.number = number;
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", actor='" + actor + '\'' +
                ", score=" + score +
                ", time=" + time +
                ", price=" + price +
                ", number=" + number +
                ", startTime=" + startTime +
                '}';
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.score, score) == 0 && Double.compare(movie.time, time) == 0 && Double.compare(movie.price, price) == 0 && number == movie.number && Objects.equals(name, movie.name) && Objects.equals(actor, movie.actor) && Objects.equals(startTime, movie.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, actor, score, time, price, number, startTime);
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}


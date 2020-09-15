package com.hjc.base.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.Objects;

public class ConcertBean extends BaseObservable {
    private String title;
    private String author;

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() == obj.getClass()) {
            return true;
        }
        ConcertBean concert = (ConcertBean) obj;
        return Objects.equals(title, concert.title) && Objects.equals(author, concert.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
}

package fr.mds.geekquote.model;

import java.io.Serializable;
import java.util.Date;

public class Quote implements Serializable {
    private int id;
    private String strQuote;
    private int rating;
    private Date creationDate;

    public Quote() {

    }

    public Quote(String strQuote, int rating, Date creationDate, int id) {
        this.strQuote = strQuote;
        this.rating = rating;
        this.creationDate = creationDate;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrQuote() {
        return strQuote;
    }

    public void setStrQuote(String strQuote) {
        this.strQuote = strQuote;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "strQuote='" + strQuote + '\'' +
                ", rating=" + rating +
                ", creationDate=" + creationDate +
                '}';
    }

}

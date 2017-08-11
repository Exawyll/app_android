package fr.imie.geekquote.model;

import java.io.Serializable;
import java.nio.channels.SeekableByteChannel;
import java.util.Date;
import java.util.Random;

/**
 * Created by WYLLIAM on 10/08/2017.
 */

public class Quote implements Serializable {
    private String strQuote;
    private int rating;
    private Date creationDate;

    public Quote(String strQuote) {
        this.strQuote = strQuote;
        this.creationDate = new Date();
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

    @Override
    public String toString() {
        return strQuote;
    }
}

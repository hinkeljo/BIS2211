package teamB.BIS2211.TankApp.Model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LeaderboardData 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; 

    private String user; 

    private String gsID; 

    private float amount; 
    private String type; 
    private float pricePayed; 
    private float priceDeviation;

    private Timestamp timestamp; 

    private boolean checked = false; 

    public long getId()
    {
        return id; 
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPricePayed() {
        return pricePayed;
    }

    public void setPricePayed(float pricePayed) {
        this.pricePayed = pricePayed;
    }

    public float getPriceDeviation() {
        return priceDeviation;
    }

    public void setPriceDeviation(float priceDeviation) {
        this.priceDeviation = priceDeviation;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getGsID() {
        return gsID;
    }

    public void setGsID(String gsID) {
        this.gsID = gsID;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
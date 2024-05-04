package Entity;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class Payment1day32 {
    private BigInteger id;
    private int userid;
    private Time beginTime1day;
    private Time endTime1day;
    private float paymentGood = 0;
    private float paymentRent = 0;
    private Date createTime;

    public Payment1day32() {
    }

    public Payment1day32(BigInteger id, int userid, Time beginTime1day, Time endTime1day, float paymentRent, Date createTime) {
        this.id = id;
        this.userid = userid;
        this.beginTime1day = beginTime1day;
        this.endTime1day = endTime1day;
        this.paymentRent = paymentRent;
        this.createTime = createTime;
        if (beginTime1day == null) {
            this.beginTime1day = new Time(0);
        }
        if (endTime1day == null) {
            this.endTime1day = new Time(0);
        }
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Time getBeginTime1day() {
        return beginTime1day;
    }

    public void setBeginTime1day(Time beginTime1day) {
        this.beginTime1day = beginTime1day;
    }

    public Time getEndTime1day() {
        return endTime1day;
    }

    public void setEndTime1day(Time endTime1day) {
        this.endTime1day = endTime1day;
    }

    public float getPaymentGood() {
        return paymentGood;
    }

    public void setPaymentGood(float paymentGood) {
        this.paymentGood = paymentGood;
    }

    public float getPaymentRent() {
        return paymentRent;
    }

    public void setPaymentRent(float paymentRent) {
        this.paymentRent = paymentRent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCheckIn() {
        return beginTime1day.toString();
    }

    public String getCheckOut() {
        return endTime1day.toString();
    }

    public float getRentPayment() {
        return paymentRent;
    }

    public float getGoodPayment() {
        return paymentGood;
    }

    public void setGoodPayment(float goodPayment) {
        this.paymentGood += goodPayment;
    }
}

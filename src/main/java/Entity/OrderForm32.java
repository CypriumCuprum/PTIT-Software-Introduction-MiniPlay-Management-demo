package Entity;

import java.util.Date;

public class OrderForm32 {
    private int orderId;
    private Date beginTime;
    private Date endTime;
    private float rentPriceOne;
    private float fullPredictPrice;
    private float deposit;
    private Date depositDate;
    private int clientid;
    private int userid;
    private Date createTime;

    public OrderForm32() {
    }

    public OrderForm32(int orderId, Date beginTime, Date endTime, float rentPriceOne, float fullPredictPrice, float deposit, Date depositDate, int clientid, int userid, Date createTime) {
        this.orderId = orderId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.rentPriceOne = rentPriceOne;
        this.fullPredictPrice = fullPredictPrice;
        this.deposit = deposit;
        this.depositDate = depositDate;
        this.clientid = clientid;
        this.userid = userid;
        this.createTime = createTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public float getRentPriceOne() {
        return rentPriceOne;
    }

    public void setRentPriceOne(float rentPriceOne) {
        this.rentPriceOne = rentPriceOne;
    }

    public float getFullPredictPrice() {
        return fullPredictPrice;
    }

    public void setFullPredictPrice(float fullPredictPrice) {
        this.fullPredictPrice = fullPredictPrice;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeposit_date() {
        return depositDate;
    }

    public void setDeposit_date(Date depositDate) {
        this.depositDate = depositDate;
    }

    public int getClient_ID() {
        return clientid;
    }

    public void setClient_ID(int clientid) {
        this.clientid = clientid;
    }

    public int getUser_ID() {
        return userid;
    }

    public void setUser_ID(int userid) {
        this.userid = userid;
    }

    public Date getCreate_time() {
        return createTime;
    }
}

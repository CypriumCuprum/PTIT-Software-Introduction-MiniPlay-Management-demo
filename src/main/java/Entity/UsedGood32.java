package Entity;

public class UsedGood32 {
    private int id;
    private Good32 good32;
    private int num;
    private float unitprice;

    public UsedGood32(Good32 good32, float v, int i) {
        this.good32 = good32;
        this.unitprice = v;
        this.num = i;
    }

    public UsedGood32(int id, Good32 good32, int num, float unitprice) {
        this.id = id;
        this.good32 = good32;
        this.num = num;
        this.unitprice = unitprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Good32 getGood32() {
        return good32;
    }

    public void setGood32(Good32 good32) {
        this.good32 = good32;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }


}

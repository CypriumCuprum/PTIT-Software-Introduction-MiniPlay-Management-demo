package util;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateConvertTimeStamp {
    public static BigInteger DateToday2timeStamp(int id) {
        Date fulltoday = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(fulltoday);
        Date todate = Date.valueOf(date);
        Timestamp timestamp = new Timestamp(todate.getTime());
        String idconv = String.format("%d%d", timestamp.getTime() / 1000, id);
        BigInteger idpayment1day = new BigInteger(idconv);
        System.out.println(idpayment1day);
        return idpayment1day;
    }
}

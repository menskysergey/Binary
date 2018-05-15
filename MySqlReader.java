import json.Currencies;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

//расчет медианы за период
public class MySqlReader {


    private float vol;

    public float getVol() {
        return vol;
    }

    public MySqlReader(Currencies currencies, int last_days, Connection connection) throws Exception {
        String table_name=currencies.toString().toLowerCase()+"_stat";
        String sql="SELECT * FROM "+table_name+" ORDER BY id DESC LIMIT "+last_days;
        //разрядность валютной пары
        float k;
        if (currencies==Currencies.EURJPY || currencies==Currencies.GBPJPY || currencies==Currencies.USDJPY || currencies==Currencies.AUDJPY)
            k=100;
        else k=10000;

    Statement statement= connection.createStatement();

        ResultSet rs=statement.executeQuery(sql);
//создаем массив для сбора волы
        float[]medianArray=new float[last_days];
        int i=0;
        while(rs.next()) {
            float high=rs.getFloat("high");
            float low=rs.getFloat("low");
            medianArray[i]=high-low;
           i=i+1;
             }

       Arrays.sort(medianArray);

       if (medianArray.length % 2 == 0)
        {
         this.vol=(float) ((int)((medianArray[medianArray.length / 2] + medianArray[medianArray.length / 2 - 1])/2*k))/k;
        }

        else this.vol=(float) (int)(medianArray[medianArray.length / 2]*k)/k;

        }

}

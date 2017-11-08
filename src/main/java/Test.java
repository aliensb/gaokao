import java.text.DecimalFormat;

/**
 * Created by paul on 2017/6/21.
 */
public class Test {
    public static void main(String[] args) {
        double d=5.3;
        DecimalFormat    df   = new DecimalFormat("######0");
        System.out.println(Double.parseDouble(df.format(d)));
    }
}

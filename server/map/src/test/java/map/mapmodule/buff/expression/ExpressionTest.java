package map.mapmodule.buff.expression;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ExpressionTest {
    public void test() {
        float small = 0.0000000000005748F;
        float big = 999999999999F;
        System.out.println(small);
        System.out.println(String.format("%.15f", small));
        Evaluator evaluator = new Evaluator();
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(20);
        //df.setMaximumIntegerDigits(20);
        System.out.println(df.format(small));
        System.out.println(df.format(big));
        System.out.println(new BigDecimal(small).toPlainString());
        System.out.println(new BigDecimal(big).toPlainString());
        try {
            String str = evaluator.evaluate(new BigDecimal(small).toPlainString() + " * 2");
            System.out.println(str);
            System.out.println(String.format("%.15f", Float.parseFloat(str)));
        } catch (EvaluationException e) {
            e.printStackTrace();
        }
    }
}
package gs.util;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class IPUtil {
    private IPUtil() {

    }

    public static boolean isIP(String ip) {
        return isIPv4(ip) || isIPv6(ip);
    }

    public static boolean isIPv4(String ip) {
        final String regex = "^(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$";
        return isMatch(ip, regex);
    }

    public static boolean isIPv6(String ip) {
        final String regex = "(^((([0-9A-Fa-f]{1,4}:){7}(([0-9A-Fa-f]{1,4}){1}|:))"
                + "|(([0-9A-Fa-f]{1,4}:){6}((:[0-9A-Fa-f]{1,4}){1}|"
                + "((22[0-3]|2[0-1][0-9]|[0-1][0-9][0-9]|"
                + "([0-9]){1,2})([.](25[0-5]|2[0-4][0-9]|"
                + "[0-1][0-9][0-9]|([0-9]){1,2})){3})|:))|"
                + "(([0-9A-Fa-f]{1,4}:){5}((:[0-9A-Fa-f]{1,4}){1,2}|"
                + ":((22[0-3]|2[0-1][0-9]|[0-1][0-9][0-9]|"
                + "([0-9]){1,2})([.](25[0-5]|2[0-4][0-9]|"
                + "[0-1][0-9][0-9]|([0-9]){1,2})){3})|:))|"
                + "(([0-9A-Fa-f]{1,4}:){4}((:[0-9A-Fa-f]{1,4}){1,3}"
                + "|:((22[0-3]|2[0-1][0-9]|[0-1][0-9][0-9]|"
                + "([0-9]){1,2})([.](25[0-5]|2[0-4][0-9]|[0-1][0-9][0-9]|"
                + "([0-9]){1,2})){3})|:))|(([0-9A-Fa-f]{1,4}:){3}((:[0-9A-Fa-f]{1,4}){1,4}|"
                + ":((22[0-3]|2[0-1][0-9]|[0-1][0-9][0-9]|"
                + "([0-9]){1,2})([.](25[0-5]|2[0-4][0-9]|"
                + "[0-1][0-9][0-9]|([0-9]){1,2})){3})|:))|"
                + "(([0-9A-Fa-f]{1,4}:){2}((:[0-9A-Fa-f]{1,4}){1,5}|"
                + ":((22[0-3]|2[0-1][0-9]|[0-1][0-9][0-9]|"
                + "([0-9]){1,2})([.](25[0-5]|2[0-4][0-9]|"
                + "[0-1][0-9][0-9]|([0-9]){1,2})){3})|:))"
                + "|(([0-9A-Fa-f]{1,4}:){1}((:[0-9A-Fa-f]{1,4}){1,6}"
                + "|:((22[0-3]|2[0-1][0-9]|[0-1][0-9][0-9]|"
                + "([0-9]){1,2})([.](25[0-5]|2[0-4][0-9]|"
                + "[0-1][0-9][0-9]|([0-9]){1,2})){3})|:))|"
                + "(:((:[0-9A-Fa-f]{1,4}){1,7}|(:[fF]{4}){0,1}:((22[0-3]|2[0-1][0-9]|"
                + "[0-1][0-9][0-9]|([0-9]){1,2})"
                + "([.](25[0-5]|2[0-4][0-9]|[0-1][0-9][0-9]|([0-9]){1,2})){3})|:)))$)";
        return isMatch(ip, regex);
    }

    private static boolean isMatch(String ip, String regex) {
        if (ip == null || ip.trim().length() == 0) {
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Normalizer.normalize(ip, Normalizer.Form.NFKC));
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(isIPv6("2001:0DB8:0000:0023:0008:0800:200C:417A"));
        System.out.println(isIPv6("2001:DB8:0:23:8:800:200C:417A"));
        System.out.println(isIPv6("2001:DB8:0:23:8:800:192.1.0.0"));
        System.out.println(isIPv6("2001:DB8:0::800:192.1.0.0"));
        System.out.println(isIPv6("2001:DB8:0:23::192.1.0.0"));
        System.out.println(isIPv6("::192.1.0.0"));
        System.out.println(isIPv6("1:af::3"));
        System.out.println(isIPv6("1:af::"));
        System.out.println(isIPv6("::1:af:0"));
        System.out.println(isIPv6("::0"));
        System.out.println("---------------------");
        System.out.println(isIPv6("+2001:0DB8:0000:0023:0008:0800:200C:417A"));
        System.out.println(isIPv6("2001:0DB8:0z00:0023:0008:0800:200C:417A"));
        System.out.println(isIPv6("2001:DB8:0:23:800::192.1.0.0.1"));
        System.out.println(isIPv6("2001:DB8::23::800:192.1.0.0"));
        System.out.println(isIPv6(":::"));
        System.out.println(isIPv6("1:::2"));

        System.out.println("---------------------");
        System.out.println(isIPv4("1.1.0.1"));
        System.out.println(isIPv4("123.1.0.19"));
        System.out.println(isIPv4("255.255.255.255"));
        System.out.println(isIPv4("0.0.0.0"));
        System.out.println("---------------------");
        System.out.println(isIPv4("-1.1.0.1"));
        System.out.println(isIPv4("1.1b.0.1"));
        System.out.println(isIPv4("1.01.0.1"));
        System.out.println(isIPv4("1.1.300.1"));
        System.out.println(isIPv4("1.1..1"));
    }
}

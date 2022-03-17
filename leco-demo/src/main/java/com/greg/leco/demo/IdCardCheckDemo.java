package com.greg.leco.demo;

/**
 * @author greg
 * @version 2022/1/13
 */
public class IdCardCheckDemo {
    public static void main(String[] args) {
//        String reg = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
//        Pattern pattern = Pattern.compile(reg);
//        String idCard = "41150219990909212X";
//        System.out.println(pattern.matcher(idCard).matches());
        String str = "1642749441000";
        System.out.println(str.equals(null));
        System.out.println(str.substring(str.length() - 5, str.length() - 3));

        String idStr = "8615377166";
        String prefix = idStr.substring(0, 2);
        int generatedId = Integer.parseInt(idStr.substring(2));
        System.out.println(prefix);
        System.out.println(generatedId);
    }
}

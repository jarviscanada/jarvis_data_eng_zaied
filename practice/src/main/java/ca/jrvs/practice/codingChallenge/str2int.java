package ca.jrvs.practice.codingChallenge;

public class str2int {

    /***
     * time complexity: 0(n). builtin java parseInt is also 0(n); n string length; from stringbuilder to string 0(n)
     * space complexity: 0(1); constant number of Stringnbuilders and string
     * @param string
     * @return
     */
    public static final int str2intWithAPI(String string) {
        int n = string.length();
        StringBuilder afterWhitespace = new StringBuilder();
        boolean changed = false;
        for (int i = 0; i < n; i++) {
            if (!changed && string.charAt(i) == ' ') {
                continue;
            } else {
                afterWhitespace.append(string.charAt(i));
                changed = true;
            }
        }
        StringBuilder removedAfter = new StringBuilder();
        int sign = 0;
        changed = false;
        for (int i = 0; i < afterWhitespace.length(); i++) {
            if (!changed && (afterWhitespace.charAt(i) == '+' || afterWhitespace.charAt(i) == '-')) {
                if (afterWhitespace.charAt(i) == '-') sign = -1;
                removedAfter.append(afterWhitespace.charAt(i));
                changed = true;
            } else if (afterWhitespace.charAt(i) >= '0' && afterWhitespace.charAt(i) <= '9') {
                removedAfter.append(afterWhitespace.charAt(i));
                changed = true;
            } else if (!changed || (afterWhitespace.charAt(i) < '0' || afterWhitespace.charAt(i) > '9')) {
                break;
            }
        }
        String resStr = removedAfter.toString();
        if (resStr.length() <= 0) return 0;
        if (resStr.length() == 1 && (resStr.charAt(0) == '+' || resStr.charAt(0) == '-')) return 0;
        int res = 0;
        try {
            res = Integer.parseInt(resStr);
        } catch (NumberFormatException ex) {
            if (sign == -1) res = Integer.MIN_VALUE;
            else res = Integer.MAX_VALUE;
        } finally {
            return res;
        }

    }

    /***
     * time complexity: 0(n)
     * space complexity: 0(1)
     * @param string
     * @return
     */
    public static final int str2intRunner(String string) {
        int n = string.length();
        boolean ns = false;
        Long temp = new Long(0);
        int res = 0;
        int sign = 0;
        for (int i = 0; i < n; i++) {
            if (ns && (string.charAt(i) < '0' || string.charAt(i) > '9')) {
                break;
            } else if (!ns && (string.charAt(i) == '-' || string.charAt(i) == '+')) {
                if (string.charAt(i) == '-') {
                    sign = -1;
                }
                ns = true;
            } else if (string.charAt(i) >= '0' && string.charAt(i) <= '9') {
                temp = temp * 10 + string.charAt(i) - '0';
                ns = true;
                if (sign == -1) {
                    if ((-1) * temp < Integer.MIN_VALUE) return Integer.MIN_VALUE;
                } else {
                    if (temp > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                }
                res = temp.intValue();
            } else if (!ns && string.charAt(i) != ' ') {
                break;
            }
        }
        if (sign == -1) return (-1) * res;
        return res;
    }
}

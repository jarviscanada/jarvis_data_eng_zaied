package ca.jrvs.practice.codingChallenge;

import java.util.Deque;
import java.util.LinkedList;

public class validParentheses<T> {

    public static final boolean isValidStack(String s) {
        int n = s.length();
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else if (s.charAt(i) == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            } else if (s.charAt(i) == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        return (stack.isEmpty() ? true : false);
    }
}

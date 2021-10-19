package cs.lab1;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    public String change(String first) {
        Stack stack = new Stack();
        int i = 0;
        String y = "";
        char top = '1';
        while (i < first.length()) {
            if (!stack.isEmpty()) top = (String.valueOf(stack.peek())).charAt(0);
            char x = first.charAt(i);
            if (x > '0' && x <= '9') {
                int j = i;
                while (j < first.length() && first.charAt(j) >= '0' && first.charAt(j) <= '9') {
                    y += first.charAt(j);
                    j++;
                }
                y += ' ';
                i = j - 1;
            } else if (x == '(') stack.push(x);
            else if (x == '+' | x == '-') {
                if (top == '+' || top == '-' || top == '*' || top == '/') {
                    y += top;
                    stack.pop();
                    stack.push(x);
                    top = x;
                } else if (top == '(' || stack.isEmpty()) stack.push(x);
            } else if (x == '*' || x == '/') {
                if (top == '+' || top == '-' || top == '(') stack.push(x);
                else if (top == '*' || top == '/') {
                    y += top;
                    stack.pop();
                    stack.push(x);
                    top = x;
                } else if (stack.isEmpty()) stack.push(x);
            } else if (x == ')') {
                while (top != '(') {
                    y += top;
                    stack.pop();
                    top = (String.valueOf(stack.peek())).charAt(0);
                }
                stack.pop();
            }
            i++;
        }
        while (!stack.isEmpty())
            y += String.valueOf(stack.pop());
        return y;
    }

    public int calculate(String postfix) {
        Stack stack = new Stack();
        int i = 0;
        while (i < postfix.length()) {
            char x = postfix.charAt(i);
            if (x > '0' && x <= '9') {
                int j = i;
                String number = "";
                while (j < postfix.length() && postfix.charAt(j) >= '0' && postfix.charAt(j) <= '9') {
                    number += postfix.charAt(j);
                    j++;
                }
                stack.push(number);
                i = j - 1;
            } else if (x == '+') {
                int a = Integer.parseInt(String.valueOf(stack.pop()));
                int b = Integer.parseInt(String.valueOf(stack.pop()));
                int y = b + a;
                stack.push(y);
            } else if (x == '-') {
                int a = Integer.parseInt(String.valueOf(stack.pop()));
                int b = Integer.parseInt(String.valueOf(stack.pop()));
                int y = b - a;
                stack.push(y);
            } else if (x == '*') {
                int a = Integer.parseInt(String.valueOf(stack.pop()));
                int b = Integer.parseInt(String.valueOf(stack.pop()));
                int y = b * a;
                stack.push(y);
            } else if (x == '/') {
                int a = Integer.parseInt(String.valueOf(stack.pop()));
                int b = Integer.parseInt(String.valueOf(stack.pop()));
                int y = b / a;
                stack.push(y);
            }
            i++;
        }
        return Integer.parseInt(String.valueOf(stack.pop()));
    }

    public static void main(String[] args) {
        // String txt = new String("30*2-(16+3)");
        System.out.println("请输入要计算的表达式(由 + - * / ( ) 和数字组成) 如:(35+25)*3+10");
        Scanner sc = new Scanner(System.in);
        String txt = sc.nextLine();
        Calculator c = new Calculator();
        int x = c.calculate(c.change(txt));
        System.out.print("转换的后缀表达式为：");
        System.out.println(c.change(txt));
        System.out.print("运算结果为：");
        System.out.println(x);
    }
}


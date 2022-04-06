package springstudy.spring;

import springstudy.spring.exception.CustomException;

import java.util.Scanner;

public class Main {
    public static void test() throws CustomException {
        int age = -10;
        if(age < 0) {
            throw new CustomException("나이는 음수일 수 없습니다.");
        }
        else System.out.printf("당신의 나이는 %d살입니다.", age);
    }
}

package springstudy.spring;

import org.springframework.beans.factory.SmartInitializingSingleton;
import springstudy.spring.exception.CustomException;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void test() throws CustomException {
//        int age = -10;
//        if(age < 0) {
//            throw new CustomException("나이는 음수일 수 없습니다.");
//        }
//        else System.out.printf("당신의 나이는 %d살입니다.", age);
    }

    //JDBC 연결 확인
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306",
                    "root",
                    "jsppass"
            );

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "show databases"
            );

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

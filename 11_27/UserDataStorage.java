package BookSearchProgram;

import java.sql.*;

public class UserDataStorage {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public UserDataStorage() {
        try {
            // JDBC Driver (MySQL 8.x)
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/userinformation?serverTimezone=UTC",
                    "root",
                    "j09180918j@!"
            );

            System.out.println("사용자 정보 DB 연결 성공!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 회원가입
    public boolean register(String id, String pw) {
        try {
            String sql = "INSERT INTO user (id, pw) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("회원가입 오류: " + ex.getMessage());
            return false;
        }
    }

    // 로그인
    public boolean login(String id, String pw) {
        try {
            String sql = "SELECT * FROM user WHERE id = ? AND pw = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            rs = pstmt.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

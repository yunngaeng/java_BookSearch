package Book;

import java.sql.*;

public class UserDataStorage {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public UserDataStorage() {
        try {
            // JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/userinformation?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "9427"
            );

            System.out.println("사용자 정보 DB 연결 성공!");
            System.out.println("Connected DB: " + conn.getCatalog()); // 확인용

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------
    // 1) 회원가입 (ID + PW + PHONE)
    // -----------------------------------------
    public boolean register(String id, String pw, String phone) {
        try {
            String sql = "INSERT INTO user (id, pw, phone) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, phone);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("회원가입 오류: " + ex.getMessage());
            return false;
        }
    }

    // -----------------------------------------
    // 2) 로그인 (ID + PW)
    // -----------------------------------------
    public boolean login(String id, String pw) {
        try {
            String sql = "SELECT * FROM user WHERE id = ? AND pw = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            rs = pstmt.executeQuery();
            return rs.next(); // 레코드 존재하면 true

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // -----------------------------------------
    // 3) 전화번호로 ID + PW 찾기
    // -----------------------------------------
    public String[] findByPhone(String phone) {
        try {
            String sql = "SELECT id, pw FROM user WHERE phone = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new String[]{
                        rs.getString("id"),
                        rs.getString("pw")
                };
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // -----------------------------------------
    // 4) 회원 삭제 (옵션, 원하면 사용)
    // -----------------------------------------
    public boolean deleteUser(String id, String pw) {
        try {
            String sql = "DELETE FROM user WHERE id = ? AND pw = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
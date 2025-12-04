package Book;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private UserDataStorage userData;

    public LoginFrame(UserDataStorage data) {
        this.userData = data;

        setTitle("로그인");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField idField = new JTextField();
        JPasswordField pwField = new JPasswordField();

        JButton loginBtn = new JButton("로그인");
        JButton registerBtn = new JButton("회원가입");
        JButton findIDBtn = new JButton("ID/PW 찾기");



        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("PW:"));
        add(pwField);

        add(loginBtn);
        add(registerBtn);
        add(findIDBtn);

        // 로그인 버튼
        loginBtn.addActionListener(e -> {
            String id = idField.getText();
            String pw = new String(pwField.getPassword());

            if (userData.login(id, pw)) {
                JOptionPane.showMessageDialog(this, "로그인 성공!");

                try {
                    new BookSearchFrame();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "로그인 실패! ID 또는 PW가 일치하지 않습니다.");
            }
        });

        // 회원가입 버튼
        registerBtn.addActionListener(e -> {
            new UserRegistrationFrame(userData);
        });

        // ID 찾기 버튼
        findIDBtn.addActionListener(e -> {
            new FindIDFrame(userData);
        });

        setVisible(true);
    }
}

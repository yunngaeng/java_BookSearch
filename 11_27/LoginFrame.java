package BookSearchProgram;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private UserDataStorage userData;

    public LoginFrame(UserDataStorage data) {
        this.userData = data;

        setTitle("로그인");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ★ 중요
        setLocationRelativeTo(null);

        JTextField idField = new JTextField();
        JPasswordField pwField = new JPasswordField();

        JButton loginBtn = new JButton("로그인");
        JButton registerBtn = new JButton("회원가입");

        setLayout(new GridLayout(3, 2));
        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("PW:"));
        add(pwField);
        add(loginBtn);
        add(registerBtn);

        // 로그인 버튼
        loginBtn.addActionListener(e -> {
            String id = idField.getText();
            String pw = pwField.getText();

            if (userData.login(id, pw)) {
                JOptionPane.showMessageDialog(this, "로그인 성공!");

                try {
                    new BookSearchFrame();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "로그인 실패!");
            }
        });

        // 회원가입 버튼
        registerBtn.addActionListener(e -> {
            new UserRegistrationFrame(userData);
        });

        setVisible(true);
    }
}


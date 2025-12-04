package Book;

import javax.swing.*;
import java.awt.*;

public class UserRegistrationFrame extends JFrame {

    private UserDataStorage userData;

    public UserRegistrationFrame(UserDataStorage data) {
        this.userData = data;

        setTitle("회원가입");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField idField = new JTextField();
        JPasswordField pwField = new JPasswordField();
        JTextField phoneField = new JTextField();

        JButton registerBtn = new JButton("가입하기");

        setLayout(new GridLayout(4, 2));

        add(new JLabel("새 ID:"));
        add(idField);
        add(new JLabel("새 PW:"));
        add(pwField);
        add(new JLabel("전화번호:"));
        add(phoneField);
        add(new JLabel());
        add(registerBtn);

        registerBtn.addActionListener(e -> {
            String id = idField.getText();
            String pw = new String(pwField.getPassword());
            String phone = phoneField.getText();

            if (id.isEmpty() || pw.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "모든 정보를 입력하세요.");
                return;
            }

            boolean result = userData.register(id, pw, phone);

            if (result) {
                JOptionPane.showMessageDialog(this, "회원가입 성공!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "회원가입 실패! 이미 존재하는 ID일 수 있습니다.");
            }
        });

        setVisible(true);
    }
}

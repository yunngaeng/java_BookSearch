package Book;

import javax.swing.*;
import java.awt.*;

public class UserRegistrationFrame extends JFrame {


    private UserDataStorage userData;

    public UserRegistrationFrame(UserDataStorage data) {
        this.userData = data;

        setTitle("회원가입");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField idField = new JTextField();
        JPasswordField pwField = new JPasswordField();
        JButton registerBtn = new JButton("가입하기");

        setLayout(new GridLayout(3, 2));
        add(new JLabel("새 ID:"));
        add(idField);
        add(new JLabel("새 PW:"));
        add(pwField);
        add(new JLabel());
        add(registerBtn);

        registerBtn.addActionListener(e -> {
            String id = idField.getText();
            String pw = new String(pwField.getPassword());


            if (id.isEmpty() || pw.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID와 PW 모두 입력하세요.");
                return;
            }

            boolean result = userData.register(id, pw);

            if (result) {
                JOptionPane.showMessageDialog(this, "회원가입 성공!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "회원가입 실패!");
            }
        });

        setVisible(true);
    }
}


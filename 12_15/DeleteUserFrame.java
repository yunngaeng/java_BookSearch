package Book;

import javax.swing.*;
import java.awt.*;

public class DeleteUserFrame extends JFrame {

    private UserDataStorage userData;

    public DeleteUserFrame(UserDataStorage data) {
        this.userData = data;

        setTitle("회원 탈퇴");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField idField = new JTextField();
        JPasswordField pwField = new JPasswordField();
        JButton deleteBtn = new JButton("회원 탈퇴");

        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("PW:"));
        add(pwField);
        add(new JLabel());
        add(deleteBtn);

        deleteBtn.addActionListener(e -> {
            String id = idField.getText();
            String pw = new String(pwField.getPassword());

            if (id.isEmpty() || pw.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID와 PW를 모두 입력하세요.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "정말 회원 탈퇴하시겠습니까?",
                    "확인",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                boolean result = userData.deleteUser(id, pw);

                if (result) {
                    JOptionPane.showMessageDialog(this, "회원 탈퇴가 완료되었습니다.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "ID 또는 PW가 올바르지 않습니다.");
                }
            }
        });

        setVisible(true);
    }
}
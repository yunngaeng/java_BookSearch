package Book;

import javax.swing.*;
import java.awt.*;

public class FindIDFrame extends JFrame {

    private UserDataStorage userData;

    public FindIDFrame(UserDataStorage data) {
        this.userData = data;

        setTitle("ID/PW 찾기");
        setSize(320, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField phoneField = new JTextField();
        JButton findBtn = new JButton("찾기");

        setLayout(new GridLayout(2, 2));

        add(new JLabel("전화번호 입력:"));
        add(phoneField);
        add(new JLabel());
        add(findBtn);

        findBtn.addActionListener(e -> {
            String phone = phoneField.getText();

            if (phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "전화번호를 입력하세요.");
                return;
            }

            String[] result = userData.findByPhone(phone);

            if (result != null) {
                JOptionPane.showMessageDialog(this,
                        "조회 결과\n\nID: " + result[0] + "\nPW: " + result[1]
                );
            } else {
                JOptionPane.showMessageDialog(this, "해당 번호로 등록된 계정이 없습니다.");
            }
        });

        setVisible(true);
    }
}

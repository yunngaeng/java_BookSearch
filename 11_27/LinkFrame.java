package BookSearchProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class LinkFrame extends JFrame {

    private String url;

    public LinkFrame(String url) {
        this.url = url;

        setTitle("링크 열기");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 이 창만 닫힘
        setLocationRelativeTo(null);

        // 컴포넌트
        JLabel urlLabel = new JLabel("<html><body>" + url + "</body></html>");
        JButton openBtn = new JButton("브라우저에서 열기");
        JButton closeBtn = new JButton("닫기");

        urlLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

        // 배치
        setLayout(new BorderLayout());
        add(urlLabel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.add(openBtn);
        btnPanel.add(closeBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // -----------------------------------------------------
        // 브라우저에서 열기 버튼
        // -----------------------------------------------------
        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "링크를 열 수 없습니다.");
                }
            }
        });

        // -----------------------------------------------------
        // 닫기 버튼
        // -----------------------------------------------------
        closeBtn.addActionListener(e -> dispose());

        setVisible(true);
    }
}

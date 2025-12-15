package Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.Map;

public class BookSearchFrame extends JFrame {

    private TextbookDataStorage storage = new TextbookDataStorage();

    public BookSearchFrame() {

        setTitle("교재 검색");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField searchField = new JTextField();
        JButton searchBtn = new JButton("검색");
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        setLayout(new BorderLayout());

        // 검색창 + 버튼 배치
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchBtn, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // 결과가 잘 보이도록 CENTER에 배치!!!
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // 검색 버튼 이벤트
        searchBtn.addActionListener(e -> {
        	System.out.println("검색 버튼 클릭됨");
            String keyword = searchField.getText().trim();

            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "검색어를 입력하세요!");
                return;
            }

            Map<String, String> links = storage.generateSearchLinks(keyword);

            resultArea.setText("검색 결과:\n\n");
            links.forEach((site, url) -> {
                resultArea.append(site + " ▶ " + url + "\n");
            });

            resultArea.append("\n(드래그 후 클릭하면 링크 열림)\n");
        });

        // 링크 클릭
        resultArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int pos = resultArea.viewToModel2D(e.getPoint());  // 클릭한 위치의 문자 index 얻기
                    int rowStart = resultArea.getLineStartOffset(resultArea.getLineOfOffset(pos));
                    int rowEnd = resultArea.getLineEndOffset(resultArea.getLineOfOffset(pos));

                    String lineText = resultArea.getText().substring(rowStart, rowEnd).trim();

                    // 해당 줄에 http로 시작하는 URL이 있는지 확인
                    if (lineText.contains("http")) {
                        int idx = lineText.indexOf("http");
                        String url = lineText.substring(idx).trim();

                        Desktop.getDesktop().browse(new URI(url));
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "링크를 열 수 없습니다.");
                }
            }
        });


        setVisible(true);
    }
}


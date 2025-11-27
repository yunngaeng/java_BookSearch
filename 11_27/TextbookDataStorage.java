package BookSearchProgram;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class TextbookDataStorage {

    public Map<String, String> generateSearchLinks(String keyword) {

        String encoded = URLEncoder.encode(keyword, StandardCharsets.UTF_8);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("교보문고", "https://www.kyobobook.co.kr/search/SearchCommonMain.jsp?vPstrKeyWord=" + encoded);
        map.put("YES24", "https://www.yes24.com/Product/Search?query=" + encoded);
        map.put("알라딘", "https://www.aladin.co.kr/search/wsearchresult.aspx?SearchWord=" + encoded);

        return map;
    }
}

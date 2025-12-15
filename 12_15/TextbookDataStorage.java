package Book;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class TextbookDataStorage {

    public Map<String, String> generateSearchLinks(String keyword) {

        // 한글 검색어를 URL에 넣을 수 있게 변환 (인코딩)
        String encoded = URLEncoder.encode(keyword, StandardCharsets.UTF_8);

        Map<String, String> map = new LinkedHashMap<>();

        // 1. 교보문고
        map.put("교보문고", "https://search.kyobobook.co.kr/search?keyword=" + encoded);

        // 2. YES24
        map.put("YES24", "https://www.yes24.com/Product/Search?query=" + encoded);

        // 3. 알라딘
        map.put("알라딘", "https://www.aladin.co.kr/search/wsearchresult.aspx?SearchWord=" + encoded);

        return map;
    }
}
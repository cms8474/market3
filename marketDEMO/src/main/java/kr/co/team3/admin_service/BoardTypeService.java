package kr.co.team3.admin_service;

import kr.co.team3.admin_entity.BoardType;
import kr.co.team3.admin_repository.BoardTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardTypeService {

    private final BoardTypeRepository repo;
    /** 코드→이름 맵 (키 정규화: trim + lowerCase) */
    public Map<String, String> getCodeNameMap(String prefix) {
        return repo.findByTypeStartingWithOrderByTypeAsc(prefix)
                .stream()
                .collect(Collectors.toMap(
                        bt -> bt.getType().trim().toLowerCase(),
                        BoardType::getName
                ));
    }

    /** 1차 목록: 마지막 자리가 0인 코드 (faq10, faq20, …) */
    public List<BoardType> getLv1(String prefix) {
        return repo.findByTypeStartingWithOrderByTypeAsc(prefix).stream()
                .filter(bt -> {
                    String t = bt.getType().trim().toLowerCase();
                    return t.startsWith(prefix) && t.length() >= 2 && t.charAt(t.length() - 1) == '0';
                })
                .toList();
    }

    /** 1차→2차 트리: 예) faq12→부모 faq10 로 그룹핑 */
    public Map<String, List<BoardType>> getLv2Tree(String prefix) {
        List<BoardType> all = repo.findByTypeStartingWithOrderByTypeAsc(prefix);
        Map<String, List<BoardType>> map = new HashMap<>();
        for (BoardType bt : all) {
            String code = bt.getType().trim().toLowerCase();
            if (!code.startsWith(prefix)) continue;
            // 2차: 끝자리가 0이 아닌 숫자인 코드들만
            char last = code.charAt(code.length() - 1);
            if (Character.isDigit(last) && last != '0') {
                String parent = code.substring(0, code.length() - 1) + "0"; // faq12 -> faq10
                map.computeIfAbsent(parent, k -> new ArrayList<>()).add(bt);
            }
        }
        // 정렬(Optional)
        map.values().forEach(list -> list.sort(Comparator.comparing(BoardType::getType)));
        return map;
    }
}
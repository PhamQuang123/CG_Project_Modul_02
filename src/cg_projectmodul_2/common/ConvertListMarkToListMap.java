package cg_projectmodul_2.common;

import cg_projectmodul_2.java.entity.Mark;

import java.util.*;

public class ConvertListMarkToListMap {
    public static Map<String, List<Mark>> listMap;
    public static Set<String> seKey;

    public ConvertListMarkToListMap() {
        listMap = new HashMap<>();
        seKey = listMap.keySet();
    }

    public static void listMarkToListMap(List<Mark> listConvert) {
        List<Mark> listValue = new ArrayList<>();

        String keyName = "";
        for (Mark mark : listConvert) {
            if (seKey.size() == 0) {
                listValue.add(mark);
                keyName += mark.getStudent().getStudentId();
            } else {
                if (check(mark) && isExist(mark, listValue)) {
                    listValue.add(mark);
                }
            }
        }
        listMap.put(keyName, listValue);
    }

    public static boolean check(Mark mark) {
        for (String str : seKey
        ) {
            if (mark.getStudent().getStudentId().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExist(Mark mark, List<Mark> list) {
        for (Mark m : list
        ) {
            if (mark.getSubject().getSubjectId().equals(m.getSubject().getSubjectId())) {
                return false;
            }
        }
        return true;
    }
}

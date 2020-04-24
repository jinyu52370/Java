package com.jinyu;

import java.util.*;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/24 12:11
 */
public class GreedyAlgorithm {
    public static List<String> radioCoverage(Map<String, List<String>> radioMap) {
        //得到所有地区集合
        HashSet<String> allAreas = new HashSet<>();
        for (String key : radioMap.keySet()) {
            allAreas.addAll(radioMap.get(key));
        }
        //<radio, areaNum>
        Map<String, Integer> radio = new LinkedHashMap<>(radioMap.size());
        List<String> result = new ArrayList<>(radioMap.size());
        int maxArea = 0;
        String maxKey = null;
        //覆盖所有地区
        while (allAreas.size() != 0) {
            //记录当前radioMap中每个radio在当前全部地区的覆盖数量：即LinkedHashMap<radio, areaNum> radio
            for (String key : radioMap.keySet()) {
                for (String area : radioMap.get(key)) {
                    //若radio key在全部地区中有覆盖到的地区，则它的最大覆盖地区加1
                    if (allAreas.contains(area)) {
                        maxArea++;
                    }
                }
                radio.put(key, maxArea);
                maxArea = 0;
            }
            //在radio中找到最大覆盖地区的radio maxKey
            for (String key : radio.keySet()) {
                if (maxArea < radio.get(key)) {
                    maxArea = radio.get(key);
                    maxKey = key;
                }
            }
            maxArea = 0;
            //记录maxKey
            result.add(maxKey);
            //从所有地区中移除maxKey所覆盖的地区
            allAreas.removeAll(radioMap.get(maxKey));
            //从radioMap中移除maxKey
            radioMap.remove(maxKey);
            //radio重置
            radio.clear();
        }
        return result;
    }
}

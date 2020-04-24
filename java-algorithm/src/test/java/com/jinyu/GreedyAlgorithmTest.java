package com.jinyu;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/24 12:14
 */
public class GreedyAlgorithmTest {
    @Test
    public void radioCoverageTest(){
        HashMap<String, List<String>> radioMap = new HashMap<>();
        radioMap.put("k1", Arrays.asList("北京","上海","天津"));
        radioMap.put("k2", Arrays.asList("广州","北京","深圳"));
        radioMap.put("k3", Arrays.asList("成都","上海","杭州"));
        radioMap.put("k4", Arrays.asList("上海","天津"));
        radioMap.put("k5", Arrays.asList("杭州","大连"));

        System.out.println(GreedyAlgorithm.radioCoverage(radioMap));
    }
}

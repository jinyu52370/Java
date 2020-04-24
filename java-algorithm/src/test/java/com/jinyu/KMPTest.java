package com.jinyu;

import org.junit.Test;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/23 20:00
 */
public class KMPTest {
    @Test
    public void violenceMatch() {
//        System.out.println(KMP.violenceMatch("AKLSDAD123JALDK", "123"));
        System.out.println(KMP.kmp("AKLSDAD123JALDK", "123"));
    }
}

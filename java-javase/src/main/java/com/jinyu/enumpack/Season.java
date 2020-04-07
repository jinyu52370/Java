package com.jinyu.enumpack;

public enum Season {
    /**
     * 春
     *  相当于调用有参私有构造
     */
    SPRING("春天","暖和"),
    /**
     * 夏
     */
    SUMMER("夏天","热"),
    /**
     * 秋
     */
    AUTUMN("秋天","凉爽"),
    /**
     * 冬
     */
    WINTER("冬天","冷");

    private final String name;
    private final String describe;

    Season(String name, String describe){
        this.name = name;
        this.describe = describe;
    }

    public void getInfo(){
        System.out.println(this.name + "," +this.describe);
    }
}

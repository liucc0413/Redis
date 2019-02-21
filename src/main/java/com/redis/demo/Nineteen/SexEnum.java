package com.redis.demo.Nineteen;

public enum SexEnum {

    FEMAN(0, "女"),
    MAN(1, "男"),
    TEST1(2, "T1"),
    TEST2(3,"T2");

    private int id;
    private  String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SexEnum getById (int id) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if ( sexEnum.id == id ) {
                return sexEnum;
            }
        }
        return null;
    }
}

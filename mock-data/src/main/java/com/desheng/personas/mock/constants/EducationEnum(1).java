package com.desheng.personas.mock.constants;

//'学历：0未知，1初中及其以下，2高中，3中专，4大专，5本科，6研究生'
public enum EducationEnum {
    UNKNOW(0, "未知"),
    JUNIOR(1, "初中及其以下"),
    SENIOR(2, "高中"),
    SECONDARY_SCHOOL(3, "中专"),
    JUNIOR_COLLEGE(4, "大专"),
    UNDERGRADUATE(5, "本科"),
    POSTGRADUATE(6, "研究生");

    private int id;
    private String name;
    private EducationEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
}

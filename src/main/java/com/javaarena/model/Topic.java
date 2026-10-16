package com.javaarena.model;

public class Topic {

    private int id;
    private String title;
    private String description;
    private String difficulty;
    private int xp;
    private int topicOrder;
    private String content;
    private String introduction;
    private String example;
    private String rules;
    private String practice;
    private String externalUrl;

    // Default Constructor
    public Topic() {
    }

    // Parameterized Constructor
    public Topic(int id, String title, String description,
                 String difficulty, int xp, int topicOrder,String content) {

        this.content=content;            
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.xp = xp;
        this.topicOrder = topicOrder;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getXp() {
        return xp;
    }

    public int getTopicOrder() {
        return topicOrder;
    }
    public String getExternalUrl() {
    return externalUrl;
}

public void setExternalUrl(String externalUrl) {
    this.externalUrl = externalUrl;
}

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setTopicOrder(int topicOrder) {
        this.topicOrder = topicOrder;
    }
    public String getContent() {
    return content;
}

public void setContent(String content) {
    this.content = content;
}
// Introduction
public String getIntroduction() {
    return introduction;
}

public void setIntroduction(String introduction) {
    this.introduction = introduction;
}

// Example
public String getExample() {
    return example;
}

public void setExample(String example) {
    this.example = example;
}

// Rules
public String getRules() {
    return rules;
}

public void setRules(String rules) {
    this.rules = rules;
}

// Practice
public String getPractice() {
    return practice;
}

public void setPractice(String practice) {
    this.practice = practice;
}

}
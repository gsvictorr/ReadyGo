package br.com.tudonamala.backend.enums;

public enum CategoryType {
    
    FOOD("food"),
    CLOTHING("clothing"),
    TOILETRIES("toiletries"),
    DOCUMENTS("documents"),
    TECH("tech"),
    MEDICATION("medication"),
    OTHERS("others");

    private String category;

    CategoryType(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
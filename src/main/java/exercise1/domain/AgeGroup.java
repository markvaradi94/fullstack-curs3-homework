package exercise1.domain;

public enum AgeGroup {
    YOUNG("1-30"),
    ADULT("30-50"),
    ELDER("50+");

    private final String ageCategory;

    AgeGroup(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    protected static AgeGroup determineAgeGroup(int age) {
        if (age <= 30) {
            return YOUNG;
        } else if (age <= 50) {
            return ADULT;
        } else {
            return ELDER;
        }
    }
}

package exercise3.business;

public enum GymReportMemberCategory {
    RED(10),
    YELLOW(30),
    GREEN(Double.MAX_VALUE);

    private final double timeLimit;

    GymReportMemberCategory(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public static GymReportMemberCategory determineCategory(double remainingTime) {
        if (remainingTime < 10) {
            return RED;
        } else if (remainingTime < 30) {
            return YELLOW;
        }
        return GREEN;
    }
}

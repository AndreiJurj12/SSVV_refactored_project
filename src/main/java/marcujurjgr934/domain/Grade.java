package marcujurjgr934.domain;

public class Grade implements HasID<Pair<String, String>> {
    private Pair<String, String> idGrade;
    private double gradeValue;
    private int deliveryWeek;
    private String feedback;

    public Grade(Pair<String, String> idGrade, double gradeValue, int deliveryWeek, String feedback) {
        this.idGrade = idGrade;
        this.gradeValue = gradeValue;
        this.deliveryWeek = deliveryWeek;
        this.feedback = feedback;
    }

    @Override
    public Pair<String, String> getID() { return idGrade; }

    @Override
    public void setID(Pair<String, String> idGrade) { this.idGrade = idGrade; }

    public double getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(double gradeValue) {
        this.gradeValue = gradeValue;
    }

    public int getDeliveryWeek() {
        return deliveryWeek;
    }

    public void setDeliveryWeek(int deliveryWeek) {
        this.deliveryWeek = deliveryWeek;
    }

    public String getFeedback() { return feedback; }

    public void setFeedback(String feedback) { this.feedback = feedback; }

    @Override
    public String toString() {
        return "Grade{" +
                "id_student = " + idGrade.getFirst() +
                ", id_assignment = " + idGrade.getSecond() +
                ", nota = " + gradeValue +
                ", deliveryWeek = " + deliveryWeek +
                ", feedback = '" + feedback + '\'' +
                '}';
    }
}

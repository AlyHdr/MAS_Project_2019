public class Constraint {
    int variable1;
    int variable2;
    char constraintType;
    char operator;
    int k12;
    int weight;

    public Constraint(int variable1, int variable2, char constraintType, char operator, int k12, int weight) {
        this.variable1 = variable1;
        this.variable2 = variable2;
        this.constraintType = constraintType;
        this.operator = operator;
        this.k12 = k12;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return variable1+" "+variable2+" "+constraintType+" "+operator+" "+k12+" "+weight;
    }
}

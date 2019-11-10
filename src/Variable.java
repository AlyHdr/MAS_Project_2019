public class Variable {
    private int domain;
    private int variable;

    public Variable(int domain, int variable) {
        this.domain = domain;
        this.variable = variable;
    }

    public int getDomain() {
        return domain;
    }

    public int getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return "var: "+variable+" dom: "+domain;
    }
}

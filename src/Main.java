public class Main {
    public static void main(String[] args) {
        InstanceTranslator translator = new InstanceTranslator("/home/alyhdr/Desktop/multi-agent/FullRLFAP/GRAPH/graph01/var.txt");
        for(Variable var:translator.parseVaribales()){
            System.out.println(var);
        }
    }
}

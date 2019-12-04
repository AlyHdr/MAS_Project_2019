import java.util.ArrayList;

public class Domain {
    String name;
    ArrayList<Integer> variablesArrayList;
    int nbValues;
    public Domain(String name,ArrayList<Integer> variablesArrayList)
    {
        this.name=name;
        this.variablesArrayList=variablesArrayList;
        this.nbValues=variablesArrayList.size();
    }

    @Override
    public String toString() {
        return name+" "+variablesArrayList+ " Size: "+nbValues+"\n";
    }
}

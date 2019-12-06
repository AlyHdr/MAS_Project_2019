import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class InstanceTranslator {
    private String fileVariables;
    private String fileConstraints;
    private String fileDomains;

    public InstanceTranslator(String fileVariables, String fileConstraints, String fileDomains) {
        this.fileVariables = fileVariables;
        this.fileConstraints = fileConstraints;
        this.fileDomains = fileDomains;
    }

//    public InstanceTranslator(String fileVariables) {
//        this.fileVariables = fileVariables;
//    }
//    public InstanceTranslator(String fileDomains) {
//        this.fileDomains= fileDomains;
//    }

    public InstanceTranslator(String fileConstraints) {
        this.fileConstraints= fileConstraints;
    }

    public ArrayList<Variable> parseVaribales(){
        ArrayList variables = new ArrayList();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileVariables)));
            String line = "";
            while ( (line = reader.readLine()) != null ){
                String values[] = line.trim().split("\\s+");
                int variableNumber=Integer.parseInt(values[0]);
                int domainNumber=Integer.parseInt(values[1]);
                variables.add(new Variable(variableNumber,domainNumber));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return variables;
    }
    public ArrayList<Domain> parseDomains()
    {
        ArrayList domains = new ArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileDomains)));
            String line = "";
            while ( (line = reader.readLine()) != null )
            {
                String values[] = line.trim().split("\\s+");
                int domainNumber=Integer.parseInt(values[0]);
                int domainCardinalty=Integer.parseInt(values[1]);
                ArrayList<Integer> valuesOfDomain=new ArrayList<>();
                for (int i=0;i<domainCardinalty;i++)
                {
                    valuesOfDomain.add(Integer.parseInt(values[i+2]));
                }
                domains.add(new Domain(domainNumber,valuesOfDomain));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return domains;
    }
    public ArrayList<Constraint> parseConstraints()
    {
        ArrayList<Constraint> constraints=new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileConstraints)));
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                String values[] = line.trim().split("\\s+");
                int varaible1 = Integer.parseInt(values[0]);
                int varaible2 = Integer.parseInt(values[1]);
                char type=values[2].charAt(0);
                char operator=values[3].charAt(0);
                int k12=Integer.parseInt(values[4]);
                int weight=0;
                if(values.length==6)
                    weight=Integer.parseInt(values[5]);

                constraints.add(new Constraint(varaible1,varaible2,type,operator,k12,weight));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return constraints;
    }
}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class InstanceTranslator {
    private String fileVariables;
    private String fileConstraints;
    private String fileDomains;

    public InstanceTranslator(String fileVariables, String fileConstraints, String fileDomains) {
        this.fileVariables = fileVariables;
        this.fileConstraints = fileConstraints;
        this.fileDomains = fileDomains;
    }

    public InstanceTranslator(String fileVariables) {
        this.fileVariables = fileVariables;
    }

    public ArrayList<Variable> parseVaribales(){
        ArrayList variables = new ArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileVariables)));
            String line = "";
            while ( (line = reader.readLine()) != null ){
                String values[] = line.trim().split("\\s+");
                variables.add(new Variable(Integer.parseInt(values[0]),Integer.parseInt(values[1])));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return variables;
    }
}

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String basePath = args[0];
        File dir_instances = new File(basePath);
        for(File file:dir_instances.listFiles()){
            if(file.isDirectory()){
                String fileVariables = basePath+file.getName()+"/var.txt";
                String fileDomains = basePath+file.getName()+"/dom.txt";
                String fileConstraints = basePath+file.getName()+"/ctr.txt";
                XCSPWriter writer = new XCSPWriter(args[1]);
                FAPInstanceParser translator = new FAPInstanceParser(fileVariables,fileConstraints,fileDomains);
                ArrayList<Variable> variables=translator.parseVaribales();
                ArrayList<Domain> domains=translator.parseDomains();
                ArrayList<Constraint> constraints=translator.parseConstraints();
                writer.writeXCSP(variables,domains,constraints);
            }

        }
        /*XCSPWriter writer = new XCSPWriter("/home/alyhdr/Desktop/multi-agent/frodo2/test_scene03.xml");
        FAPInstanceParser translator = new FAPInstanceParser(fileVariables,fileConstraints,fileDomains);

        ArrayList<Variable> variables=translator.parseVaribales();
        ArrayList<Domain> domains=translator.parseDomains();
        ArrayList<Constraint> constraints=translator.parseConstraints();
        writer.writeXCSP(variables,domains,constraints);*/
    }
}

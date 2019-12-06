import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String fileVariables="/home/ahmad/Documents/FullRLFAP/CELAR/scen01/var.txt";
        String fileDomains="/home/ahmad/Documents/FullRLFAP/CELAR/scen01/dom.txt";
        String fileConstraints="/home/ahmad/Documents/FullRLFAP/CELAR/scen01/ctr.txt";

        InstanceTranslator translator = new InstanceTranslator(fileVariables,fileConstraints,fileDomains);

        ArrayList<Variable> variables=translator.parseVaribales();
        ArrayList<Domain> domains=translator.parseDomains();
        ArrayList<Constraint> constraints=translator.parseConstraints();
        writeXCSP(variables,domains,constraints);
    }
    public static void writeXCSP(ArrayList<Variable> variablesArray,ArrayList<Domain> domainsArray,ArrayList<Constraint> constraintsArray)
    {
        try
        {
            Element instance=new Element("instance");
            Document document = new Document(instance);
//            document.setRootElement(instance);

            Element agents = new Element("agents");
            agents.setAttribute(new Attribute("nbAgents", String.valueOf(variablesArray.size())));

            Element variables=new Element("variables");
            variables.setAttribute("nbVariables",String.valueOf(variablesArray.size()));

            for (Variable variable: variablesArray)
            {
                Element agentElement=new Element("agent");
                agentElement.setAttribute("name","agent"+variable.getVariableNumber());
                agents.addContent(agentElement);

                Element variableElement=new Element("variable");
                variableElement.setAttribute("name","variable"+variable.getVariableNumber());
                variableElement.setAttribute("domain","domain"+variable.getDomain());
                variableElement.setAttribute("agent","agent"+variable.getVariableNumber());
                variables.addContent(variableElement);
            }

            document.getRootElement().addContent(agents);

            Element domains=new Element("domains");
            domains.setAttribute(new Attribute("nbDomains",String.valueOf(domainsArray.size())));
            for(Domain domain:domainsArray)
            {
                Element domainElement=new Element("domain");
                domainElement.setAttribute("name",domain.getName());
                domainElement.setAttribute("nbValues",String.valueOf(domain.getNbValues()));
                String domainValuesString="";
                for(int domainValues: domain.getVariablesArrayList())
                {
                    domainValuesString+=domainValues+" ";
                }
                domainElement.setText(domainValuesString);
                domains.addContent(domainElement);
            }
            document.getRootElement().addContent(domains);

            document.getRootElement().addContent(variables);

//            staff.addContent(new Element("firstname").setText("yong"));
//            staff.addContent(new Element("lastname").setText("mook kim"));
//            staff.addContent(new Element("nickname").setText("mkyong"));
//            staff.addContent(new Element("salary").setText("199999"));
//
//            doc.getRootElement().addContent(staff);
//
//            Element staff2 = new Element("staff");
//            staff2.setAttribute(new Attribute("id", "2"));
//            staff2.addContent(new Element("firstname").setText("low"));
//            staff2.addContent(new Element("lastname").setText("yin fong"));
//            staff2.addContent(new Element("nickname").setText("fong fong"));
//            staff2.addContent(new Element("salary").setText("188888"));

            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter("output.xml"));

            System.out.println("File Saved!");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

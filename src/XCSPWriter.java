import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.util.ArrayList;

public class XCSPWriter {

    private String outputFile;

    public XCSPWriter(String outputFile) {
        this.outputFile = outputFile;
    }

    public void writeXCSP(ArrayList<Variable> variablesArray, ArrayList<Domain> domainsArray, ArrayList<Constraint> constraintsArray) {
        try {
            Element instance = new Element("instance");
            Document document = new Document(instance);
            Element presenElement = new Element("presentation");
            presenElement.setAttribute("name","sampleProblem");
            presenElement.setAttribute("maxConstraintArity",String.valueOf(2));
            presenElement.setAttribute("maximize","false");
            presenElement.setAttribute("format","XCSP 2.1_FRODO");

            Element agents = new Element("agents");
            agents.setAttribute(new Attribute("nbAgents", String.valueOf(variablesArray.size())));

            Element variables = new Element("variables");
            variables.setAttribute("nbVariables", String.valueOf(variablesArray.size()));

            for (Variable variable : variablesArray) {
                Element agentElement = new Element("agent");
                agentElement.setAttribute("name", "agent" + variable.getVariableNumber());
                agents.addContent(agentElement);

                Element variableElement = new Element("variable");
                variableElement.setAttribute("name", "V" + variable.getVariableNumber());
                variableElement.setAttribute("domain", "domain" + variable.getDomain());
                variableElement.setAttribute("agent", "agent" + variable.getVariableNumber());
                variables.addContent(variableElement);
            }

            Element domains = new Element("domains");
            domains.setAttribute(new Attribute("nbDomains", String.valueOf(domainsArray.size())));
            for (Domain domain : domainsArray) {
                Element domainElement = new Element("domain");
                domainElement.setAttribute("name", domain.getName());
                domainElement.setAttribute("nbValues", String.valueOf(domain.getNbValues()));
                String domainValuesString = "";
                for (int domainValues : domain.getVariablesArrayList()) {
                    domainValuesString += domainValues + " ";
                }
                domainElement.setText(domainValuesString);
                domains.addContent(domainElement);
            }
            Element predicates = new Element("predicates");
            predicates.setAttribute(new Attribute("nbPredicates", String.valueOf(constraintsArray.size())));

            /*for(Constraint constraint:constraintsArray){
                Element predicateElement = new Element("predicate");
                predicateElement.setAttribute("name","pred_"+constraint.variable1+"_"+constraint.variable2);
                Element paramsElement = new Element("parameters");
                paramsElement.setText(" int X1 int X2");
                Element expressionElement = new Element("expression");
                Element functionalElement = new Element("functional");
                if(constraint.operator == '='){
                    functionalElement.setText(" eq(abs(sub(X1, X2)),"+constraint.k+")");
                }else if(constraint.operator == '>'){
                    functionalElement.setText(" gt(abs(sub(X1, X2)),"+constraint.k+")");
                }
                expressionElement.addContent(functionalElement);
                predicateElement.addContent(paramsElement);
                predicateElement.addContent(expressionElement);
                predicates.addContent(predicateElement);
            }*/
            Element pred_eq = new Element("predicate");
            pred_eq.setAttribute("name","pred_eq");
            Element paramsElement = new Element("parameters");
            paramsElement.setText(" int X1 int X2 int K");
            Element expressionElement = new Element("expression");
            Element functionalElement = new Element("functional");
            functionalElement.setText(" if(eq(abs(sub(X1, X2)), K),0,1");
            expressionElement.addContent(functionalElement);
            pred_eq.addContent(paramsElement);
            pred_eq.addContent(expressionElement);

            predicates.addContent(pred_eq);

            Element pred_gt = new Element("predicate");
            pred_gt.setAttribute("name","pred_gt");
            paramsElement = new Element("parameters");
            paramsElement.setText(" int X1 int X2 int K");
            expressionElement = new Element("expression");
            functionalElement = new Element("functional");
            functionalElement.setText(" if(gt(abs(sub(X1, X2)), K),0,1");
            expressionElement.addContent(functionalElement);
            pred_gt.addContent(paramsElement);
            pred_gt.addContent(expressionElement);

            predicates.addContent(pred_gt);

            Element constraints = new Element("constraints");
            constraints.setAttribute(new Attribute("nbConstraints", String.valueOf(constraintsArray.size())));
            for(Constraint constraint:constraintsArray){
                Element constraintElement = new Element("constraint");
                constraintElement.setAttribute("name","const_"+constraint.variable1+"_"+constraint.variable2);
                constraintElement.setAttribute("arity",String.valueOf(2));
                constraintElement.setAttribute("scope","V"+constraint.variable1+" V"+constraint.variable2);
                if(constraint.operator == '=')
                    constraintElement.setAttribute("reference","pred_eq");
                else if(constraint.operator == '>')
                    constraintElement.setAttribute("reference","pred_gt");
                Element parametersElement = new Element("parameters");
                parametersElement.setText(" V"+constraint.variable1+" V"+constraint.variable2+" "+constraint.k);
                constraintElement.addContent(parametersElement);
                constraints.addContent(constraintElement);
            }

            document.getRootElement().addContent(presenElement);
            document.getRootElement().addContent(agents);
            document.getRootElement().addContent(domains);
            document.getRootElement().addContent(variables);
            document.getRootElement().addContent(predicates);
            document.getRootElement().addContent(constraints);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter(this.outputFile));

            System.out.println("File Saved!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

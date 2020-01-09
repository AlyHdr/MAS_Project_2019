import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class CountFreqResult {
    public static void main(String[] args) {
        try {
            File fXmlFile = new File("/home/alyhdr/Desktop/multi-agent/result_sbb_0");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("assignment");
            HashSet<Integer> frequencies = new HashSet<>();
            for(int i=0;i<nList.getLength();i++){
                Node nNode = nList.item(i);
                int value = Integer.parseInt(nNode.getAttributes().getNamedItem("value").getNodeValue());
                frequencies.add(value);
            }
            System.out.println(frequencies);
            System.out.println("Number of used frequencies: "+frequencies.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

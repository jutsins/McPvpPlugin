package baglisted;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ChestDecay {
    Timer timer;

    public void timerChests(int seconds) {
        timer = new Timer();
        timer.schedule(new timerJ(), seconds *1000);
    }

    class timerJ extends TimerTask {
        String xmlChestFilePath = "SpawnChest.xml";
        File file = new File(xmlChestFilePath);

        @Override
        public void run() {
            try {
                    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                    Document document = documentBuilder.parse(file);

                    Element chestDelete = (Element) document.getElementsByTagName("chest").item(0);
                    int chestToDelete = document.getElementsByTagName("chest").getLength();
                if (chestToDelete == 0){
                    timer.cancel();
                    System.out.println("No chests to delete.");
                } else {
                    chestDelete.getParentNode().removeChild(chestDelete);
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();

                    DOMSource domSource1 = new DOMSource(document);
                    StreamResult streamResult1 = new StreamResult(new File(xmlChestFilePath));
                    transformer.transform(domSource1, streamResult1);
                    System.out.println("Deleted oldest chest.");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            timer.cancel();
        }
    }
}

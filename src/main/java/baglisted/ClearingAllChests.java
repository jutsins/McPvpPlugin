package baglisted;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class ClearingAllChests {
    Timer timer;

    public void timerChests(int seconds) {
        timer = new Timer();
        timer.schedule(new timerAllChests(), seconds * 0);
    }

    public class timerAllChests extends TimerTask {
        String xmlChestFilePath = "SpawnChest.xml";
        File file = new File(xmlChestFilePath);

        @Override
        public void run() {

            try {
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(file);
                int chestToDelete = document.getElementsByTagName("chest").getLength();
                for (int i = 0; i < chestToDelete; i++) {
                    Element chestDelete = (Element) document.getElementsByTagName("chest").item(0);
                    chestDelete.getParentNode().removeChild(chestDelete);
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();

                    DOMSource domSource1 = new DOMSource(document);
                    StreamResult streamResult1 = new StreamResult(new File(xmlChestFilePath));
                    transformer.transform(domSource1, streamResult1);
                    System.out.println("A chest has decayed.");

                }
            } catch (Exception e) {
                System.out.println(e);
            }
            timer.cancel();
        }
    }
}

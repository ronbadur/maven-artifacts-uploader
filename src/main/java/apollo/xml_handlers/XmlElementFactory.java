package apollo.xml_handlers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

public class XmlElementFactory {

    public Element createRootXmlElement(Path pathToXml){
        Document document = null;

        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(pathToXml.toFile());
        }  catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return document.getDocumentElement();
    }
}

package apollo.xml_handlers;

import com.google.inject.Inject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class XmlReformer {

    private final XmlTagsRemover xmlTagsRemover;

    @Inject
    public XmlReformer(XmlTagsRemover xmlTagsRemover) {
        this.xmlTagsRemover = xmlTagsRemover;
    }

    public void prepareXmlToDeploy(Path pathToXml){
        Element rootXmlElement = createRootXmlElement(pathToXml);
        xmlTagsRemover.removeTags(rootXmlElement);
        writeXmlChanges(rootXmlElement, pathToXml);
    }

    private Element createRootXmlElement(Path pathToXml){
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

    private void writeXmlChanges(Element xmlRootElement, Path pathToXml) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        try(OutputStream outputStream = new FileOutputStream(pathToXml.toFile())){
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new DOMSource(xmlRootElement.getOwnerDocument()),
                                  new StreamResult(outputStream));
        } catch (IOException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}

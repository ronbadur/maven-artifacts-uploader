package apollo.xml_handlers;

import org.w3c.dom.Element;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

public class XmlWriter {

    public void writeXmlChanges(Element xmlRootElement, Path pathToXml) {
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

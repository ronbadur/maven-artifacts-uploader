package apollo.maven;

import apollo.xml_handlers.XmlElementFactory;
import com.google.inject.Inject;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GAVFactory {

    private final XmlElementFactory xmlElementFactory;

    @Inject
    public GAVFactory(XmlElementFactory xmlElementFactory) {
        this.xmlElementFactory = xmlElementFactory;
    }


    public GAV createGAV(Path pathToPom){
        Element rootXmlElement = xmlElementFactory.createRootXmlElement(pathToPom);

        String groupId = getDirectChildsByTag(rootXmlElement, "groupId");
        String artifactId = getDirectChildsByTag(rootXmlElement, "artifactId");
        String version = getDirectChildsByTag(rootXmlElement, "version");

        return new GAV(groupId, artifactId, version);
    }

    private String getDirectChildsByTag(Element el, String sTagName) {
        NodeList allChilds = el.getElementsByTagName(sTagName);
        List<Element> res = new ArrayList<>();

        for (int i = 0; i < allChilds.getLength(); i++) {
            if (allChilds.item(i).getParentNode().equals(el))
               return allChilds.item(i).getTextContent();
        }

        return el.getElementsByTagName(sTagName).item(0).getTextContent();
    }
}

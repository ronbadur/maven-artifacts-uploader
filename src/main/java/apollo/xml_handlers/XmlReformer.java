package apollo.xml_handlers;

import com.google.inject.Inject;
import org.w3c.dom.Element;

import java.nio.file.Path;

public class XmlReformer {

    private final XmlTagsRemover xmlTagsRemover;
    private final XmlTagChanger xmlTagChanger;
    private final XmlElementFactory xmlElementFactory;
    private final XmlWriter xmlWriter;

    @Inject
    public XmlReformer(XmlTagsRemover xmlTagsRemover, XmlTagChanger xmlTagChanger, XmlElementFactory xmlElementFactory, XmlWriter xmlWriter) {
        this.xmlTagsRemover = xmlTagsRemover;
        this.xmlTagChanger = xmlTagChanger;
        this.xmlElementFactory = xmlElementFactory;
        this.xmlWriter = xmlWriter;
    }

    public void prepareXmlToDeploy(Path pathToXml){
        Element rootXmlElement = xmlElementFactory.createRootXmlElement(pathToXml);

        xmlTagsRemover.removeTags(rootXmlElement);
        xmlTagChanger.changeTag(rootXmlElement);

        xmlWriter.writeXmlChanges(rootXmlElement, pathToXml);
    }
}

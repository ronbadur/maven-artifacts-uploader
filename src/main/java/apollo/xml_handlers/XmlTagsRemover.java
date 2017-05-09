package apollo.xml_handlers;

import com.google.inject.Inject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.TransformerException;
import java.util.List;

public class XmlTagsRemover {

    private final List<String> tagsToRemove;

    @Inject
    public XmlTagsRemover(List<String> tagsToRemove) {
        this.tagsToRemove = tagsToRemove;
    }

    public void removeTags(Element xmlRootElement) {
        for (String tagToRemove: tagsToRemove) {
            NodeList nodesToRemove = xmlRootElement.getElementsByTagName(tagToRemove);
            deleteNodes(nodesToRemove);
        }
    }

    private void deleteNodes(NodeList nodesToDelete){
        while (nodesToDelete.getLength() > 0){
            Node nodeToRemove = nodesToDelete.item(0);
            nodeToRemove.getParentNode().removeChild(nodeToRemove);
        }
    }
}

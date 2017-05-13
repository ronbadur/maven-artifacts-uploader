package apollo.xml_handlers;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XmlTagChanger {

    private final String tagToChange;
    private final String tagNewValue;

    @Inject
    public XmlTagChanger(@Named("tagToChange") String tagToChange,
                         @Named("newValue") String tagNewValue) {
        this.tagToChange = tagToChange;
        this.tagNewValue = tagNewValue;
    }

    public void changeTag(Element xmlRootElement){
        Node nodeToChange = xmlRootElement.getElementsByTagName(tagToChange).item(0);
        
        if (nodeToChange != null && nodeToChange.getTextContent().equals("bundle")){
            nodeToChange.setTextContent(tagNewValue);
        }
    }
}

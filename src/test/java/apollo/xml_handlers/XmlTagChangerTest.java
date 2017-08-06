package apollo.xml_handlers;

import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class XmlTagChangerTest {

    private DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    private XmlTagChanger xmlTagChanger;

    @Test
    public void theTagIsNotExist() throws Exception {
        String tagToChange = "arbitrary-tag";
        String newValue = "test";
        String tagOldValue = "test2";
        File fileToTest = getFile("valid-pom.pom");
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println(exception.getMessage());
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println(exception.getMessage());
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println(exception.getMessage());
            }
        });
        Element element = documentBuilder.parse(fileToTest).getDocumentElement();

        xmlTagChanger = new XmlTagChanger(tagToChange, tagOldValue, newValue);
        int numOfTags = element.getElementsByTagName("arbitrary-tag").getLength();

        assertThat(numOfTags, is(0));
    }

    @Test
    public void theTagIsExist() throws Exception {
        String tagToChange = "packaging";
        String newValue = "jar";
        String tagOldValue = "pom";
        File fileToTest = getFile("valid-pom.pom");
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Element element = documentBuilder.parse(fileToTest).getDocumentElement();

        String oldValue = element.getElementsByTagName("packaging").item(0)
                                                                        .getTextContent();
        xmlTagChanger = new XmlTagChanger(tagToChange, tagOldValue, newValue);
        xmlTagChanger.changeTag(element);
        String currentValue = element.getElementsByTagName("packaging").item(0)
                                                                             .getTextContent();

        assertThat(oldValue, is("pom"));
        assertThat(currentValue, is("jar"));
    }

    private File getFile(String filename) throws URISyntaxException {
        return new File(this.getClass().getClassLoader().getResource(filename).toURI());
    }
}

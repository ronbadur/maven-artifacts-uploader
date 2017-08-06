package apollo.xml_handlers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.File;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;

public class XmlReformerTest {

    private XmlTagsRemover xmlTagsRemover = Mockito.mock(XmlTagsRemover.class);
    private XmlTagChanger xmlTagChanger = Mockito.mock(XmlTagChanger.class);
    private XmlElementFactory xmlElementFactory = Mockito.mock(XmlElementFactory.class);
    private XmlWriter xmlWriter = Mockito.mock(XmlWriter.class);
    private XmlReformer xmlReformer = new XmlReformer(xmlTagsRemover, xmlTagChanger, xmlElementFactory, xmlWriter);

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void reformXml() throws Exception {
        File fileToTest = getFile("valid-pom.pom");

        xmlReformer.prepareXmlToDeploy(fileToTest.toPath());

        Mockito.verify(xmlTagsRemover).removeTags(any());
        Mockito.verify(xmlTagChanger).changeTag(any());
    }

    private File getFile(String filename) throws URISyntaxException {
        return new File(this.getClass().getClassLoader().getResource(filename).toURI());
    }
}

package apollo.xml_handlers;

import org.junit.Test;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class XmlTagsRemoverTest {

    private List<String> tagsToRemove = Arrays.asList("repositories", "pluginRepository");
    private final XmlTagsRemover xmlTagsRemover = new XmlTagsRemover(tagsToRemove);
    private DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    @Test
    public void tagsNotExists() throws Exception {
        File fileToTest = getFile("valid-pom.pom");
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Element element = documentBuilder.parse(fileToTest).getDocumentElement();

        int forbiddenItemsBeforeRemove = element.getElementsByTagName("repositories").getLength();
        xmlTagsRemover.removeTags(element);
        int forbiddenItemsAfterRemove = element.getElementsByTagName("repositories").getLength();

        assertThat(forbiddenItemsBeforeRemove, is(0));
        assertThat(forbiddenItemsAfterRemove, is(0));
    }

    @Test
    public void onlyOneForbiddenTagExist() throws Exception {
        File fileToTest = getFile("OneForbiddenTagExist.pom");
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Element element = documentBuilder.parse(fileToTest).getDocumentElement();

        int forbiddenItemsBeforeRemove = element.getElementsByTagName("repositories").getLength();
        xmlTagsRemover.removeTags(element);
        int forbiddenItemsAfterRemove = element.getElementsByTagName("repositories").getLength();

        assertThat(forbiddenItemsBeforeRemove, is(1));
        assertThat(forbiddenItemsAfterRemove, is(0));
    }

    @Test
    public void twoForbiddenTagsExist() throws Exception {
        File fileToTest = getFile("TwoForbiddenTagsExists.pom");
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Element element = documentBuilder.parse(fileToTest).getDocumentElement();

        int forbiddenItemsBeforeRemove = element.getElementsByTagName("repositories").getLength();
        forbiddenItemsBeforeRemove += element.getElementsByTagName("pluginRepository").getLength();
        xmlTagsRemover.removeTags(element);
        int forbiddenItemsAfterRemove = element.getElementsByTagName("repositories").getLength();
         forbiddenItemsAfterRemove += element.getElementsByTagName("pluginRepository").getLength();

        assertThat(forbiddenItemsBeforeRemove, is(2));
        assertThat(forbiddenItemsAfterRemove, is(0));
    }

    @Test
    public void twoForbiddenTagsExistFewTimes() throws Exception {
        File fileToTest = getFile("TwoForbiddenTagsExistsFewTimes.pom");
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Element element = documentBuilder.parse(fileToTest).getDocumentElement();

        int forbiddenItemsBeforeRemove = element.getElementsByTagName("repositories").getLength();
        forbiddenItemsBeforeRemove += element.getElementsByTagName("pluginRepository").getLength();
        xmlTagsRemover.removeTags(element);
        int forbiddenItemsAfterRemove = element.getElementsByTagName("repositories").getLength();
        forbiddenItemsAfterRemove += element.getElementsByTagName("pluginRepository").getLength();

        assertThat(forbiddenItemsBeforeRemove, is(11));
        assertThat(forbiddenItemsAfterRemove, is(0));
    }

    @Test
    public void deleteSubTagOfForbiddenTag() throws Exception {
        File fileToTest = getFile("OneForbiddenTagExist.pom");
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Element element = documentBuilder.parse(fileToTest).getDocumentElement();

        int subForbiddenItemsBeforeRemove = element.getElementsByTagName("test").getLength();
        xmlTagsRemover.removeTags(element);
        int subForbiddenItemsAfterRemove = element.getElementsByTagName("test").getLength();

        assertThat(subForbiddenItemsBeforeRemove, is(1));
        assertThat(subForbiddenItemsAfterRemove, is(0));
    }

    private File getFile(String filename) throws URISyntaxException {
        return new File(this.getClass().getClassLoader().getResource(filename).toURI());
    }
}

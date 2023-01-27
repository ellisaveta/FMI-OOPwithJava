package xml.file;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CreateXMLFile {
    public static void main(String[] args)
    {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("users");
            doc.appendChild(rootElement);

            //user element
            Element user = doc.createElement("user");
            rootElement.appendChild(user);

            // username element
            Element username = doc.createElement("username");
            username.setTextContent("ivan.petrov");
            user.appendChild(username);

            //password element
            Element password = doc.createElement("password");
            password.setTextContent("ivan123");
            user.appendChild(password);

            //rights for encrypting
            Element rights = doc.createElement("rights");
            rights.setTextContent("yes");
            user.appendChild(rights);

            Element user2 = doc.createElement("user");
            rootElement.appendChild(user2);

            Element username2 = doc.createElement("username");
            username2.setTextContent("KaterinaIvanova");
            user2.appendChild(username2);

            Element password2 = doc.createElement("password");
            password2.setTextContent("katIvanova");
            user2.appendChild(password2);

            Element rights2 = doc.createElement("rights");
            rights2.setTextContent("no");
            user2.appendChild(rights2);

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File("C:\\Eli\\II kurs\\III sem\\OOP_JAVA\\EncryptionRMI\\users.xml"));

            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

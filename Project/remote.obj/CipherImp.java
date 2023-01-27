package app;

import com.CipherInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static java.lang.Math.abs;

public class CipherImp extends UnicastRemoteObject
    implements CipherInterface {

    protected CipherImp() throws RemoteException {
    }

    @Override
    public String encrypted(String cardNumber) throws RemoteException {
        if(!validateCardNumber(cardNumber))
            return "";
        char[] cardNumberAsArray = cardNumber.toCharArray();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < cardNumberAsArray.length; i++) {
            numbers.add(Integer.parseInt(String.valueOf(cardNumberAsArray[i])));
        }
        ArrayList<Integer> cipherAsArray = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            cipherAsArray.add((numbers.get(i) + 5) % 10);
        }
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < cipherAsArray.size(); i++) {
            sb.append(cipherAsArray.get(i));
        }
        String cipher = sb.toString();
        cardsDB.put(cipher, cardNumber);
        return cipher;
    }

    @Override
    public String decrypted(String cipher) throws RemoteException {
        char[] cipherAsArray = cipher.toCharArray();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (char c : cipherAsArray) {
            numbers.add(Integer.parseInt(String.valueOf(c)));
        }
        ArrayList<Integer> cardNumberAsArray = new ArrayList<>();
        for (Integer number : numbers) {
            cardNumberAsArray.add(abs(number - 5) % 10);
        }
        StringBuilder sb  = new StringBuilder();
        for (Integer integer : cardNumberAsArray) {
            sb.append(integer);
        }
        String cardNumber = sb.toString();
        return cardNumber;
    }

    @Override
    public boolean validateCardNumber(String cardNumber) throws RemoteException {
        boolean correctFirstDigit;
        correctFirstDigit = cardNumber.charAt(0) - '0' == 3 || cardNumber.charAt(0) - '0' == 4 ||
                cardNumber.charAt(0) - '0' == 5 || cardNumber.charAt(0) - '0' == 6;

        if(!correctFirstDigit) {
            return false;
        }

        int length = cardNumber.length();
        int sum = 0;
        boolean isSecond = false;
        for (int i = length - 1; i >= 0; i--) {
            int digit = cardNumber.charAt(i) - '0';

            if (isSecond) {   //6014808023
                digit *= 2;
            }
            sum += digit / 10;
            sum += digit % 10;

            isSecond = !isSecond;
        }
        return (sum % 10) == 0;
    }

    @Override
    public boolean validateUserInfo(String username, String password) throws RemoteException {
        String userNameRegex = "^[a-zA-Z][a-zA-Z0-9._-]{3,}$";
        Pattern patternUsername = Pattern.compile(userNameRegex);
        Matcher matcher = patternUsername.matcher(username);

        boolean correctUsername = matcher.matches();

        String passwordRegex = "^[a-zA-Z0-9._-]{3,}$";
        Pattern patternPassword = Pattern.compile(passwordRegex);
        matcher = patternPassword.matcher(password);

        boolean correctPassword = matcher.matches();

        return correctUsername && correctPassword;
    }

    @Override
    public boolean[] correctUserInfo(String username, String password) throws RemoteException {
        boolean[] result = new boolean[2];

        File xmlFile = new File("C:\\\\Eli\\\\II kurs\\\\III sem\\\\OOP_JAVA\\\\EncryptionRMI\\\\users.xml");

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("user");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;
                    Node unameNode = elem.getElementsByTagName("username").item(0);
                    String uname = unameNode.getTextContent();
                    if(Objects.equals(username, uname)) {
                        Node passNode = elem.getElementsByTagName("password").item(0);
                        String pass = passNode.getTextContent();
                        if(Objects.equals(password, pass)) {
                            result[0] = true;
                            Node rightsNode = elem.getElementsByTagName("rights").item(0);
                            String rights = rightsNode.getTextContent();
                            if(Objects.equals(rights, "yes")) {
                                result[1] = true;
                            }
                            return result;
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void writeCardInfoByCiphers() throws RemoteException {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("cardInfoByCiphers.txt"));
            for(Map.Entry<String, String> entry : cardsDB.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                writer.write(String.format("%s : %s\n", key, value));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeCardIngoByCardNumbers() throws RemoteException {
        Map<String, String> cardsDBByValue = cardsDB.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter("cardInfoByCards.txt"));
            for (Map.Entry<String, String> entry : cardsDBByValue.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                writer.write(String.format("%s : %s\n", key, value));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

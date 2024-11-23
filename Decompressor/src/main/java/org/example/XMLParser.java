package org.example;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {
    public void parse(String filePath, DataProcessor processor) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(fis);

            Map<String, String> document = new HashMap<>();
            String currentElement = null;

            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {
                    case XMLStreamReader.START_ELEMENT:
                        currentElement = reader.getLocalName();
                        break;
                    case XMLStreamReader.CHARACTERS:
                        if (currentElement != null && !reader.isWhiteSpace()) {
                            document.put(currentElement, reader.getText().trim());
                        }
                        break;
                    case XMLStreamReader.END_ELEMENT:
                        if ("document".equals(reader.getLocalName())) {
                            processor.processDocument(document);
                            document = new HashMap<>();
                        }
                        currentElement = null;
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

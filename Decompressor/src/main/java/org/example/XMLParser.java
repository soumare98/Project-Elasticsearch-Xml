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

            Map<String, String> document = null;
            String currentElement = null;
            StringBuilder currentText = new StringBuilder();

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamReader.START_ELEMENT:
                        currentElement = reader.getLocalName();
                        if ("product".equals(currentElement)) {
                            document = new HashMap<>();
                        }
                        currentText.setLength(0); // Reset text buffer
                        break;

                    case XMLStreamReader.CHARACTERS:
                        if (currentElement != null) {
                            currentText.append(reader.getText().trim());
                        }
                        break;

                    case XMLStreamReader.END_ELEMENT:
                        String endElement = reader.getLocalName();
                        if ("product".equals(endElement)) {
                            if (document != null) {
                                processor.processDocument(document);
                            }
                            document = null;
                        } else if (document != null && currentElement != null) {
                            document.put(currentElement, currentText.toString());
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

package org.example.validation;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.MessageExtractor;
import org.example.XmlSchemaService;
import org.example.util.XmlUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MessageValidator {

    private final MessageExtractor messageExtractor;
    private final XmlSchemaService xmlSchemaService;

    public MessageValidator(
            MessageExtractor messageExtractor,
            XmlSchemaService xmlSchemaService
    ) {
        this.messageExtractor = messageExtractor;
        this.xmlSchemaService = xmlSchemaService;
    }

    public void validateMessage(Element message) throws MessageValidationException {
        Log.debug("Validating message");

        List<Element> elementChildNodes = XmlUtils.getElementChildNodes(message);
        if (elementChildNodes.size() != 1) {
            throw new MessageValidationException("Size not 1");
        }

        Element tafTapTsiMessage = elementChildNodes.getFirst();
        String messageTypeVersion = messageExtractor.extractMessageTypeVersion(tafTapTsiMessage);
        if (messageTypeVersion == null) {
            throw new MessageValidationException("No MessageTypeVersion");
        }

        Optional<Schema> schema = xmlSchemaService.getSchema(messageTypeVersion);
        if (schema.isEmpty()) {
            throw new MessageValidationException("No schema");
        }

        try {
            schema.get().newValidator().validate(new DOMSource(tafTapTsiMessage));
        } catch (IOException | SAXException e) {
            throw new MessageValidationException(e.getMessage());
        }
    }
}

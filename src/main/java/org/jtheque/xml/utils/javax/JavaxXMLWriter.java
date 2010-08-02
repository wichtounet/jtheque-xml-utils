package org.jtheque.xml.utils.javax;

import org.jtheque.xml.utils.XMLWriter;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/*
 * Copyright JTheque (Baptiste Wicht)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * An XML writer.
 *
 * @author Baptiste Wicht
 */
public final class JavaxXMLWriter implements XMLWriter<Node> {
    private final Document document;

    private Element current;

    /**
     * Construct a new XML writer.
     *
     * @param root The name of the root element.
     */
    public JavaxXMLWriter(String root) {
        super();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            DOMImplementation impl = builder.getDOMImplementation();

            document = impl.createDocument(null, root, null);

            current = document.getDocumentElement();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Unable to create the document");
        }
    }

    @Override
    public void add(String element) {
        Element newElement = document.createElement(element);

        current.appendChild(newElement);

        current = newElement;
    }

    @Override
    public void add(String element, String text) {
        add(element);

        current.setTextContent(text);
    }

    @Override
    public void addOnly(String element, String text) {
        Element newElement = document.createElement(element);

        newElement.setTextContent(text);

        current.appendChild(newElement);
    }

    @Override
    public void addOnly(String element, int value) {
        addOnly(element, Integer.toString(value));
    }

    @Override
    public void addAttribute(String key, String value) {
        current.setAttribute(key, value);
    }

    @Override
    public Element getRoot() {
        return document.getDocumentElement();
    }

    @Override
    public void write(String filePath) {
        XMLUtils.writeXml(document, filePath);
    }

    @Override
    public void switchToParent() {
        current = (Element) current.getParentNode();
    }
}
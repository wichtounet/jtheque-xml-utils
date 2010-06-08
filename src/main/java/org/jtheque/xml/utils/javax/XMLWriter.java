package org.jtheque.xml.utils.javax;

import org.jtheque.xml.utils.XMLException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
public final class XMLWriter {
    private final Document document;

    private Element current;

    /**
     * Construct a new XML writer.
     *
     * @param root The name of the root element.
     */
    public XMLWriter(String root){
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

    /**
     * Add the element to the document and set the new element as the current element.
     *
     * @param element The element to add.
     */
    public void add(String element) {
        Element newElement = document.createElement(element);

        current.appendChild(newElement);

        current = newElement;
    }

    /**
     * Add an element to the document and set the new element as the current element.
     *
     * @param element The name of the element to add.
     * @param text    The text of the element.
     */
    public void add(String element, String text) {
        add(element);

        current.setTextContent(text);
    }

    /**
     * Add the element.
     *
     * @param element The name of the element.
     * @param text    The text of the element.
     */
    public void addOnly(String element, String text) {
        Element newElement = document.createElement(element);

        newElement.setTextContent(text);

        current.appendChild(newElement);
    }

    /**
     * Add only the element with the specified value.
     *
     * @param element The element.
     * @param value   The value of the element.
     */
    public void addOnly(String element, int value) {
        addOnly(element, Integer.toString(value));
    }

    /**
     * Add an attribute to the current element.
     *
     * @param key   The key of the attribute.
     * @param value The value of the attribute.
     */
    public void addAttribute(String key, String value) {
        current.setAttribute(key, value);
    }

    /**
     * Return the root element of the document.
     *
     * @return The root element of the document.
     */
    public Element getRoot() {
        return document.getDocumentElement();
    }

    /**
     * Set the current element.
     *
     * @param element The current element.
     */
    public void setCurrent(Element element) {
        current = element;
    }

    /**
     * Write the XML document to the file path.
     *
     * @param filePath The file path.
     */
    public void write(String filePath) {
        XMLUtils.writeXml(document, filePath);
    }

    /**
     * Switch to parent.
     */
    public void switchToParent() {
        current = (Element) current.getParentNode();
    }
}
package org.jtheque.xml.utils.jdom;

import org.jtheque.xml.utils.XMLWriter;

import org.jdom.Document;
import org.jdom.Element;

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
public final class JDOMXMLWriter implements XMLWriter<Element> {
    private final Document document;

    private Element current;

    /**
     * Construct a new XML writer.
     *
     * @param root The name of the root element.
     */
    public JDOMXMLWriter(String root) {
        super();

        document = new Document(new Element(root));

        current = getRoot();
    }

    @Override
    public void add(String element) {
        Element newElement = new Element(element);

        current.addContent(newElement);

        current = newElement;
    }

    @Override
    public void add(String element, String text) {
        add(element);

        current.setText(text);
    }

    @Override
    public void addOnly(String element, String text) {
        Element newElement = new Element(element);

        newElement.setText(text);

        current.addContent(newElement);
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
        return document.getRootElement();
    }

    @Override
    public void write(String filePath) {
        XMLUtils.writeXml(document, filePath);
    }

    @Override
    public void switchToParent() {
        current = current.getParentElement();
    }
}
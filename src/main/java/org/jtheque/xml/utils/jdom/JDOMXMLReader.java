package org.jtheque.xml.utils.jdom;

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

import org.jtheque.xml.utils.IXMLReader;
import org.jtheque.xml.utils.XMLException;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;

import java.util.Collection;

/**
 * A reader for XML files.
 *
 * @author Baptiste Wicht
 */
public final class JDOMXMLReader extends AbstractReader implements IXMLReader<Element> {
    @Override
    public Element getRootElement() {
        if (getDocument() != null) {
            return getDocument().getRootElement();
        }

        return null;
    }

    @Override
    public Collection<Element> getNodes(String path, Object node) throws XMLException {
        try {
            return XPath.newInstance(path).selectNodes(node);
        } catch (JDOMException e) {
            throw new XMLException("Error selecting nodes", e);
        }
    }

    @Override
    public Element getNode(String path, Object node) throws XMLException {
        Element n;

        try {
            n = (Element) XPath.newInstance(path).selectSingleNode(node);
        } catch (JDOMException e) {
            throw new XMLException("Error selecting nodes", e);
        }

        return n;
    }

    @Override
    public boolean existsNode(String path, Object node) throws XMLException {
        return getNode(path, node) != null;
    }

    @Override
    public boolean existsValue(String path, Object node) throws XMLException {
        return readString(path, node) != null;
    }

    @Override
    public String readString(String path, Object node) throws XMLException {
        String value;

        try {
            value = XPath.newInstance(path).valueOf(node);
        } catch (JDOMException e) {
            throw new XMLException(READING_ERROR, e);
        }

        return value;
    }

    @Override
    public int readInt(String path, Object node) throws XMLException {
        return Integer.parseInt(readString(path, node));
    }

    @Override
    public double readDouble(String path, Object node) throws XMLException {
        return Double.parseDouble(readString(path, node));
    }

    @Override
    public boolean readBoolean(String path, Object node) throws XMLException {
        return Boolean.parseBoolean(readString(path, node));
    }

    @Override
    public long readLong(String path, Object node) throws XMLException {
        return Long.parseLong(readString(path, node));
    }
}
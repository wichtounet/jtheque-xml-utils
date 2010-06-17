package org.jtheque.xml.utils.javax;

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

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import java.util.Collection;

/**
 * A reader for XML files.
 *
 * @author Baptiste Wicht
 */
public final class JavaxXMLReader extends AbstractReader implements IXMLReader<Node> {
    @Override
    public Element getRootElement() {
        if (getDocument() != null) {
            return getDocument().getDocumentElement();
        }

        return null;
    }

    @Override
    public Collection<Node> getNodes(String path, Object node) throws XMLException {
        try {
            return new NodeListCollection((NodeList) getXPath().evaluate(path, node, XPathConstants.NODESET));
        } catch (XPathExpressionException e) {
            throw new XMLException("Error selecting nodes", e);
        }
    }

    @Override
    public Node getNode(String path, Object node) throws XMLException {
        Node n;

        try {
            n = (Node) getXPath().evaluate(path, node, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
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
        try {
            return (String) getXPath().evaluate(path, node, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            throw new XMLException(READING_ERROR, e);
        }
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
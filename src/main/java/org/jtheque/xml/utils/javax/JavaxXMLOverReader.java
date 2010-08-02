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

import org.jtheque.xml.utils.Entry;
import org.jtheque.xml.utils.XMLOverReader;
import org.jtheque.xml.utils.XMLException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

/**
 * A reader for XML files. This reader keep a current element so it's easier to read.
 *
 * @author Baptiste Wicht
 */
public final class JavaxXMLOverReader extends AbstractReader implements XMLOverReader {
    private final Deque<Entry<Node>> elements = new ArrayDeque<Entry<Node>>();

    @Override
    public boolean readNode(String path) throws XMLException {
        try {
            Element n = (Element) getXPath().compile(path).evaluate(getCurrent(), XPathConstants.NODE);

            if (n != null) {
                setCurrent(n);

                return true;
            }
        } catch (XPathExpressionException e) {
            throw new XMLException("Error selecting node", e);
        }

        return false;
    }

    @Override
    public void switchToParent() {
        if (getCurrent() == null) {
            throw new IllegalArgumentException("No current element");
        }

        setCurrent((Element) getCurrent().getParentNode());
    }

    @Override
    public boolean next(String path) throws XMLException {
        if (elements.isEmpty() || !elements.peek().getPath().equals(path)) {
            try {
                Collection<Node> nodes = new NodeListCollection((NodeList) getXPath().compile(path).evaluate(getCurrent(),
                        XPathConstants.NODESET));

                elements.addFirst(new Entry<Node>(path, nodes.iterator()));
            } catch (XPathExpressionException e) {
                throw new XMLException("Error selecting nodes", e);
            }
        }

        if (elements.peek().getElements().hasNext()) {
            setCurrent((Element) elements.peek().getElements().next());

            return true;
        } else {
            elements.poll();

            return false;
        }
    }

    @Override
    public String readString(String path) throws XMLException {
        try {
            return (String) getXPath().compile(path).evaluate(getCurrent(), XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            throw new XMLException(READING_ERROR, e);
        }
    }

    @Override
    public int readInt(String path) throws XMLException {
        return Integer.parseInt(readString(path));
    }

    @Override
    public double readDouble(String path) throws XMLException {
        return Double.parseDouble(readString(path));
    }

    @Override
    public boolean readBoolean(String path) throws XMLException {
        return Boolean.parseBoolean(readString(path));
    }

    @Override
    public long readLong(String path) throws XMLException {
        return Long.parseLong(readString(path));
    }
}
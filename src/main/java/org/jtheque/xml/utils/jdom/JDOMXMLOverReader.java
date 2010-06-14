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

import org.jtheque.xml.utils.Entry;
import org.jtheque.xml.utils.IXMLOverReader;
import org.jtheque.xml.utils.XMLException;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A reader for XML files. This reader keep a current element so it's easier to read.
 *
 * @author Baptiste Wicht
 */
public final class JDOMXMLOverReader extends AbstractReader implements IXMLOverReader {
    private final Deque<Entry<Element>> elements = new ArrayDeque<Entry<Element>>();

    @Override
    public boolean readNode(String path) throws XMLException {
        try {
            Element n = (Element) XPath.newInstance(path).selectSingleNode(getCurrent());

            if (n != null) {
                setCurrent(n);

                return true;
            }
        } catch (JDOMException e) {
            throw new XMLException("Error selecting nodes", e);
        }

        return false;
    }

    @Override
    public void switchToParent() {
        if (getCurrent() == null) {
            throw new IllegalArgumentException("No current element");
        }

        setCurrent(getCurrent().getParentElement());
    }

    @Override
    public boolean next(String path) throws XMLException {
        if (elements.isEmpty() || !elements.peek().getPath().equals(path)) {
            try {
                elements.addFirst(new Entry<Element>(path, XPath.newInstance(path).selectNodes(getCurrent()).iterator()));
            } catch (JDOMException e) {
                throw new XMLException("Error selecting nodes", e);
            }
        }

        if (elements.peek().getElements().hasNext()) {
            setCurrent(elements.peek().getElements().next());

            return true;
        } else {
            elements.poll();

            return false;
        }
    }

    @Override
    public String readString(String path) throws XMLException {
        String value;

        try {
            value = XPath.newInstance(path).valueOf(getCurrent());
        } catch (JDOMException e) {
            throw new XMLException(READING_ERROR, e);
        }

        return value;
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
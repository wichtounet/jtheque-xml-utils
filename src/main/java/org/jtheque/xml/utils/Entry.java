package org.jtheque.xml.utils;

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

import java.util.Iterator;

/**
 * An entry of the elements stack.
 *
 * @author Baptiste Wicht
 */
public final class Entry<T> {
    private final String path;
    private final Iterator<T> elements;

    /**
     * Construct a new Entry.
     *
     * @param path     The XPath request.
     * @param elements The iterator on the found elements.
     */
    public Entry(String path, Iterator<T> elements) {
        super();

        this.path = path;
        this.elements = elements;
    }

    /**
     * Return the XPath request.
     *
     * @return The XPath request.
     */
    public String getPath() {
        return path;
    }

    /**
     * Return the iterator on the results of the request.
     *
     * @return The iterator on the results of the request.
     */
    public Iterator<T> getElements() {
        return elements;
    }
}

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

public interface IXMLOverReader extends Reader {
    /**
     * Read a node from the current element. The current element become the read node if there is one.
     *
     * @param path The path of the node.
     *
     * @return if the node exists.
     *
     * @throws XMLException If an error occurs during XML Processing.
     */
    boolean readNode(String path) throws XMLException;

    /**
     * Change the current element for the parent of current element.
     */
    void switchToParent();

    /**
     * Go to the next element of the request. The first time this method is called on a specific path, the request is
     * executed and the elements enqueued. The next time, we only use the enqueued iterator to retrieve the next
     * element.
     *
     * @param path The XPath request.
     *
     * @return <code>true</code> if there is a next element else <code>false</code>.
     *
     * @throws XMLException If an error occurs during XML Processing.
     */
    boolean next(String path) throws XMLException;

    /**
     * Read a String value from the node.
     *
     * @param path The XPath request.
     *
     * @return The string value of the request.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    String readString(String path) throws XMLException;

    /**
     * Read a int value from the node.
     *
     * @param path The XPath request.
     *
     * @return The int value of the request.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    int readInt(String path) throws XMLException;

    /**
     * Read a double value from the node.
     *
     * @param path The XPath request.
     *
     * @return The double value of the request.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    double readDouble(String path) throws XMLException;

    /**
     * Read a boolean value from the node.
     *
     * @param path The XPath request.
     *
     * @return The boolean value of the request.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    boolean readBoolean(String path) throws XMLException;

    /**
     * Read a long value from the node.
     *
     * @param path The XPath request.
     *
     * @return The double value of the request.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    long readLong(String path) throws XMLException;
}

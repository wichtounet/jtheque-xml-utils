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

import java.util.Collection;

/**
 * A simple XML Reader specification.
 *
 * @author Baptiste Wicht
 * @param <T> The type of element.
 */
public interface XMLReader<T> extends Reader {
    /**
     * Return the root element of the reader.
     *
     * @return the root element else null if the reader is not open.
     */
    T getRootElement();

    /**
     * Return all the nodes corresponding to the XPath request on the specified node.
     *
     * @param path The XPath request.
     * @param node The node to request in.
     *
     * @return A List containing all elements corresponding to the request.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    Collection<T> getNodes(String path, Object node) throws XMLException;

    /**
     * Return the unique node corresponding to the XPath request on the specified node.
     *
     * @param path The XPath request.
     * @param node The node to request in.
     *
     * @return The unique node corresponding to the request else null if there is no node corresponding to the request.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    T getNode(String path, Object node) throws XMLException;

    /**
     * Indicate if a node exists with the specified path in the specified node.
     *
     * @param path The path to search for.
     * @param node The node to search from.
     *
     * @return true if the path exists from the node else false.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    boolean existsNode(String path, Object node) throws XMLException;

    /**
     * Indicate if a value exists with the specified path in the specified node.
     *
     * @param path The path to search for.
     * @param node The node to search from.
     *
     * @return true if the path exists from the node else false.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    boolean existsValue(String path, Object node) throws XMLException;

    /**
     * Read a String value from the node.
     *
     * @param path The XPath request.
     * @param node The node.
     *
     * @return The string value of the request.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    String readString(String path, Object node) throws XMLException;

    /**
     * Read a int value from the node.
     *
     * @param path The XPath request.
     * @param node The node.
     *
     * @return The int value of the request.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    int readInt(String path, Object node) throws XMLException;

    /**
     * Read a double value from the node.
     *
     * @param path The XPath request.
     * @param node The node.
     *
     * @return The double value of the request.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    double readDouble(String path, Object node) throws XMLException;

    /**
     * Read a boolean value from the node.
     *
     * @param path The XPath request.
     * @param node The node.
     *
     * @return The boolean value of the request.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    boolean readBoolean(String path, Object node) throws XMLException;

    /**
     * Read a long value from the node.
     *
     * @param path The XPath request.
     * @param node The node.
     *
     * @return The double value of the request.
     *
     * @throws XMLException If an errors occurs during the reading process.
     */
    long readLong(String path, Object node) throws XMLException;
}

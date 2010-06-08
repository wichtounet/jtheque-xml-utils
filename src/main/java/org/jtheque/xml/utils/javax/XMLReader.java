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

import org.jtheque.xml.utils.XMLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;

/**
 * A reader for XML files.
 *
 * @author Baptiste Wicht
 */
public final class XMLReader implements Closeable {
    private InputStream stream;
    private Document document;

    private static final String OPEN_ERROR = "Error opening the file";
    private static final String READING_ERROR = "Error reading the file";

    private XPathFactory xPathFactory;
    private XPath xPath;

    /**
     * Open the file a the URL.
     *
     * @param strUrl The URL of the XML file.
     * @throws XMLException If an error occurs during the open process.
     */
    public void openURL(String strUrl) throws XMLException {
        URL url;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException e) {
            throw new XMLException("Invalid URL", e);
        }

        openURL(url);
    }

    /**
     * Open the file a the URL.
     *
     * @param url The URL of the XML file.
     * @throws XMLException If an error occurs during the open process.
     */
    public void openURL(URL url) throws XMLException {
        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.setUseCaches(false);

            urlConnection.connect();

            stream = urlConnection.getInputStream();

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            document = docBuilder.parse(stream);
            document.getDocumentElement().normalize();

            xPathFactory = XPathFactory.newInstance();
            xPath = xPathFactory.newXPath();
        } catch (IOException e) {
            throw new XMLException(OPEN_ERROR, e);
        } catch (ParserConfigurationException e) {
            throw new XMLException(OPEN_ERROR, e);
        } catch (SAXException e) {
            throw new XMLException(OPEN_ERROR, e);
        }
    }

    /**
     * Open the file.
     *
     * @param strFile The path to the file to open.
     * @throws XMLException If an error occurs during the open process.
     */
    public void openFile(String strFile) throws XMLException {
        openFile(new File(strFile));
    }

    /**
     * Open the file.
     *
     * @param file The file to open.
     * @throws XMLException If an error occurs during the open process.
     */
    public void openFile(File file) throws XMLException {
        try {
            stream = new FileInputStream(file);

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            document = docBuilder.parse(stream);
            document.getDocumentElement().normalize();

            xPathFactory = XPathFactory.newInstance();
            xPath = xPathFactory.newXPath();
        } catch (IOException e) {
            throw new XMLException(OPEN_ERROR, e);
        } catch (ParserConfigurationException e) {
            throw new XMLException(OPEN_ERROR, e);
        } catch (SAXException e) {
            throw new XMLException(OPEN_ERROR, e);
        }
    }

    @Override
    public void close() throws IOException {
        if (stream != null) {
            stream.close();
        }
    }

    /**
     * Return the root element of the reader.
     *
     * @return the root element else null if the reader is not open.
     */
    public Element getRootElement() {
        if (document != null) {
            return document.getDocumentElement();
        }

        return null;
    }

    /**
     * Return all the nodes corresponding to the XPath request on the specified node.
     *
     * @param path The XPath request.
     * @param node The node to request in.
     * @return A List containing all elements corresponding to the request.
     * @throws XMLException If an errors occurs during the reading process.
     */
    public Collection<Node> getNodes(String path, Object node) throws XMLException {
        try {
            return new NodeListCollection((NodeList) xPath.evaluate(path, node, XPathConstants.NODESET));
        } catch (XPathExpressionException e) {
            throw new XMLException("Error selecting nodes", e);
        }
    }

    /**
     * Return the unique node corresponding to the XPath request on the specified node.
     *
     * @param path The XPath request.
     * @param node The node to request in.
     * @return The unique node corresponding to the request else null if there is no node corresponding to the request.
     * @throws XMLException If an errors occurs during the reading process.
     */
    public Node getNode(String path, Object node) throws XMLException {
        Node n;

        try {
            n = (Node) xPath.evaluate(path, node, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            throw new XMLException("Error selecting nodes", e);
        }

        return n;
    }

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
    public boolean existsNode(String path, Object node) throws XMLException {
        return getNode(path, node) != null;
    }

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
    public boolean existsValue(String path, Object node) throws XMLException {
        return readString(path, node) != null;
    }

    /**
     * Read a String value from the node.
     *
     * @param path The XPath request.
     * @param node The node.
     * @return The string value of the request.
     * @throws XMLException If an errors occurs during the reading process.
     */
    public String readString(String path, Object node) throws XMLException {
        String value;

        try {
            value = (String) xPath.evaluate(path, node, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            throw new XMLException(READING_ERROR, e);
        }

        return value;
    }

    /**
     * Read a int value from the node.
     *
     * @param path The XPath request.
     * @param node The node.
     * @return The int value of the request.
     * @throws XMLException If an errors occurs during the reading process.
     */
    public int readInt(String path, Object node) throws XMLException {
        return Integer.parseInt(readString(path, node));
    }

    /**
     * Read a double value from the node.
     *
     * @param path The XPath request.
     * @param node The node.
     * @return The double value of the request.
     * @throws XMLException If an errors occurs during the reading process.
     */
    public double readDouble(String path, Object node) throws XMLException {
        return Double.parseDouble(readString(path, node));
    }

    /**
     * Read a boolean value from the node.
     *
     * @param path The XPath request.
     * @param node The node.
     * @return The boolean value of the request.
     * @throws XMLException If an errors occurs during the reading process.
     */
    public boolean readBoolean(String path, Object node) throws XMLException {
        return Boolean.parseBoolean(readString(path, node));
    }

    /**
     * Read a long value from the node.
     *
     * @param path The XPath request.
     * @param node The node.
     * @return The double value of the request.
     * @throws XMLException If an errors occurs during the reading process.
     */
    public long readLong(String path, Object node) throws XMLException {
        return Long.parseLong(readString(path, node));
    }

}
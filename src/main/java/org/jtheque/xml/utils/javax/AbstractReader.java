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

import org.jtheque.xml.utils.Reader;
import org.jtheque.xml.utils.XMLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public abstract class AbstractReader implements Reader {
    private static final String OPEN_ERROR = "Error opening the file";
    static final String READING_ERROR = "Error reading the file";

    private InputStream stream;
    private Element current;
    private XPath xPath;
    private Document document;

    @Override
    public void openURL(String strUrl) throws XMLException {
        URL url;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException e) {
            throw new XMLException("Invalid URL", e);
        }

        openURL(url);
    }

    @Override
    public final void openURL(URL url) throws XMLException {
        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.setUseCaches(false);

            urlConnection.connect();

            openStream(urlConnection.getInputStream());
        } catch (IOException e) {
            throw new XMLException(OPEN_ERROR, e);
        }
    }

    @Override
    public void openFile(String strFile) throws XMLException {
        openFile(new File(strFile));
    }

    @Override
    public final void openFile(File file) throws XMLException {
        try {
            openStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new XMLException(OPEN_ERROR, e);
        }
    }

    @Override
    public final void openStream(InputStream stream) throws XMLException {
        try {
            this.stream = stream;

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            document = docBuilder.parse(stream);
            document.getDocumentElement().normalize();

            xPath = XPathFactory.newInstance().newXPath();

            current = document.getDocumentElement();
        } catch (IOException e) {
            throw new XMLException(OPEN_ERROR, e);
        } catch (ParserConfigurationException e) {
            throw new XMLException(OPEN_ERROR, e);
        } catch (SAXException e) {
            throw new XMLException(OPEN_ERROR, e);
        }
    }

    @Override
    public final void close() throws IOException {
        if (stream != null) {
            stream.close();
        }
    }

    final Node getCurrent() {
        return current;
    }

    final XPath getXPath() {
        return xPath;
    }

    final Document getDocument() {
        return document;
    }

    final void setCurrent(Element current) {
        this.current = current;
    }
}
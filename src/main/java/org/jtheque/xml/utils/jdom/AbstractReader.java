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

import org.jtheque.xml.utils.Reader;
import org.jtheque.xml.utils.XMLException;

import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * An abstract reader for JDOM.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractReader implements Reader {
    private static final String OPEN_ERROR = "Error opening the file";
    static final String READING_ERROR = "Error reading the file";

    private InputStream stream;
    private Document document;
    private Element current;

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

            stream = urlConnection.getInputStream();

            openStream(stream);
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
            stream = new FileInputStream(file);

            openStream(stream);
        } catch (IOException e) {
            throw new XMLException(OPEN_ERROR, e);
        }
    }

    @Override
    public final void openStream(InputStream stream) throws XMLException {
        try {
            SAXBuilder sxb = new SAXBuilder();

            document = sxb.build(stream);

            current = document.getRootElement();
        } catch (JDOMException e) {
            throw new XMLException(OPEN_ERROR, e);
        } catch (IOException e) {
            throw new XMLException(OPEN_ERROR, e);
        }
    }

    @Override
    public final void close() throws IOException {
        if (stream != null) {
            stream.close();
        }
    }

    /**
     * Return the current element.
     *
     * @return The current element.
     */
    final Content getCurrent() {
        return current;
    }

    /**
     * Set the current element.
     *
     * @param current The current element.
     */
    final void setCurrent(Element current) {
        this.current = current;
    }

    /**
     * Return the document currently read by the reader.
     *
     * @return The document.
     */
    final Document getDocument() {
        return document;
    }
}

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

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * A basic XML Reader. Provide only ways to open XML documents. 
 *
 * @author Baptiste Wicht
 */
public interface Reader extends Closeable {
    /**
     * Open the file a the URL.
     *
     * @param strUrl The URL of the XML file.
     *
     * @throws XMLException If an error occurs during the open process.
     */
    void openURL(String strUrl) throws XMLException;

    /**
     * Open the file a the URL.
     *
     * @param url The URL of the XML file.
     *
     * @throws XMLException If an error occurs during the open process.
     */
    void openURL(URL url) throws XMLException;

    /**
     * Open the file.
     *
     * @param strFile The path to the file to open.
     *
     * @throws XMLException If an error occurs during the open process.
     */
    void openFile(String strFile) throws XMLException;

    /**
     * Open the file.
     *
     * @param file The file to open.
     *
     * @throws XMLException If an error occurs during the open process.
     */
    void openFile(File file) throws XMLException;

    /**
     * Open the given stream.
     *
     * @param stream The stream to open.
     *
     * @throws XMLException If an error occurs during the open process.
     */
    void openStream(InputStream stream) throws XMLException;
}

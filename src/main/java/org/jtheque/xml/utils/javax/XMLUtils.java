package org.jtheque.xml.utils.javax;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;

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

/**
 * An utility class for XML.
 *
 * @author Baptiste Wicht
 */
public final class XMLUtils {
    /**
     * This is an utility class, not instanciable.
     */
    private XMLUtils() {
        super();
    }

    /**
     * Save a XML file.
     *
     * @param doc  The XML document to save.
     * @param path The path to the file.
     */
    public static void writeXml(Document doc, String path) {
        boolean fileOk = true;

        File f = new File(path);

        if (!f.exists()) {
            try {
                fileOk = f.createNewFile();
            } catch (IOException e) {
                fileOk = false;
                LoggerFactory.getLogger(XMLUtils.class).error(e.getMessage(), e);
            }
        }

        if (fileOk) {
            try {
                Source source = new DOMSource(doc);

                Result result = new StreamResult(f);

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, result);
            } catch (TransformerConfigurationException e) {
                LoggerFactory.getLogger(XMLUtils.class).error(e.getMessage(), e);
            } catch (TransformerException e) {
                LoggerFactory.getLogger(XMLUtils.class).error(e.getMessage(), e);
            }
        }
    }
}
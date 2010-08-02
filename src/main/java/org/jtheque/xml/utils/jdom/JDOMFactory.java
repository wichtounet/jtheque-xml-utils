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

import org.jtheque.xml.utils.NodeLoader;
import org.jtheque.xml.utils.NodeSaver;
import org.jtheque.xml.utils.XMLOverReader;
import org.jtheque.xml.utils.XMLReader;
import org.jtheque.xml.utils.XMLWriter;
import org.jtheque.xml.utils.XMLFactory;

import org.jdom.Element;

/**
 * A XML Factory implementation for JDOM.
 *
 * @author Baptiste Wicht
 */
public final class JDOMFactory implements XMLFactory<Element, JDOMXMLWriter> {
    @Override
    public NodeLoader<Element> newNodeLoader() {
        return new JDOMNodeLoader();
    }

    @Override
    public NodeSaver<JDOMXMLWriter> newNodeSaver() {
        return new JDOMNodeSaver();
    }

    @Override
    public XMLWriter<Element> newWriter(String root) {
        return new JDOMXMLWriter(root);
    }

    @Override
    public XMLReader<Element> newReader() {
        return new JDOMXMLReader();
    }

    @Override
    public XMLOverReader newOverReader() {
        return new JDOMXMLOverReader();
    }
}

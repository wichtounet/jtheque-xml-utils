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

import org.jtheque.xml.utils.NodeLoader;
import org.jtheque.xml.utils.NodeSaver;
import org.jtheque.xml.utils.XMLOverReader;
import org.jtheque.xml.utils.XMLReader;
import org.jtheque.xml.utils.XMLWriter;
import org.jtheque.xml.utils.XMLFactory;

import org.w3c.dom.Node;

/**
 * A factory to create XML instances using Java.
 *
 * @author Baptiste Wicht
 */
public final class JavaxFactory implements XMLFactory<Node, XMLWriter<Node>> {
    @Override
    public NodeLoader<Node> newNodeLoader() {
        return new JavaxNodeLoader();
    }

    @Override
    public NodeSaver<XMLWriter<Node>> newNodeSaver() {
        return new JavaxNodeSaver();
    }

    @Override
    public XMLWriter<Node> newWriter(String root) {
        return new JavaxXMLWriter(root);
    }

    @Override
    public XMLReader<Node> newReader() {
        return new JavaxXMLReader();
    }

    @Override
    public XMLOverReader newOverReader() {
        return new JavaxXMLOverReader();
    }
}
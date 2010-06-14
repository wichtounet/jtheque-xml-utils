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

import org.jtheque.xml.utils.INodeLoader;
import org.jtheque.xml.utils.INodeSaver;
import org.jtheque.xml.utils.IXMLOverReader;
import org.jtheque.xml.utils.IXMLReader;
import org.jtheque.xml.utils.IXMLWriter;
import org.jtheque.xml.utils.XMLFactory;

import org.w3c.dom.Node;

public final class JavaxFactory implements XMLFactory<Node, IXMLWriter<Node>> {
    @Override
    public INodeLoader<Node> newNodeLoader() {
        return new JavaxNodeLoader();
    }

    @Override
    public INodeSaver<IXMLWriter<Node>> newNodeSaver() {
        return new JavaxNodeSaver();
    }

    @Override
    public IXMLWriter<Node> newWriter(String root) {
        return new JavaxXMLWriter(root);
    }

    @Override
    public IXMLReader<Node> newReader() {
        return new JavaxXMLReader();
    }

    @Override
    public IXMLOverReader newXMlReader() {
        return new JavaxXMLOverReader();
    }
}
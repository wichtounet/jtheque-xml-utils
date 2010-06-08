package org.jtheque.xml.utils.javax;

import org.jtheque.xml.utils.Node;
import org.jtheque.xml.utils.NodeAttribute;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collection;

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

public final class NodeLoader {
    private NodeLoader() {
        super();
    }

    /**
     * Resolve the node states from the XML elements.
     *
     * @param nodes The nodes to transform to Node.
     * @return A List containing all the resolved Node.
     */
    public static Collection<Node> resolveNodeStates(Collection<org.w3c.dom.Node> nodes) {
        Collection<Node> nodeStates = new ArrayList<Node>(nodes.size());

        for (org.w3c.dom.Node element : nodes) {
            Node node = resolve(element);

            nodeStates.add(node);
        }

        return nodeStates;
    }

    /**
     * Resolve a Node apart from an Element.
     *
     * @param element The Element representing the Node
     * @return The resolved Node.
     */
    private static Node resolve(org.w3c.dom.Node element) {
        Node node = new Node(element.getNodeName());

        readNode(element, node);
        readAttributes(element, node);

        return node;
    }

    /**
     * Read and fill the Node from the element.
     *
     * @param element   The element to read.
     * @param node The node state to fill.
     */
    private static void readNode(org.w3c.dom.Node element, Node node) {
        if (element.getChildNodes().getLength() == 0) {
            String text = element.getTextContent();

            if (text != null && !text.isEmpty()) {
                node.setText(text);
            }
        } else {
            NodeList childrenElements = element.getChildNodes();

            Collection<Node> childrens = new ArrayList<Node>(childrenElements.getLength());

            for(int i = 0; i < childrenElements.getLength(); i++){
                childrens.add(resolve(childrenElements.item(i)));
            }

            node.setChildrens(childrens);
        }
    }

    /**
     * Read the attributes of the node state.
     *
     * @param element   The element to get the attributes from.
     * @param node The node state to fill.
     */
    private static void readAttributes(org.w3c.dom.Node element, Node node) {
        if (element.getAttributes().getLength() != 0) {
            NamedNodeMap attributes = element.getAttributes();

            Collection<NodeAttribute> nodeAttributes = new ArrayList<NodeAttribute>(attributes.getLength());

            for (int i = 0; i < attributes.getLength(); i++) {
                nodeAttributes.add(new NodeAttribute(
                        attributes.item(i).getNodeName(),
                        attributes.item(i).getTextContent()));
            }

            node.setAttributes(nodeAttributes);
        }
    }
}
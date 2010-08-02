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
 * A Node Loader. It takes a collection of node from a XML file and resolve it into generic Node.
 *
 * @author Baptiste Wicht
 * @param <T> The type of node to resolve.
 */
public interface NodeLoader<T> {
    /**
     * Resolve the node states from the XML elements.
     *
     * @param nodes The nodes to transform to Node.
     *
     * @return A List containing all the resolved Node.
     */
    Collection<Node> resolveNodeStates(Collection<T> nodes);
}

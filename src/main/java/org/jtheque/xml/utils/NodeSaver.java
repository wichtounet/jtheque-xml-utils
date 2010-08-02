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

/**
 * A node saver. It takes a collection of generic Nodes and write them using the given writer.
 *
 * @author Baptiste Wicht
 * @param <T> The type of writer.
 */
public interface NodeSaver<T> {
    /**
     * Write the given generic nodes into the given writer.
     *
     * @param writer The writer to use.
     * @param nodes  The nodes to write.
     */
    void writeNodes(T writer, Iterable<Node> nodes);
}
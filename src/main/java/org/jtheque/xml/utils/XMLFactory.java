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
 * A XML Factory. It's a factory who can creates XML object instances to read and write XML files.
 *
 * @author Baptiste Wicht
 * @param <T> The type of elements.
 * @param <K> The type of writer.
 */
public interface XMLFactory<T, K> {
    /**
     * Create a new NodeLoader.
     *
     * @return A new node loader.
     */
    NodeLoader<T> newNodeLoader();

    /**
     * Create a new NodeSaver.
     *
     * @return A new node saver.
     */
    NodeSaver<K> newNodeSaver();

    /**
     * Create a new Writer.
     *
     * @param root The root of the file.
     *
     * @return A New XML Writer using the given root name as root element.
     */
    XMLWriter<T> newWriter(String root);

    /**
     * Create a new XML reader.
     *
     * @return A new XML reader.
     */
    XMLReader<T> newReader();

    /**
     * Create a new XML Over Reader.
     *
     * @return A new XML Over reader.
     */
    XMLOverReader newOverReader();
}

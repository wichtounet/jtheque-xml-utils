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
 * An XML writer.
 *
 * @param <T> The type of element.
 *
 * @author Baptiste Wicht
 */
public interface IXMLWriter<T> {
    /**
     * Add the element to the document and set the new element as the current element.
     *
     * @param element The element to add.
     */
    void add(String element);

    /**
     * Add an element to the document and set the new element as the current element.
     *
     * @param element The name of the element to add.
     * @param text    The text of the element.
     */
    void add(String element, String text);

    /**
     * Add the element.
     *
     * @param element The name of the element.
     * @param text    The text of the element.
     */
    void addOnly(String element, String text);

    /**
     * Add only the element with the specified value.
     *
     * @param element The element.
     * @param value   The value of the element.
     */
    void addOnly(String element, int value);

    /**
     * Add an attribute to the current element.
     *
     * @param key   The key of the attribute.
     * @param value The value of the attribute.
     */
    void addAttribute(String key, String value);

    /**
     * Return the root element of the document.
     *
     * @return The root element of the document.
     */
    T getRoot();

    /**
     * Write the XML document to the file path.
     *
     * @param filePath The file path.
     */
    void write(String filePath);

    /**
     * Switch to parent.
     */
    void switchToParent();
}

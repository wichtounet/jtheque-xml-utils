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

import org.jtheque.utils.StringUtils;
import org.jtheque.utils.collections.CollectionUtils;

import java.util.Collection;

/**
 * A node of a state.
 *
 * @author Baptiste Wicht
 */
public final class Node {
    private final String name;
    private String text;

    private Collection<Node> childrens = CollectionUtils.newList(5);
    private Collection<NodeAttribute> attributes = CollectionUtils.newList(3);

    /**
     * Construct a new Node.
     *
     * @param name The name of the node.
     */
    public Node(String name) {
        super();

        this.name = name;
    }

    /**
     * Construct a new Node.
     *
     * @param name The name of the node.
     * @param text The text of the node.
     */
    public Node(String name, String text) {
        this(name);

        this.text = text;
    }

    /**
     * Return the name of the node.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the childrens of the node.
     *
     * @return A List containing all the Node children.
     */
    public Iterable<Node> getChildrens() {
        return childrens;
    }

    /**
     * Add a child to the node.
     *
     * @param child The child to add.
     */
    public void addChild(Node child) {
        childrens.add(child);
    }

    /**
     * Set the childrens of the node.
     *
     * @param childrens The childrens.
     */
    public void setChildrens(Collection<Node> childrens) {
        this.childrens = CollectionUtils.copyOf(childrens);
    }

    /**
     * Add a simple child with a string value.
     *
     * @param name  The name of the node.
     * @param value The value of the node.
     */
    public void addSimpleChildValue(String name, String value) {
        childrens.add(new Node(name, value));
    }

    /**
     * Add a simple child with a int value.
     *
     * @param name  The name of child
     * @param value The value of the node.
     */
    public void addSimpleChildValue(String name, int value) {
        childrens.add(new Node(name, Integer.toString(value)));
    }

    /**
     * Add a simple child with a long value.
     *
     * @param name  The name of child
     * @param value The value of the node.
     */
    public void addSimpleChildValue(String name, long value) {
        childrens.add(new Node(name, Long.toString(value)));
    }

    /**
     * Return the text of the node.
     *
     * @return The text of the node.
     */
    public String getText() {
        return text;
    }

    /**
     * Return the int value of the text of the node.
     *
     * @return The int value of the text of the node.
     *
     * @throws NumberFormatException If the text of the child cannot be parsed to int.
     */
    public int getInt() {
        return Integer.parseInt(text);
    }

    /**
     * Set the text of the node.
     *
     * @param text The text of the node.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Return the attributes of the node.
     *
     * @return A List containing all the attributes.
     */
    public Iterable<NodeAttribute> getAttributes() {
        return attributes;
    }

    /**
     * Add an attribute to the node.
     *
     * @param attribute The attribute to add.
     */
    public void addAttribute(NodeAttribute attribute) {
        attributes.add(attribute);
    }

    /**
     * Set the attributes of the node.
     *
     * @param attributes A List containing all the attributes.
     */
    public void setAttributes(Collection<NodeAttribute> attributes) {
        this.attributes = CollectionUtils.copyOf(attributes);
    }

    /**
     * Indicate if the node has children or not.
     *
     * @return true if the node has children else false.
     */
    public boolean hasChildren() {
        return childrens != null && !childrens.isEmpty();
    }

    /**
     * Indicate if the node has attribute or not.
     *
     * @return true if the node has attribute else false.
     */
    public boolean hasAttribute() {
        return attributes != null && !attributes.isEmpty();
    }

    /**
     * Set an attribute.
     *
     * @param key   The key of the attribute.
     * @param value The value of the attribute.
     */
    public void setAttribute(String key, String value) {
        NodeAttribute attribute = new NodeAttribute(key, value);

        attributes.add(attribute);
    }

    /**
     * Return the attribute value.
     *
     * @param key The name of the attribute.
     *
     * @return The value of the attribute or null if the attribute doesn't exist.
     */
    public String getAttributeValue(String key) {
        String value = null;

        for (NodeAttribute attribute : attributes) {
            if (attribute.getKey().equals(key)) {
                value = attribute.getValue();
                break;
            }
        }

        return value;
    }

    /**
     * Return the integer attribute value.
     *
     * @param key The name of the attribute.
     *
     * @return The int value of the attribute or 0 if the attribute doesn't exist.
     */
    public int getIntAttributeValue(String key) {
        String value = getAttributeValue(key);

        if (StringUtils.isNotEmpty(value)) {
            return Integer.parseInt(value);
        }

        return 0;
    }

    /**
     * Return the String value of the child text.
     *
     * @param name The name of the child.
     *
     * @return The String value of the child text.
     */
    public String getChildValue(String name) {
        for (Node child : childrens) {
            if (name.equals(child.name)) {
                return child.text;
            }
        }

        return null;
    }

    /**
     * Return the int value of the child text.
     *
     * @param name The name of the child.
     *
     * @return The int value of the child text.
     *
     * @throws IllegalStateException If there is no child of this name
     * @throws NumberFormatException If the child value is not a int
     */
    public int getChildIntValue(String name) {
        String value = getChildValue(name);

        return value == null ? 0 : Integer.parseInt(value);
    }

    /**
     * Return the Long value of the child text.
     *
     * @param name The name of the child.
     *
     * @return The Long value of the child text.
     *
     * @throws IllegalStateException If there is no child of this name
     * @throws NumberFormatException If the child value is not a long
     */
    public long getChildLongValue(String name) {
        String value = getChildValue(name);

        if (value == null) {
            throw new IllegalStateException("There is no child of this name");
        }

        return Long.parseLong(value);
    }
}
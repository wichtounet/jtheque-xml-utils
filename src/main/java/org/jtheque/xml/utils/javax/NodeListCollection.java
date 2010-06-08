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

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

class NodeListCollection implements Collection<Node> {
    private NodeList nodeList;

    NodeListCollection(NodeList nodeList) {
        super();

        this.nodeList = nodeList;
    }

    @Override
    public int size() {
        return nodeList.getLength();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Contains() is not supported");
    }

    @Override
    public Iterator<Node> iterator() {
        return new NodeListCollectionIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];

        for (int i = 0; i < size(); i++) {
            array[i] = nodeList.item(i);
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        T[] array = ts.length >= size() ? ts : (T[]) Array.newInstance(ts.getClass(), size());

        for (int i = 0; i < size(); i++) {
            array[i] = (T) nodeList.item(i);
        }

        return array;
    }

    @Override
    public boolean add(Node node) {
        throw new UnsupportedOperationException("Modification is not supported");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Modification is not supported");
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        throw new UnsupportedOperationException("Contains() is not supported");
    }

    @Override
    public boolean addAll(Collection<? extends Node> nodes) {
        throw new UnsupportedOperationException("Modification is not supported");
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        throw new UnsupportedOperationException("Modification is not supported");
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        throw new UnsupportedOperationException("Modification is not supported");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Modification is not supported");
    }

    private class NodeListCollectionIterator implements Iterator<Node> {
        private int current;

        @Override
        public boolean hasNext() {
            return current + 1 < nodeList.getLength();
        }

        @Override
        public Node next() {
            current++;

            return nodeList.item(current);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Modification is not supported");
        }
    }
}

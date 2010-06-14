package org.jtheque.xml.utils;

import org.jtheque.xml.utils.javax.JavaxFactory;
import org.jtheque.xml.utils.jdom.JDOMFactory;
import org.jtheque.xml.utils.jdom.JDOMXMLWriter;

import org.jdom.Element;
import org.w3c.dom.Node;

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
 * XML Utils base class. This class provide methods to access factories.
 *
 * @author Baptiste Wicht
 */
public class XML {
    private static final XMLFactory<Element, JDOMXMLWriter> JDOM_FACTORY = new JDOMFactory();
    private static final XMLFactory<Node, IXMLWriter<Node>> JAVA_FACTORY = new JavaxFactory();

    /**
     * Utility class, not instantiable.
     */
    private XML() {
        super();
    }

    /**
     * Return a JDOM Factory.
     *
     * @return A JDOM Factory.
     */
    public static XMLFactory<Element, JDOMXMLWriter> newJDOmFactory() {
        return JDOM_FACTORY;
    }

    /**
     * Return a Java Factory.
     *
     * @return A Java Factory.
     */
    public static XMLFactory<Node, IXMLWriter<Node>> newJavaFactory() {
        return JAVA_FACTORY;
    }
}
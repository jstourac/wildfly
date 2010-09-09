/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.model.base.util;

import java.io.Reader;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.jboss.as.model.ParseResult;
import org.jboss.staxmapper.XMLElementReader;
import org.jboss.staxmapper.XMLExtendedStreamReader;
import org.jboss.staxmapper.XMLMapper;

/**
 * A parser which can be sent in to {@link XMLMapper#registerRootElement(QName, XMLElementReader)}
 * for {@link MockElementRoot} elements.
 *
 * @author Brian Stansberry
 */
public final class MockRootElementParser implements XMLElementReader<ParseResult<MockRootElement>>, XMLStreamConstants {

    private MockRootElementParser() {
    }

    private static final MockRootElementParser INSTANCE = new MockRootElementParser();

    public static void registerXMLElementReaders(XMLMapper mapper, String namespace) {
        mapper.registerRootElement(new QName(namespace, MockRootElement.ELEMENT_NAME), INSTANCE);
    }

    public static MockRootElement parseRootElement(XMLMapper mapper, Reader content) throws XMLStreamException {
        XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(content);
        ParseResult<MockRootElement> parseResult = new ParseResult<MockRootElement>();
        mapper.parseDocument(parseResult, xmlReader);
        return parseResult.getResult();
    }

    /**
     * Get the instance.
     *
     * @return the instance
     */
    public static MockRootElementParser getInstance() {
        return INSTANCE;
    }

    /** {@inheritDoc} */
    public void readElement(final XMLExtendedStreamReader reader, final ParseResult<MockRootElement> value) throws XMLStreamException {
        value.setResult(new MockRootElement(reader));
    }
}

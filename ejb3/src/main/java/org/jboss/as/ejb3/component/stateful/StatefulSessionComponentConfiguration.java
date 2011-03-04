/*
 * JBoss, Home of Professional Open Source.
 * Copyright (c) 2011, Red Hat, Inc., and individual contributors
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
package org.jboss.as.ejb3.component.stateful;

import org.jboss.as.ejb3.component.EJBComponentConfiguration;
import org.jboss.as.ejb3.component.EJBComponentDescription;
import org.jboss.as.ejb3.component.session.AbstractSessionComponentFactory;
import org.jboss.invocation.ImmediateInterceptorFactory;

/**
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class StatefulSessionComponentConfiguration extends EJBComponentConfiguration {
    public StatefulSessionComponentConfiguration(final EJBComponentDescription description) {
        this(description, new StatefulSessionComponentFactory());
    }

    /**
     * Construct a new instance.
     *
     * @param description       the original component description
     * @param componentFactory  the component factory to use to create the actual component
     */
    protected StatefulSessionComponentConfiguration(final EJBComponentDescription description, final AbstractSessionComponentFactory componentFactory) {
        super(description, componentFactory);

        addComponentSystemInterceptorFactory(new ImmediateInterceptorFactory(new ComponentInstanceInterceptor()));
    }
}

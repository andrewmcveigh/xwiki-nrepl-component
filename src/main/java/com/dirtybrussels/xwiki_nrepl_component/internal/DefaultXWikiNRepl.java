/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
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
package com.dirtybrussels.xwiki_nrepl_component.internal;

import java.util.HashMap;

import com.dirtybrussels.xwiki_nrepl_component.IXWikiNRepl;

import org.xwiki.component.annotation.Component;
import org.xwiki.context.Execution;
import org.xwiki.component.manager.ComponentManager;
import org.xwiki.component.manager.ComponentManagerInitializer;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import com.dirtybrussels.xwiki_nrepl_component.XWikiNReplComponent;

/**
 * Implementation of <tt>XWiki nRepl</tt> component.
 */
@Component
@Singleton
public class DefaultXWikiNRepl implements IXWikiNRepl
{
    @Inject
    private ComponentManager componentManager;

    @Inject
    private Execution execution;

    @Override
    public String cljHello()
    {
        return new XWikiNReplComponent().hello(", [from Java]");
    }

    @Override
    public String startRepl(HashMap seed)
    {
        XWikiNReplComponent s = new XWikiNReplComponent();
        s.inject("component-manager", componentManager);
        s.inject("execution", execution);
        if (seed != null) s.merge(seed);
        return s.startRepl();
    }
}


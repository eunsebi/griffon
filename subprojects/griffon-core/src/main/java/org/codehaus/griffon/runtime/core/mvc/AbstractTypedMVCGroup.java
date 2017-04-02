/*
 * Copyright 2008-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.griffon.runtime.core.mvc;

import griffon.core.artifact.GriffonController;
import griffon.core.artifact.GriffonModel;
import griffon.core.artifact.GriffonView;
import griffon.core.mvc.MVCGroup;
import griffon.core.mvc.TypedMVCGroup;

import javax.annotation.Nonnull;

/**
 * @author Andres Almiray
 * @since 2.11.0
 */
public abstract class AbstractTypedMVCGroup<M extends GriffonModel, V extends GriffonView, C extends GriffonController> extends DelegatingMVCGroup implements TypedMVCGroup<M, V, C> {
    public AbstractTypedMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }

    @Nonnull
    @Override
    public M model() {
        return (M) delegate().getModel();
    }

    @Nonnull
    @Override
    public V view() {
        return (V) delegate().getView();
    }

    @Nonnull
    @Override
    public C controller() {
        return (C) delegate().getController();
    }
}

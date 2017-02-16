/*
 * Copyright 2017 Carnegie Mellon University Silicon Valley
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

//
// Decompiled by Procyon v0.5.30
// 

package sampling.engagement1.tc3.sort;

import java.util.Comparator;

public class DefaultComparator<T extends Comparable<? super T>> implements Comparator<T>
{
    public static final DefaultComparator<String> STRING;
    
    @Override
    public int compare(final T object1, final T object2) {
        return object1.compareTo((T)object2);
    }
    
    static {
        STRING = new DefaultComparator<String>();
    }
}
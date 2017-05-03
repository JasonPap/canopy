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

package edu.cmu.sv.isstac.sampling.exploration.cache;

import edu.cmu.sv.isstac.sampling.exploration.Trie;
import gov.nasa.jpf.vm.VM;

/**
 * @author Kasper Luckow
 */
public class TrieCache implements StateCache {

  private Trie trie = new Trie();
  private int hits;
  private int misses;

  @Override
  public void add(VM vm) {
    trie.setFlag(vm.getPath(), true);
  }

  @Override
  public boolean contains(VM vm) {
    boolean hit = trie.isFlagSet(vm.getPath());
    if(hit)
      hits++;
    else
      misses++;
    return hit;
  }
}

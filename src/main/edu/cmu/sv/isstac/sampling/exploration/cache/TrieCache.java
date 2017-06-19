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
import edu.cmu.sv.isstac.sampling.util.JPFUtil;
import gov.nasa.jpf.vm.VM;

/**
 * @author Kasper Luckow
 */
public class TrieCache implements StateCache {

  private Trie trie = new Trie();
  private int hits;
  private int misses;

  @Override
  public void addState(VM vm) {
    trie.setFlag(vm.getPath(), true);
  }

  @Override
  public boolean isStateCached(VM vm) {
    boolean hit = false;
    Trie.TrieNode node = trie.getNode(vm.getPath());
    if(node != null) {
      int currentChoice = JPFUtil.getCurrentChoiceOfCG(vm.getChoiceGenerator());
      if(node.getNext()[currentChoice] != null) {
        hit = true;
      }
    }

    // Keep stats
    if(hit)
      hits++;
    else
      misses++;
    return hit;
  }

  @Override
  public boolean supportsPCOptimization() {
    //will fail on at least lawdb if pc optimization is set to true
    return false;
  }
}

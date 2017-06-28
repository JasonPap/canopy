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

import gov.nasa.jpf.vm.VM;

/**
 * @author Kasper Luckow
 **/
public class NoCache implements StateCache {

  @Override
  public void addState(VM vm) {

  }

  @Override
  public boolean isStateCached(VM vm) {
    return false;
  }

  @Override
  public boolean supportsPCOptimization() {
    return true;
  }
}
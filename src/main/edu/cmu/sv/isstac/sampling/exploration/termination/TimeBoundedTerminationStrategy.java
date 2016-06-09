package edu.cmu.sv.isstac.sampling.exploration.termination;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import edu.cmu.sv.isstac.sampling.SamplingResult;
import edu.cmu.sv.isstac.sampling.mcts.TerminationStrategy;
import edu.cmu.sv.isstac.sampling.structure.Node;
import gov.nasa.jpf.vm.VM;

/**
 * @author Kasper Luckow
 *
 */
public class TimeBoundedTerminationStrategy implements TerminationStrategy {

  private final Stopwatch stopwatch;
  private final TimeUnit timeUnit;
  private final long timeBound;
  
  public TimeBoundedTerminationStrategy(long timeBound, TimeUnit unit) {
    this.timeUnit = unit;
    this.timeBound = timeBound;
    this.stopwatch = new Stopwatch();
    
    this.stopwatch.start();
  }
  
  @Override
  public boolean terminate(VM vm, Node root, SamplingResult currentResult) {
    return stopwatch.elapsedTime(timeUnit) >= timeBound;
  }
}

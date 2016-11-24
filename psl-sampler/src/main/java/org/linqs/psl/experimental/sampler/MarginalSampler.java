/*
 * This file is part of the PSL software.
 * Copyright 2011-2015 University of Maryland
 * Copyright 2013-2015 The Regents of the University of California
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
package org.linqs.psl.utils.sampler;

import java.util.HashMap;
import java.util.Map;

import org.linqs.psl.model.rule.GroundRule;
import org.linqs.psl.reasoner.function.AtomFunctionVariable;

import de.mathnbits.statistics.DoubleDist;

public class MarginalSampler extends LinearSampler {

	private transient Map<AtomFunctionVariable,DoubleDist> samples;
	
	public MarginalSampler() {
		this(defaultMaxNoSteps,defaultSignificantDigits);
	}
	
	public MarginalSampler(int maxNoSteps) {
		this(maxNoSteps,defaultSignificantDigits);
	}
 	
	public MarginalSampler(int maxNoSteps, int significantDigits) {
		super(maxNoSteps, significantDigits);
		samples = new HashMap<AtomFunctionVariable,DoubleDist>();
	}
	
	public DoubleDist getDistribution(AtomFunctionVariable atomvar) {
		return samples.get(atomvar);
	}
	
	public Map<AtomFunctionVariable,DoubleDist> getDistributions() {
		return samples;
	}
	
	@Override
	protected void processNewDimension(AtomFunctionVariable var, int index) {
		DoubleDist newdist = new DoubleDist();
		if (getNoSamples()>0) newdist.incBy(0.0, getNoSamples());
		samples.put(var, newdist);
	}
	
	@Override
	protected void processSampledPoint(Iterable<GroundRule> groundKernels) {
		for (Map.Entry<AtomFunctionVariable, DoubleDist> e : samples.entrySet())
			e.getValue().inc(e.getKey().getValue());
	}
	
}

/*
 * This file is part of the PSL software.
 * Copyright 2011-2015 University of Maryland
 * Copyright 2013-2017 The Regents of the University of California
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
package org.linqs.psl.experimental.optimizer.conic.ipm.solver;

import org.linqs.psl.experimental.optimizer.conic.program.ConicProgram;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.impl.SparseCCDoubleMatrix2D;

/**
 * Solves the systems of normal equations that arise in interior-point methods.
 * 
 * @author Stephen Bach <bach@cs.umd.edu>
 */
public interface NormalSystemSolver {
	public void setConicProgram(ConicProgram program);
	
	public void setA(SparseCCDoubleMatrix2D A);
	
	public void solve(DoubleMatrix1D b);
}

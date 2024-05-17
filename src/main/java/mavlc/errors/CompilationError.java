/*******************************************************************************
 * Copyright (c) 2016-2019 Embedded Systems and Applications Group
 * Department of Computer Science, Technische Universitaet Darmstadt,
 * Hochschulstr. 10, 64289 Darmstadt, Germany.
 *
 * All rights reserved.
 *
 * This software is provided free for educational use only.
 * It may not be used for commercial purposes without the
 * prior written permission of the authors.
 ******************************************************************************/
package mavlc.errors;

/**
 * Abstract super type of all errors thrown by the mavlc compiler.
 */
public abstract class CompilationError extends RuntimeException {
	
	private static final long serialVersionUID = 525255578840927013L;
	
	protected String message;
	
	@Override
	public String getLocalizedMessage() {
		return message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return message;
	}
}

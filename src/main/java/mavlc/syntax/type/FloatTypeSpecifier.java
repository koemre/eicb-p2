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
package mavlc.syntax.type;

import mavlc.syntax.AstNodeVisitor;
import mavlc.syntax.SourceLocation;
import mavlc.type.FloatType;

public class FloatTypeSpecifier extends TypeSpecifier<FloatType> {
	/** @param sourceLocation Location of the node within the containing source file. */
	public FloatTypeSpecifier(SourceLocation sourceLocation) {
		super(sourceLocation, FloatType.class, FloatType.instance);
	}
	
	@Override public <RetTy, ArgTy> RetTy accept(AstNodeVisitor<? extends RetTy, ArgTy> visitor, ArgTy obj) {
		return visitor.visitFloatTypeSpecifier(this, obj);
	}
}

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
package mavlc.syntax.expression;

import mavlc.syntax.AstNodeVisitor;
import mavlc.syntax.SourceLocation;

/**
 * AST node representing a exponentiation of base ^ power.
 */
public class Exponentiation extends BinaryExpression {
	
	/**
	 * @param sourceLocation Location of the node within the containing source file.
	 * @param base base operand of the exponentiation.
	 * @param power power operand of the exponentiation.
	 */
	public Exponentiation(SourceLocation sourceLocation, Expression base, Expression power) {
		super(sourceLocation, base, power);
	}
	
	@Override
	public <RetTy, ArgTy> RetTy accept(AstNodeVisitor<? extends RetTy, ArgTy> visitor, ArgTy obj) {
		return visitor.visitExponentiation(this, obj);
	}
}

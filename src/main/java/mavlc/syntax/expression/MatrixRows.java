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
 * AST node representation of the primitive ".rows"-expression for matrices.
 * Yields the number of rows in the underlying matrix.
 */
public class MatrixRows extends UnaryExpression {
	
	/**
	 * @param sourceLocation Location of the node within the containing source file.
	 * @param operand The underlying matrix.
	 */
	public MatrixRows(SourceLocation sourceLocation, Expression operand) {
		super(sourceLocation, operand);
	}
	
	@Override
	public <RetTy, ArgTy> RetTy accept(AstNodeVisitor<? extends RetTy, ArgTy> visitor, ArgTy obj) {
		return visitor.visitMatrixRows(this, obj);
	}
}

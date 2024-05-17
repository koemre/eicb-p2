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

import java.util.List;
import java.util.Objects;

/**
 * AST node representing the construction of a record from
 * the underlying values.
 */
public class RecordInit extends StructureInit {
	
	public final String typeName;
	
	/**
	 * @param sourceLocation Location of the node within the containing source file.
	 * @param typeName The name of the record type
	 * @param elements A list of expressions representing the record elements.
	 */
	public RecordInit(SourceLocation sourceLocation, String typeName, List<Expression> elements) {
		super(sourceLocation, elements);
		this.typeName = typeName;
	}
	
	@Override
	public <RetTy, ArgTy> RetTy accept(AstNodeVisitor<? extends RetTy, ArgTy> visitor, ArgTy obj) {
		return visitor.visitRecordInit(this, obj);
	}
	
	@Override public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		if(!super.equals(o)) return false;
		RecordInit that = (RecordInit) o;
		return Objects.equals(typeName, that.typeName);
	}
	
	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), typeName);
	}
}

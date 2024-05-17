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
package mavlc.context_analysis;

import mavlc.errors.NonConstantExpressionError;
import mavlc.syntax.AstNode;
import mavlc.syntax.AstNodeBaseVisitor;
import mavlc.syntax.expression.*;

/* TODO enter group information
 *
 * EiCB group number: ...
 * Names and matriculation numbers of all group members:
 * ...
 */

public class ConstantExpressionEvaluator extends AstNodeBaseVisitor<Integer, Void> {
    @Override
    protected Integer defaultOperation(AstNode node, Void obj) {
        if (node instanceof Expression) {
            throw new NonConstantExpressionError((Expression) node);
        } else {
            throw new RuntimeException("Internal compiler error: should not try to constant-evaluate non-expressions");
        }
    }

    @Override
    public Integer visitIntValue(IntValue intValue, Void __) {
        return intValue.value;
    }

    // TODO implement (exercise 2.3)

    public Integer visitExpression(Expression expr, Void __) {
        // a constant expression is made of integer-literals, parentheses and arithmetic operators
        // in MAVL they are always of type int

        if (expr instanceof Addition) return visitAddition((Addition) expr, __);
        if (expr instanceof Subtraction) return visitSubtraction((Subtraction) expr, __);
        if (expr instanceof UnaryMinus) return visitUnaryMinus((UnaryMinus) expr, __);
        if (expr instanceof Multiplication) return visitMultiplication((Multiplication) expr, __);
        if (expr instanceof Division) return visitDivision((Division) expr, __);
        if (expr instanceof Exponentiation) return visitExponentiation((Exponentiation) expr, __);
        if (expr instanceof IntValue) return visitIntValue((IntValue) expr, __);
            // all others are not allowed for constant expressions
            // look at mavlc.parsing.Parser.java -> parseExpr()
            // else defaultOperation(expr, __);	// throw error
        else throw new NonConstantExpressionError(expr); // throw error
    }

    public Integer visitAddition(Addition expr, Void __) {
        // return leftOperand + rightOperand
        return visitExpression(expr.leftOperand, null) + visitExpression(expr.rightOperand, null);
    }

    public Integer visitSubtraction(Subtraction expr, Void __) {
        // return leftOperand - rightOperand
        return visitExpression(expr.leftOperand, null) - visitExpression(expr.rightOperand, null);
    }

    public Integer visitUnaryMinus(UnaryMinus expr, Void __) {
        // return -operand
        return -visitExpression(expr.operand, null);
    }

    public Integer visitMultiplication(Multiplication expr, Void __) {
        // return leftOperand * rightOperand
        return visitExpression(expr.leftOperand, null) * visitExpression(expr.rightOperand, null);
    }

    public Integer visitDivision(Division expr, Void __) {
        // return leftOperand / rightOperand
        return visitExpression(expr.leftOperand, null) / visitExpression(expr.rightOperand, null);
    }

    public Integer visitExponentiation(Exponentiation expr, Void __) {
        // return leftOperand ^ rightOperand
        return (int) Math.pow(visitExpression(expr.leftOperand, null), visitExpression(expr.rightOperand, null));
    }
}

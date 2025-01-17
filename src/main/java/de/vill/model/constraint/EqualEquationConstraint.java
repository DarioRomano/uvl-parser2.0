package de.vill.model.constraint;

import de.vill.model.expression.Expression;

import java.util.Arrays;
import java.util.List;

public class EqualEquationConstraint extends ExpressionConstraint {

    public EqualEquationConstraint(Expression left, Expression right) {
        super(left, right, "==");
    }

    @Override
    public List<Constraint> getConstraintSubParts() {
        return Arrays.asList();
    }

}

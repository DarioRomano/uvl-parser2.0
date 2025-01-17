package de.vill.model.expression;

import de.vill.model.Attribute;
import de.vill.model.Feature;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static de.vill.util.Util.addNecessaryQuotes;

public class LiteralExpression extends Expression {
    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    private String attributeName;

    public Attribute getAttribute() {
        return getFeature().getAttributes().get(attributeName);
    }

    private Feature feature;

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public LiteralExpression(String content) {
        this.attributeName = content;
    }

    @Override
    public String toString() {
        return toString(true, "");
    }

    @Override
    public String toString(boolean withSubmodels, String currentAlias) {
        if (getFeature() == null) {
            return addNecessaryQuotes(getAttributeName());
        }
        if (withSubmodels) {
            return addNecessaryQuotes(getFeature().getFullReference() + "." + getAttributeName());
        }
        return addNecessaryQuotes(feature.getReferenceFromSpecificSubmodel(currentAlias) + "." + getAttributeName());
    }

    @Override
    public List<Expression> getExpressionSubParts() {
        return Arrays.asList();
    }

    @Override
    public void replaceExpressionSubPart(Expression oldSubExpression, Expression newSubExpression) {

    }

    @Override
    public double evaluate(Set<Feature> selectedFeatures) {
        Object attributeValue = feature.getAttributes().get(attributeName).getValue();
        if (attributeValue instanceof Integer) {
            return ((Integer) attributeValue).doubleValue();
        }
        if (attributeValue instanceof Long) {
            return ((Long) attributeValue).doubleValue();
        }
        return (double) attributeValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(attributeName);
        return result;
    }

    @Override
    public int hashCode(int level) {
        return 31 * level + (attributeName == null ? 0 : attributeName.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LiteralExpression other = (LiteralExpression) obj;
        return Objects.equals(attributeName, other.attributeName);
    }

}

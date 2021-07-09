package edu.ccac.courses.cit130.assignments.first.conversion.unit;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 08, 2021
 */
public interface IConversionUnitType {
    ConversionUnitParent parentType();
    float convertUnit(float value, ConversionUnitType type);
}

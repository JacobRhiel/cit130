package edu.ccac.courses.cit130.assignments.first.conversion.unit;

/**
 * Represents a {@link ConversionUnitType} defined by its {@link ConversionUnitParent} type.
 * This enum represents tightly scoped conversions of units that match the {@link ConversionUnitParent} type.
 *
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 08, 2021
 */
public enum ConversionUnitType implements IConversionUnitType {

    /* Begin ConversionUnitParent.LENGTH options */
    INCHES(ConversionUnitParent.LENGTH) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case CENTIMETERS -> value * 2.54f;
                case METERS -> value / 39.37f;
                case FEET -> value / 12;
                case YARDS -> value / 36;
                case KILOMETERS -> value / 39370;
                default -> value;
            };
        }
    },
    CENTIMETERS(ConversionUnitParent.LENGTH) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case INCHES -> value / 2.54f;
                case METERS -> value / 100;
                case FEET -> value / 30.48f;
                case YARDS -> value / 91.44f;
                case KILOMETERS -> value / 100000;
                default -> value;
            };
        }
    },
    METERS(ConversionUnitParent.LENGTH) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case INCHES -> value * 39.37f;
                case CENTIMETERS -> value * 100;
                case FEET -> value / 3.281f;
                case YARDS -> value / 1.094f;
                case KILOMETERS -> value / 1000;
                default -> value;
            };
        }
    },
    FEET(ConversionUnitParent.LENGTH) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case INCHES -> value * 12;
                case CENTIMETERS -> value * 30.48f;
                case METERS -> value / 3.281f;
                case YARDS -> value / 3;
                case KILOMETERS -> value / 3281;
                case MILES -> value / 5280;
                default -> value;
            };
        }
    },
    YARDS(ConversionUnitParent.LENGTH) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case INCHES -> value * 36;
                case CENTIMETERS -> value * 91.44f;
                case METERS -> value / 1.094f;
                case FEET -> value * 3;
                case KILOMETERS -> value / 1094;
                case MILES -> value / 1760;
                default -> value;
            };
        }
    },
    KILOMETERS(ConversionUnitParent.LENGTH) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case INCHES -> value * 39370;
                case CENTIMETERS -> value * 100000;
                case METERS -> value * 1000;
                case FEET -> value * 3281;
                case YARDS -> value * 1094;
                default -> value;
            };
        }
    },
    MILES(ConversionUnitParent.LENGTH) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case INCHES -> value * 63360;
                case CENTIMETERS -> value * 160934;
                case METERS -> value * 1609;
                case FEET -> value * 5280;
                case YARDS -> value * 1760;
                default -> value;
            };
        }
    },
    /* End ConversionUnitParent.LENGTH options **/

    /* Begin ConversionUnitParent.AREA options */
    SQUARE_YARDS(ConversionUnitParent.AREA) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case SQUARE_INCHES -> value * 1296;
                case SQUARE_METERS -> value / 1.196f;
                case SQUARE_FEET -> value * 9;
                case SQUARE_MILES -> value / 3.098e+6f;//3.098e+6 - 3.098 x 10^6
                default -> value;
            };
        }
    },
    SQUARE_INCHES(ConversionUnitParent.AREA) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case SQUARE_YARDS -> value / 1296;
                case SQUARE_METERS -> value / 1550;
                case SQUARE_FEET -> value / 144;
                case SQUARE_MILES -> value / 4.014e+9f;//4.014e+9 - 4.014 x 10^9
                default -> value;
            };
        }
    },
    SQUARE_METERS(ConversionUnitParent.AREA) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case SQUARE_YARDS -> value * 1.196f;
                case SQUARE_INCHES -> value * 1550;
                case SQUARE_FEET -> value * 10.764f;
                case SQUARE_MILES -> (float)value / 2.59e+6f;//2.59e+6 - 2.59 x 10^6
                default -> value;
            };
        }
    },
    SQUARE_FEET(ConversionUnitParent.AREA) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case SQUARE_YARDS -> value / 9;
                case SQUARE_INCHES -> value * 144;
                case SQUARE_METERS -> value / 10.764f;
                case SQUARE_MILES -> value / 2.788e+7f;//2.788e+7 - 2.788 x 10^7
                default -> value;
            };
        }
    },
    SQUARE_MILES(ConversionUnitParent.AREA) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case SQUARE_YARDS -> value * 3.098e+6f;
                case SQUARE_INCHES -> value * 4.014e+9f;
                case SQUARE_METERS -> value * 2.59e+6f;
                case SQUARE_FEET -> value / 2.788e+7f;//2.788e+7 - 2.788 x 10^7
                default -> value;
            };
        }
    },
    /* End ConversionUnitParent.AREA options **/

    /* Begin ConversionUnitParent.VOLUME options */
    CUBIC_FEET(ConversionUnitParent.VOLUME) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case CUBIC_INCHES -> value * 1728;
                case CUBIC_METERS -> value / 35.315f;
                case CUBIC_YARDS -> value / 27;
                default -> value;
            };
        }
    },
    CUBIC_INCHES(ConversionUnitParent.VOLUME) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case CUBIC_FEET -> value / 1728;
                case CUBIC_METERS -> value / 61024;
                case CUBIC_YARDS -> value / 46656;
                default -> value;
            };
        }
    },
    CUBIC_METERS(ConversionUnitParent.VOLUME) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case CUBIC_INCHES -> value * 61024;
                case CUBIC_FEET -> value * 35.315f;
                case CUBIC_YARDS -> value * 1.308f;
                default -> value;
            };
        }
    },
    CUBIC_YARDS(ConversionUnitParent.VOLUME) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case CUBIC_INCHES -> value * 46656;
                case CUBIC_METERS -> value / 1.308f;
                case CUBIC_FEET -> value * 27;
                default -> value;
            };
        }
    },
    /* End ConversionUnitParent.VOLUME options **/

    /* Begin ConversionUnitParent.CAPACITY options */
    OUNCES(ConversionUnitParent.CAPACITY) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case GRAMS -> value * 28.35f;
                case KILOGRAMS -> value / 35.274f;
                case POUNDS -> value / 16;
                default -> value;
            };
        }
    },
    KILOGRAMS(ConversionUnitParent.CAPACITY) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case GRAMS -> value * 1000;
                case OUNCES -> value * 35.274f;
                case POUNDS -> value * 2.205f;
                default -> value;
            };
        }
    },
    POUNDS(ConversionUnitParent.CAPACITY) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case GRAMS -> value * 454;
                case KILOGRAMS -> value / 2.205f;
                case OUNCES -> value * 16;
                default -> value;
            };
        }
    },
    GRAMS(ConversionUnitParent.CAPACITY) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case POUNDS -> value / 454;
                case KILOGRAMS -> value / 1000;
                case OUNCES -> value / 28.35f;
                default -> value;
            };
        }
    },
    /* End ConversionUnitParent.CAPACITY options **/

    /* Begin ConversionUnitParent.LIQUID options */
    PINTS(ConversionUnitParent.LIQUID) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case LIQUID_OUNCES -> value * 16;
                case CUPS -> value * 2;
                case QUARTS -> value / 2;
                case GALLONS -> value / 8;
                default -> value;
            };
        }
    },
    LIQUID_OUNCES(ConversionUnitParent.LIQUID) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case QUARTS -> value / 32;
                case CUPS -> value / 8;
                case PINTS -> value / 16;
                case GALLONS -> value / 128;
                default -> value;
            };
        }
    },
    CUPS(ConversionUnitParent.LIQUID) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case LIQUID_OUNCES -> value * 8;
                case QUARTS -> value / 4;
                case PINTS -> value / 2;
                case GALLONS -> value / 16;
                default -> value;
            };
        }
    },
    QUARTS(ConversionUnitParent.LIQUID) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case LIQUID_OUNCES -> value * 32;
                case CUPS -> value * 4;
                case PINTS -> value * 2;
                case GALLONS -> value / 4;
                default -> value;
            };
        }
    },
    GALLONS(ConversionUnitParent.LIQUID) {
        @Override
        public float convertUnit(float value, ConversionUnitType type) {
            return switch (type) {
                case LIQUID_OUNCES -> value * 128;
                case CUPS -> value * 16;
                case PINTS -> value * 8;
                case QUARTS -> value * 4;
                default -> value;
            };
        }
    }
    /* End ConversionUnitParent.LIQUID options **/
    ;

    private final ConversionUnitParent parent;

    /**
     * Constructor to create this enum object instance.
     * @param parent The {@link ConversionUnitParent} type of this {@link ConversionUnitType}.
     */
    ConversionUnitType(ConversionUnitParent parent) {
        this.parent = parent;
    }

    @Override
    public ConversionUnitParent parentType() {
        return this.parent;
    }

}

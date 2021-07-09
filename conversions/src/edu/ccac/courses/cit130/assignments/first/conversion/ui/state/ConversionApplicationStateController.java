package edu.ccac.courses.cit130.assignments.first.conversion.ui.state;

import edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.ConversionPanel;
import edu.ccac.courses.cit130.assignments.first.conversion.unit.ConversionUnitParent;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 12, 2021
 */
public class ConversionApplicationStateController {

    private static ConversionUnitParent currentUnit;

    // keeps things nice and in order..
    private static final SortedMap<ConversionUnitParent, ConversionPanel> STATES = new TreeMap<>();

    static {
        ConversionUnitParent[] values = ConversionUnitParent.values();
        currentUnit = values[0];
        for (ConversionUnitParent value : values) {
            // store the panels and their parent to quickly define states.
            STATES.put(value, new ConversionPanel(value, value == values[values.length - 1]));
            // set the first panel to enabled.
            fireStateUpdate(value, value == currentUnit ? PanelState.ENABLED : PanelState.DISABLED);
        }
    }

    public static Collection<ConversionPanel> getConversionPanels() {
        // quicker with binary.
        return STATES.values();
    }

    public static void activateNextConversionPanel() {
        ConversionUnitParent[] keySet = STATES.keySet().toArray(new ConversionUnitParent[0]);
        for(int index = 0; index < STATES.size(); index++ ) {
            ConversionUnitParent key = keySet[index];
            if(key == currentUnit) {
                fireStateUpdate(currentUnit, PanelState.DISABLED);
                if(index + 1 > STATES.size() - 1)
                    return;
                currentUnit = keySet[index + 1];
                fireStateUpdate(currentUnit, PanelState.ENABLED);
                return;
            }
        }
    }

    public static void fireStateUpdate(ConversionUnitParent parent, PanelState state) {
        ConversionPanel panel = STATES.get(parent);
        if(panel.getState() == state)
            return;
        panel.setState(state);
    }

    public static void resetApplicationStates() {
        currentUnit = STATES.firstKey();
        for (ConversionPanel value : STATES.values()) {
            value.resetPanel();
            fireStateUpdate(value.getUnitParent(), value.getUnitParent() == currentUnit ? PanelState.ENABLED : PanelState.DISABLED);
        }
    }

}

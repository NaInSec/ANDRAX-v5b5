package com.onesignal;

import com.onesignal.OSDynamicTriggerController;
import com.onesignal.OSTrigger;
import com.onesignal.OneSignal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class OSTriggerController {
    OSDynamicTriggerController dynamicTriggerController;
    private final ConcurrentHashMap<String, Object> triggers = new ConcurrentHashMap<>();

    OSTriggerController(OSDynamicTriggerController.OSDynamicTriggerControllerObserver oSDynamicTriggerControllerObserver) {
        this.dynamicTriggerController = new OSDynamicTriggerController(oSDynamicTriggerControllerObserver);
    }

    /* access modifiers changed from: package-private */
    public boolean evaluateMessageTriggers(OSInAppMessage oSInAppMessage) {
        if (oSInAppMessage.triggers.size() == 0) {
            return true;
        }
        Iterator<ArrayList<OSTrigger>> it = oSInAppMessage.triggers.iterator();
        while (it.hasNext()) {
            if (evaluateAndTriggers(it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean evaluateAndTriggers(ArrayList<OSTrigger> arrayList) {
        Iterator<OSTrigger> it = arrayList.iterator();
        while (it.hasNext()) {
            if (!evaluateTrigger(it.next())) {
                return false;
            }
        }
        return true;
    }

    private boolean evaluateTrigger(OSTrigger oSTrigger) {
        if (oSTrigger.kind == OSTrigger.OSTriggerKind.UNKNOWN) {
            return false;
        }
        if (oSTrigger.kind != OSTrigger.OSTriggerKind.CUSTOM) {
            return this.dynamicTriggerController.dynamicTriggerShouldFire(oSTrigger);
        }
        OSTrigger.OSTriggerOperator oSTriggerOperator = oSTrigger.operatorType;
        Object obj = this.triggers.get(oSTrigger.property);
        if (obj == null) {
            if (oSTriggerOperator == OSTrigger.OSTriggerOperator.NOT_EXISTS) {
                return true;
            }
            if (oSTriggerOperator != OSTrigger.OSTriggerOperator.NOT_EQUAL_TO || oSTrigger.value == null) {
                return false;
            }
            return true;
        } else if (oSTriggerOperator == OSTrigger.OSTriggerOperator.EXISTS) {
            return true;
        } else {
            if (oSTriggerOperator == OSTrigger.OSTriggerOperator.NOT_EXISTS) {
                return false;
            }
            if (oSTriggerOperator == OSTrigger.OSTriggerOperator.CONTAINS) {
                if (!(obj instanceof Collection) || !((Collection) obj).contains(oSTrigger.value)) {
                    return false;
                }
                return true;
            } else if ((obj instanceof String) && (oSTrigger.value instanceof String) && triggerMatchesStringValue((String) oSTrigger.value, (String) obj, oSTriggerOperator)) {
                return true;
            } else {
                if ((!(oSTrigger.value instanceof Number) || !(obj instanceof Number) || !triggerMatchesNumericValue((Number) oSTrigger.value, (Number) obj, oSTriggerOperator)) && !triggerMatchesFlex(oSTrigger.value, obj, oSTriggerOperator)) {
                    return false;
                }
                return true;
            }
        }
    }

    private boolean triggerMatchesStringValue(String str, String str2, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        int i = AnonymousClass1.$SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[oSTriggerOperator.ordinal()];
        if (i == 1) {
            return str.equals(str2);
        }
        if (i == 2) {
            return !str.equals(str2);
        }
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
        OneSignal.onesignalLog(log_level, "Attempted to use an invalid operator for a string trigger comparison: " + oSTriggerOperator.toString());
        return false;
    }

    private boolean triggerMatchesFlex(Object obj, Object obj2, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        if (obj == null) {
            return false;
        }
        if (oSTriggerOperator.checksEquality()) {
            return triggerMatchesStringValue(obj.toString(), obj2.toString(), oSTriggerOperator);
        }
        if (!(obj2 instanceof String) || !(obj instanceof Number)) {
            return false;
        }
        return triggerMatchesNumericValueFlex((Number) obj, (String) obj2, oSTriggerOperator);
    }

    private boolean triggerMatchesNumericValueFlex(Number number, String str, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        try {
            return triggerMatchesNumericValue(Double.valueOf(number.doubleValue()), Double.valueOf(Double.parseDouble(str)), oSTriggerOperator);
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private boolean triggerMatchesNumericValue(Number number, Number number2, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        double doubleValue = number.doubleValue();
        double doubleValue2 = number2.doubleValue();
        switch (oSTriggerOperator) {
            case EQUAL_TO:
                if (doubleValue2 == doubleValue) {
                    return true;
                }
                return false;
            case NOT_EQUAL_TO:
                if (doubleValue2 != doubleValue) {
                    return true;
                }
                return false;
            case EXISTS:
            case CONTAINS:
            case NOT_EXISTS:
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                OneSignal.onesignalLog(log_level, "Attempted to use an invalid operator with a numeric value: " + oSTriggerOperator.toString());
                return false;
            case LESS_THAN:
                return doubleValue2 < doubleValue;
            case GREATER_THAN:
                return doubleValue2 > doubleValue;
            case LESS_THAN_OR_EQUAL_TO:
                return doubleValue2 < doubleValue || doubleValue2 == doubleValue;
            case GREATER_THAN_OR_EQUAL_TO:
                int i = (doubleValue2 > doubleValue ? 1 : (doubleValue2 == doubleValue ? 0 : -1));
                return i > 0 || i == 0;
            default:
                return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isTriggerOnMessage(OSInAppMessage oSInAppMessage, Collection<String> collection) {
        for (String next : collection) {
            Iterator<ArrayList<OSTrigger>> it = oSInAppMessage.triggers.iterator();
            while (true) {
                if (it.hasNext()) {
                    Iterator it2 = it.next().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (next.equals(((OSTrigger) it2.next()).property)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void addTriggers(Map<String, Object> map) {
        synchronized (this.triggers) {
            for (String next : map.keySet()) {
                this.triggers.put(next, map.get(next));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void removeTriggersForKeys(Collection<String> collection) {
        synchronized (this.triggers) {
            for (String remove : collection) {
                this.triggers.remove(remove);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Object getTriggerValue(String str) {
        synchronized (this.triggers) {
            if (!this.triggers.containsKey(str)) {
                return null;
            }
            Object obj = this.triggers.get(str);
            return obj;
        }
    }
}

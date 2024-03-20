package com.thecrackertechnology.dragonterminal.backend;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import com.thecrackertechnology.dragonterminal.component.colorscheme.NeoColorScheme;
import java.util.Map;
import java.util.Properties;

public final class TerminalColorScheme {
    public static final int[] DEFAULT_COLORS = {ViewCompat.MEASURED_STATE_MASK, -3342336, -16724736, -3289856, -10185235, -3342131, -16724531, -1710619, -8421505, SupportMenu.CATEGORY_MASK, -16711936, InputDeviceCompat.SOURCE_ANY, -10724097, -65281, -16711681, -1, ViewCompat.MEASURED_STATE_MASK, -16777121, -16777081, -16777041, -16777001, -16776961, -16752896, -16752801, -16752761, -16752721, -16752681, -16752641, -16742656, -16742561, -16742521, -16742481, -16742441, -16742401, -16732416, -16732321, -16732281, -16732241, -16732201, -16732161, -16722176, -16722081, -16722041, -16722001, -16721961, -16721921, -16711936, -16711841, -16711801, -16711761, -16711721, -16711681, -10551296, -10551201, -10551161, -10551121, -10551081, -10551041, -10526976, -10526881, -10526841, -10526801, -10526761, -10526721, -10516736, -10516641, -10516601, -10516561, -10516521, -10516481, -10506496, -10506401, -10506361, -10506321, -10506281, -10506241, -10496256, -10496161, -10496121, -10496081, -10496041, -10496001, -10486016, -10485921, -10485881, -10485841, -10485801, -10485761, -7929856, -7929761, -7929721, -7929681, -7929641, -7929601, -7905536, -7905441, -7905401, -7905361, -7905321, -7905281, -7895296, -7895201, -7895161, -7895121, -7895081, -7895041, -7885056, -7884961, -7884921, -7884881, -7884841, -7884801, -7874816, -7874721, -7874681, -7874641, -7874601, -7874561, -7864576, -7864481, -7864441, -7864401, -7864361, -7864321, -5308416, -5308321, -5308281, -5308241, -5308201, -5308161, -5284096, -5284001, -5283961, -5283921, -5283881, -5283841, -5273856, -5273761, -5273721, -5273681, -5273641, -5273601, -5263616, -5263521, -5263481, -5263441, -5263401, -5263361, -5253376, -5253281, -5253241, -5253201, -5253161, -5253121, -5243136, -5243041, -5243001, -5242961, -5242921, -5242881, -2686976, -2686881, -2686841, -2686801, -2686761, -2686721, -2662656, -2662561, -2662521, -2662481, -2662441, -2662401, -2652416, -2652321, -2652281, -2652241, -2652201, -2652161, -2642176, -2642081, -2642041, -2642001, -2641961, -2641921, -2631936, -2631841, -2631801, -2631761, -2631721, -2631681, -2621696, -2621601, -2621561, -2621521, -2621481, -2621441, SupportMenu.CATEGORY_MASK, -65441, -65401, -65361, -65321, -65281, -41216, -41121, -41081, -41041, -41001, -40961, -30976, -30881, -30841, -30801, -30761, -30721, -20736, -20641, -20601, -20561, -20521, -20481, -10496, -10401, -10361, -10321, -10281, -10241, InputDeviceCompat.SOURCE_ANY, -161, -121, -81, -41, -1, -16250872, -15592942, -14935012, -14277082, -13619152, -12961222, -12303292, -11645362, -10987432, -10329502, -9671572, -9013642, -8355712, -7697782, -7039852, -6381922, -5723992, -5066062, -4408132, -3750202, -3092272, -2434342, -1776412, -1118482, -16711936, ViewCompat.MEASURED_STATE_MASK, -16736256};
    public final int[] mDefaultColors = new int[259];

    public TerminalColorScheme() {
        reset();
    }

    private void reset() {
        System.arraycopy(DEFAULT_COLORS, 0, this.mDefaultColors, 0, 259);
    }

    public void updateWith(String str, String str2, String str3, Map<Integer, String> map) {
        Properties properties = new Properties();
        if (str != null) {
            properties.put(NeoColorScheme.COLOR_DEF_FOREGROUND, str);
        }
        if (str2 != null) {
            properties.put(NeoColorScheme.COLOR_DEF_BACKGROUND, str2);
        }
        if (str3 != null) {
            properties.put(NeoColorScheme.COLOR_DEF_CURSOR, str3);
        }
        for (Integer intValue : map.keySet()) {
            int intValue2 = intValue.intValue();
            properties.put(NeoColorScheme.COLOR_PREFIX + intValue2, map.get(Integer.valueOf(intValue2)));
        }
        updateWith(properties);
    }

    public void updateWith(Properties properties) {
        int i;
        reset();
        for (Map.Entry entry : properties.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (str.equals(NeoColorScheme.COLOR_DEF_FOREGROUND)) {
                i = 256;
            } else if (str.equals(NeoColorScheme.COLOR_DEF_BACKGROUND)) {
                i = 257;
            } else if (str.equals(NeoColorScheme.COLOR_DEF_CURSOR)) {
                i = 258;
            } else if (str.startsWith(NeoColorScheme.COLOR_PREFIX)) {
                try {
                    i = Integer.parseInt(str.substring(5));
                } catch (NumberFormatException unused) {
                    throw new IllegalArgumentException("Invalid property: '" + str + "'");
                }
            } else {
                throw new IllegalArgumentException("Invalid property: '" + str + "'");
            }
            int parse = TerminalColors.parse(str2);
            if (parse != 0) {
                this.mDefaultColors[i] = parse;
            } else {
                throw new IllegalArgumentException("Property '" + str + "' has invalid color: '" + str2 + "'");
            }
        }
    }
}

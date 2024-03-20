package com.thecrackertechnology.dragonterminal.backend;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public final class KeyHandler {
    public static final int KEYMOD_ALT = Integer.MIN_VALUE;
    public static final int KEYMOD_CTRL = 1073741824;
    public static final int KEYMOD_SHIFT = 536870912;
    private static final Map<String, Integer> TERMCAP_TO_KEYCODE = new HashMap();

    static {
        TERMCAP_TO_KEYCODE.put("%i", 536870934);
        TERMCAP_TO_KEYCODE.put("#2", 536871034);
        TERMCAP_TO_KEYCODE.put("#4", 536870933);
        TERMCAP_TO_KEYCODE.put("*7", 536871035);
        TERMCAP_TO_KEYCODE.put("k1", 131);
        TERMCAP_TO_KEYCODE.put("k2", 132);
        TERMCAP_TO_KEYCODE.put("k3", Integer.valueOf(SDL_1_3_Keycodes.SDLK_KP_COMMA));
        TERMCAP_TO_KEYCODE.put("k4", Integer.valueOf(SDL_1_3_Keycodes.SDLK_KP_EQUALSAS400));
        TERMCAP_TO_KEYCODE.put("k5", Integer.valueOf(SDL_1_3_Keycodes.SDLK_INTERNATIONAL1));
        TERMCAP_TO_KEYCODE.put("k6", Integer.valueOf(SDL_1_3_Keycodes.SDLK_INTERNATIONAL2));
        TERMCAP_TO_KEYCODE.put("k7", Integer.valueOf(SDL_1_3_Keycodes.SDLK_INTERNATIONAL3));
        TERMCAP_TO_KEYCODE.put("k8", Integer.valueOf(SDL_1_3_Keycodes.SDLK_INTERNATIONAL4));
        TERMCAP_TO_KEYCODE.put("k9", Integer.valueOf(SDL_1_3_Keycodes.SDLK_INTERNATIONAL5));
        TERMCAP_TO_KEYCODE.put("k;", Integer.valueOf(SDL_1_3_Keycodes.SDLK_INTERNATIONAL6));
        TERMCAP_TO_KEYCODE.put("F1", Integer.valueOf(SDL_1_3_Keycodes.SDLK_INTERNATIONAL7));
        TERMCAP_TO_KEYCODE.put("F2", Integer.valueOf(SDL_1_3_Keycodes.SDLK_INTERNATIONAL8));
        TERMCAP_TO_KEYCODE.put("F3", 536871043);
        TERMCAP_TO_KEYCODE.put("F4", 536871044);
        TERMCAP_TO_KEYCODE.put("F5", 536871045);
        TERMCAP_TO_KEYCODE.put("F6", 536871046);
        TERMCAP_TO_KEYCODE.put("F7", 536871047);
        TERMCAP_TO_KEYCODE.put("F8", 536871048);
        TERMCAP_TO_KEYCODE.put("F9", 536871049);
        TERMCAP_TO_KEYCODE.put("FA", 536871050);
        TERMCAP_TO_KEYCODE.put("FB", 536871051);
        TERMCAP_TO_KEYCODE.put("FC", 536871052);
        TERMCAP_TO_KEYCODE.put("FD", 536871053);
        TERMCAP_TO_KEYCODE.put("FE", 536871054);
        TERMCAP_TO_KEYCODE.put("kb", 67);
        TERMCAP_TO_KEYCODE.put("kd", 20);
        TERMCAP_TO_KEYCODE.put("kh", 122);
        TERMCAP_TO_KEYCODE.put("kl", 21);
        TERMCAP_TO_KEYCODE.put("kr", 22);
        TERMCAP_TO_KEYCODE.put("K1", 122);
        TERMCAP_TO_KEYCODE.put("K3", 92);
        Map<String, Integer> map = TERMCAP_TO_KEYCODE;
        Integer valueOf = Integer.valueOf(SDL_1_3_Keycodes.SDLK_CUT);
        map.put("K4", valueOf);
        TERMCAP_TO_KEYCODE.put("K5", 93);
        TERMCAP_TO_KEYCODE.put("ku", 19);
        TERMCAP_TO_KEYCODE.put("kB", 536870973);
        TERMCAP_TO_KEYCODE.put("kD", 112);
        TERMCAP_TO_KEYCODE.put("kDN", 536870932);
        TERMCAP_TO_KEYCODE.put("kF", 536870932);
        TERMCAP_TO_KEYCODE.put("kI", Integer.valueOf(SDL_1_3_Keycodes.SDLK_COPY));
        TERMCAP_TO_KEYCODE.put("kN", 92);
        TERMCAP_TO_KEYCODE.put("kP", 93);
        TERMCAP_TO_KEYCODE.put("kR", 536870931);
        TERMCAP_TO_KEYCODE.put("kUP", 536870931);
        TERMCAP_TO_KEYCODE.put("@7", valueOf);
        TERMCAP_TO_KEYCODE.put("@8", 160);
    }

    static String getCodeFromTermcap(String str, boolean z, boolean z2) {
        Integer num = TERMCAP_TO_KEYCODE.get(str);
        if (num == null) {
            return null;
        }
        int intValue = num.intValue();
        int i = 0;
        if ((intValue & KEYMOD_SHIFT) != 0) {
            intValue &= -536870913;
            i = KEYMOD_SHIFT;
        }
        if ((intValue & KEYMOD_CTRL) != 0) {
            i |= KEYMOD_CTRL;
            intValue &= -1073741825;
        }
        if ((intValue & Integer.MIN_VALUE) != 0) {
            i |= Integer.MIN_VALUE;
            intValue &= Integer.MAX_VALUE;
        }
        return getCode(intValue, i, z, z2);
    }

    public static String getCode(int i, int i2, boolean z, boolean z2) {
        String str = "\u001b";
        if (i != 4) {
            if (i == 61) {
                return (536870912 & i2) == 0 ? "\t" : "\u001b[Z";
            }
            if (i != 62) {
                if (i != 66) {
                    if (i == 67) {
                        if ((i2 & Integer.MIN_VALUE) == 0) {
                            str = "";
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append((i2 & KEYMOD_CTRL) == 0 ? "" : "\b");
                        return sb.toString();
                    } else if (i == 92) {
                        return "\u001b[5~";
                    } else {
                        if (i == 93) {
                            return "\u001b[6~";
                        }
                        if (i != 111) {
                            if (i == 112) {
                                return transformForModifiers("\u001b[3", i2, '~');
                            }
                            switch (i) {
                                case 19:
                                    if (i2 == 0) {
                                        return z ? "\u001bOA" : "\u001b[A";
                                    }
                                    return transformForModifiers("\u001b[1", i2, 'A');
                                case 20:
                                    if (i2 == 0) {
                                        return z ? "\u001bOB" : "\u001b[B";
                                    }
                                    return transformForModifiers("\u001b[1", i2, 'B');
                                case 21:
                                    if (i2 == 0) {
                                        return z ? "\u001bOD" : "\u001b[D";
                                    }
                                    return transformForModifiers("\u001b[1", i2, 'D');
                                case 22:
                                    if (i2 == 0) {
                                        return z ? "\u001bOC" : "\u001b[C";
                                    }
                                    return transformForModifiers("\u001b[1", i2, 'C');
                                case 23:
                                    return StringUtils.CR;
                                default:
                                    switch (i) {
                                        case 120:
                                            return "\u001b[32~";
                                        case 121:
                                            return "\u001b[34~";
                                        case 122:
                                            if (i2 == 0) {
                                                return z ? "\u001bOH" : "\u001b[H";
                                            }
                                            return transformForModifiers("\u001b[1", i2, 'H');
                                        case SDL_1_3_Keycodes.SDLK_CUT:
                                            if (i2 == 0) {
                                                return z ? "\u001bOF" : "\u001b[F";
                                            }
                                            return transformForModifiers("\u001b[1", i2, 'F');
                                        case SDL_1_3_Keycodes.SDLK_COPY:
                                            return transformForModifiers("\u001b[2", i2, '~');
                                        default:
                                            switch (i) {
                                                case 131:
                                                    if (i2 == 0) {
                                                        return "\u001bOP";
                                                    }
                                                    return transformForModifiers("\u001b[1", i2, 'P');
                                                case 132:
                                                    if (i2 == 0) {
                                                        return "\u001bOQ";
                                                    }
                                                    return transformForModifiers("\u001b[1", i2, 'Q');
                                                case SDL_1_3_Keycodes.SDLK_KP_COMMA:
                                                    if (i2 == 0) {
                                                        return "\u001bOR";
                                                    }
                                                    return transformForModifiers("\u001b[1", i2, 'R');
                                                case SDL_1_3_Keycodes.SDLK_KP_EQUALSAS400:
                                                    if (i2 == 0) {
                                                        return "\u001bOS";
                                                    }
                                                    return transformForModifiers("\u001b[1", i2, 'S');
                                                case SDL_1_3_Keycodes.SDLK_INTERNATIONAL1:
                                                    return transformForModifiers("\u001b[15", i2, '~');
                                                case SDL_1_3_Keycodes.SDLK_INTERNATIONAL2:
                                                    return transformForModifiers("\u001b[17", i2, '~');
                                                case SDL_1_3_Keycodes.SDLK_INTERNATIONAL3:
                                                    return transformForModifiers("\u001b[18", i2, '~');
                                                case SDL_1_3_Keycodes.SDLK_INTERNATIONAL4:
                                                    return transformForModifiers("\u001b[19", i2, '~');
                                                case SDL_1_3_Keycodes.SDLK_INTERNATIONAL5:
                                                    return transformForModifiers("\u001b[20", i2, '~');
                                                case SDL_1_3_Keycodes.SDLK_INTERNATIONAL6:
                                                    return transformForModifiers("\u001b[21", i2, '~');
                                                case SDL_1_3_Keycodes.SDLK_INTERNATIONAL7:
                                                    return transformForModifiers("\u001b[23", i2, '~');
                                                case SDL_1_3_Keycodes.SDLK_INTERNATIONAL8:
                                                    return transformForModifiers("\u001b[24", i2, '~');
                                                case SDL_1_3_Keycodes.SDLK_INTERNATIONAL9:
                                                    return "\u001bOP";
                                                case SDL_1_3_Keycodes.SDLK_LANG1:
                                                    if (z2) {
                                                        return transformForModifiers("\u001bO", i2, 'p');
                                                    }
                                                    return "0";
                                                case SDL_1_3_Keycodes.SDLK_LANG2:
                                                    return z2 ? transformForModifiers("\u001bO", i2, 'q') : "1";
                                                case SDL_1_3_Keycodes.SDLK_LANG3:
                                                    return z2 ? transformForModifiers("\u001bO", i2, 'r') : "2";
                                                case SDL_1_3_Keycodes.SDLK_LANG4:
                                                    return z2 ? transformForModifiers("\u001bO", i2, 's') : "3";
                                                case SDL_1_3_Keycodes.SDLK_LANG5:
                                                    return z2 ? transformForModifiers("\u001bO", i2, 't') : "4";
                                                case SDL_1_3_Keycodes.SDLK_LANG6:
                                                    return z2 ? transformForModifiers("\u001bO", i2, 'u') : "5";
                                                case SDL_1_3_Keycodes.SDLK_LANG7:
                                                    return z2 ? transformForModifiers("\u001bO", i2, 'v') : "6";
                                                case SDL_1_3_Keycodes.SDLK_LANG8:
                                                    return z2 ? transformForModifiers("\u001bO", i2, 'w') : "7";
                                                case SDL_1_3_Keycodes.SDLK_LANG9:
                                                    return z2 ? transformForModifiers("\u001bO", i2, 'x') : "8";
                                                case SDL_1_3_Keycodes.SDLK_ALTERASE:
                                                    return z2 ? transformForModifiers("\u001bO", i2, 'y') : "9";
                                                case SDL_1_3_Keycodes.SDLK_SYSREQ:
                                                    if (z2) {
                                                        return transformForModifiers("\u001bO", i2, 'o');
                                                    }
                                                    return "/";
                                                case SDL_1_3_Keycodes.SDLK_CANCEL:
                                                    return z2 ? transformForModifiers("\u001bO", i2, 'j') : "*";
                                                case SDL_1_3_Keycodes.SDLK_CLEAR:
                                                    if (z2) {
                                                        return transformForModifiers("\u001bO", i2, 'm');
                                                    }
                                                    return "-";
                                                case SDL_1_3_Keycodes.SDLK_PRIOR:
                                                    return z2 ? transformForModifiers("\u001bO", i2, 'k') : "+";
                                                case SDL_1_3_Keycodes.SDLK_RETURN2:
                                                    return z2 ? "\u001bOn" : ".";
                                                case SDL_1_3_Keycodes.SDLK_SEPARATOR:
                                                    return ",";
                                                case 160:
                                                    return z2 ? transformForModifiers("\u001bO", i2, 'M') : StringUtils.LF;
                                                case 161:
                                                    if (z2) {
                                                        return transformForModifiers("\u001bO", i2, 'X');
                                                    }
                                                    return "=";
                                                default:
                                                    return null;
                                            }
                                    }
                            }
                        }
                    }
                } else if ((i2 & Integer.MIN_VALUE) == 0) {
                    return StringUtils.CR;
                } else {
                    return "\u001b\r";
                }
            } else if ((i2 & KEYMOD_CTRL) == 0) {
                return null;
            } else {
                return "\u0000";
            }
        }
        return str;
    }

    private static String transformForModifiers(String str, int i, char c) {
        int i2;
        if (i == Integer.MIN_VALUE) {
            i2 = 3;
        } else if (i == -1610612736) {
            i2 = 4;
        } else if (i == -1073741824) {
            i2 = 7;
        } else if (i == -536870912) {
            i2 = 8;
        } else if (i == 536870912) {
            i2 = 2;
        } else if (i == 1073741824) {
            i2 = 5;
        } else if (i != 1610612736) {
            return str + c;
        } else {
            i2 = 6;
        }
        return str + ";" + i2 + c;
    }
}

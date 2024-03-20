package org.apache.commons.lang3.text.translate;

import java.lang.reflect.Array;
import org.apache.commons.lang3.StringUtils;

@Deprecated
public class EntityArrays {
    private static final String[][] APOS_ESCAPE = {new String[]{"'", "&apos;"}};
    private static final String[][] APOS_UNESCAPE = invert(APOS_ESCAPE);
    private static final String[][] BASIC_ESCAPE = {new String[]{"\"", "&quot;"}, new String[]{"&", "&amp;"}, new String[]{"<", "&lt;"}, new String[]{">", "&gt;"}};
    private static final String[][] BASIC_UNESCAPE = invert(BASIC_ESCAPE);
    private static final String[][] HTML40_EXTENDED_ESCAPE;
    private static final String[][] HTML40_EXTENDED_UNESCAPE = invert(HTML40_EXTENDED_ESCAPE);
    private static final String[][] ISO8859_1_ESCAPE = {new String[]{" ", "&nbsp;"}, new String[]{"¡", "&iexcl;"}, new String[]{"¢", "&cent;"}, new String[]{"£", "&pound;"}, new String[]{"¤", "&curren;"}, new String[]{"¥", "&yen;"}, new String[]{"¦", "&brvbar;"}, new String[]{"§", "&sect;"}, new String[]{"¨", "&uml;"}, new String[]{"©", "&copy;"}, new String[]{"ª", "&ordf;"}, new String[]{"«", "&laquo;"}, new String[]{"¬", "&not;"}, new String[]{"­", "&shy;"}, new String[]{"®", "&reg;"}, new String[]{"¯", "&macr;"}, new String[]{"°", "&deg;"}, new String[]{"±", "&plusmn;"}, new String[]{"²", "&sup2;"}, new String[]{"³", "&sup3;"}, new String[]{"´", "&acute;"}, new String[]{"µ", "&micro;"}, new String[]{"¶", "&para;"}, new String[]{"·", "&middot;"}, new String[]{"¸", "&cedil;"}, new String[]{"¹", "&sup1;"}, new String[]{"º", "&ordm;"}, new String[]{"»", "&raquo;"}, new String[]{"¼", "&frac14;"}, new String[]{"½", "&frac12;"}, new String[]{"¾", "&frac34;"}, new String[]{"¿", "&iquest;"}, new String[]{"À", "&Agrave;"}, new String[]{"Á", "&Aacute;"}, new String[]{"Â", "&Acirc;"}, new String[]{"Ã", "&Atilde;"}, new String[]{"Ä", "&Auml;"}, new String[]{"Å", "&Aring;"}, new String[]{"Æ", "&AElig;"}, new String[]{"Ç", "&Ccedil;"}, new String[]{"È", "&Egrave;"}, new String[]{"É", "&Eacute;"}, new String[]{"Ê", "&Ecirc;"}, new String[]{"Ë", "&Euml;"}, new String[]{"Ì", "&Igrave;"}, new String[]{"Í", "&Iacute;"}, new String[]{"Î", "&Icirc;"}, new String[]{"Ï", "&Iuml;"}, new String[]{"Ð", "&ETH;"}, new String[]{"Ñ", "&Ntilde;"}, new String[]{"Ò", "&Ograve;"}, new String[]{"Ó", "&Oacute;"}, new String[]{"Ô", "&Ocirc;"}, new String[]{"Õ", "&Otilde;"}, new String[]{"Ö", "&Ouml;"}, new String[]{"×", "&times;"}, new String[]{"Ø", "&Oslash;"}, new String[]{"Ù", "&Ugrave;"}, new String[]{"Ú", "&Uacute;"}, new String[]{"Û", "&Ucirc;"}, new String[]{"Ü", "&Uuml;"}, new String[]{"Ý", "&Yacute;"}, new String[]{"Þ", "&THORN;"}, new String[]{"ß", "&szlig;"}, new String[]{"à", "&agrave;"}, new String[]{"á", "&aacute;"}, new String[]{"â", "&acirc;"}, new String[]{"ã", "&atilde;"}, new String[]{"ä", "&auml;"}, new String[]{"å", "&aring;"}, new String[]{"æ", "&aelig;"}, new String[]{"ç", "&ccedil;"}, new String[]{"è", "&egrave;"}, new String[]{"é", "&eacute;"}, new String[]{"ê", "&ecirc;"}, new String[]{"ë", "&euml;"}, new String[]{"ì", "&igrave;"}, new String[]{"í", "&iacute;"}, new String[]{"î", "&icirc;"}, new String[]{"ï", "&iuml;"}, new String[]{"ð", "&eth;"}, new String[]{"ñ", "&ntilde;"}, new String[]{"ò", "&ograve;"}, new String[]{"ó", "&oacute;"}, new String[]{"ô", "&ocirc;"}, new String[]{"õ", "&otilde;"}, new String[]{"ö", "&ouml;"}, new String[]{"÷", "&divide;"}, new String[]{"ø", "&oslash;"}, new String[]{"ù", "&ugrave;"}, new String[]{"ú", "&uacute;"}, new String[]{"û", "&ucirc;"}, new String[]{"ü", "&uuml;"}, new String[]{"ý", "&yacute;"}, new String[]{"þ", "&thorn;"}, new String[]{"ÿ", "&yuml;"}};
    private static final String[][] ISO8859_1_UNESCAPE = invert(ISO8859_1_ESCAPE);
    private static final String[][] JAVA_CTRL_CHARS_ESCAPE = {new String[]{"\b", "\\b"}, new String[]{StringUtils.LF, "\\n"}, new String[]{"\t", "\\t"}, new String[]{"\f", "\\f"}, new String[]{StringUtils.CR, "\\r"}};
    private static final String[][] JAVA_CTRL_CHARS_UNESCAPE = invert(JAVA_CTRL_CHARS_ESCAPE);

    public static String[][] ISO8859_1_ESCAPE() {
        return (String[][]) ISO8859_1_ESCAPE.clone();
    }

    static {
        String[][] strArr = new String[SDL_1_3_Keycodes.SDLK_LANG9][];
        strArr[0] = new String[]{"ƒ", "&fnof;"};
        strArr[1] = new String[]{"Α", "&Alpha;"};
        strArr[2] = new String[]{"Β", "&Beta;"};
        strArr[3] = new String[]{"Γ", "&Gamma;"};
        strArr[4] = new String[]{"Δ", "&Delta;"};
        strArr[5] = new String[]{"Ε", "&Epsilon;"};
        strArr[6] = new String[]{"Ζ", "&Zeta;"};
        strArr[7] = new String[]{"Η", "&Eta;"};
        strArr[8] = new String[]{"Θ", "&Theta;"};
        strArr[9] = new String[]{"Ι", "&Iota;"};
        strArr[10] = new String[]{"Κ", "&Kappa;"};
        strArr[11] = new String[]{"Λ", "&Lambda;"};
        strArr[12] = new String[]{"Μ", "&Mu;"};
        strArr[13] = new String[]{"Ν", "&Nu;"};
        strArr[14] = new String[]{"Ξ", "&Xi;"};
        strArr[15] = new String[]{"Ο", "&Omicron;"};
        strArr[16] = new String[]{"Π", "&Pi;"};
        strArr[17] = new String[]{"Ρ", "&Rho;"};
        strArr[18] = new String[]{"Σ", "&Sigma;"};
        strArr[19] = new String[]{"Τ", "&Tau;"};
        strArr[20] = new String[]{"Υ", "&Upsilon;"};
        strArr[21] = new String[]{"Φ", "&Phi;"};
        strArr[22] = new String[]{"Χ", "&Chi;"};
        strArr[23] = new String[]{"Ψ", "&Psi;"};
        strArr[24] = new String[]{"Ω", "&Omega;"};
        strArr[25] = new String[]{"α", "&alpha;"};
        strArr[26] = new String[]{"β", "&beta;"};
        strArr[27] = new String[]{"γ", "&gamma;"};
        strArr[28] = new String[]{"δ", "&delta;"};
        strArr[29] = new String[]{"ε", "&epsilon;"};
        strArr[30] = new String[]{"ζ", "&zeta;"};
        strArr[31] = new String[]{"η", "&eta;"};
        strArr[32] = new String[]{"θ", "&theta;"};
        strArr[33] = new String[]{"ι", "&iota;"};
        strArr[34] = new String[]{"κ", "&kappa;"};
        strArr[35] = new String[]{"λ", "&lambda;"};
        strArr[36] = new String[]{"μ", "&mu;"};
        strArr[37] = new String[]{"ν", "&nu;"};
        strArr[38] = new String[]{"ξ", "&xi;"};
        strArr[39] = new String[]{"ο", "&omicron;"};
        strArr[40] = new String[]{"π", "&pi;"};
        strArr[41] = new String[]{"ρ", "&rho;"};
        strArr[42] = new String[]{"ς", "&sigmaf;"};
        strArr[43] = new String[]{"σ", "&sigma;"};
        strArr[44] = new String[]{"τ", "&tau;"};
        strArr[45] = new String[]{"υ", "&upsilon;"};
        strArr[46] = new String[]{"φ", "&phi;"};
        strArr[47] = new String[]{"χ", "&chi;"};
        strArr[48] = new String[]{"ψ", "&psi;"};
        strArr[49] = new String[]{"ω", "&omega;"};
        strArr[50] = new String[]{"ϑ", "&thetasym;"};
        strArr[51] = new String[]{"ϒ", "&upsih;"};
        strArr[52] = new String[]{"ϖ", "&piv;"};
        strArr[53] = new String[]{"•", "&bull;"};
        strArr[54] = new String[]{"…", "&hellip;"};
        strArr[55] = new String[]{"′", "&prime;"};
        strArr[56] = new String[]{"″", "&Prime;"};
        strArr[57] = new String[]{"‾", "&oline;"};
        strArr[58] = new String[]{"⁄", "&frasl;"};
        strArr[59] = new String[]{"℘", "&weierp;"};
        strArr[60] = new String[]{"ℑ", "&image;"};
        strArr[61] = new String[]{"ℜ", "&real;"};
        strArr[62] = new String[]{"™", "&trade;"};
        strArr[63] = new String[]{"ℵ", "&alefsym;"};
        strArr[64] = new String[]{"←", "&larr;"};
        strArr[65] = new String[]{"↑", "&uarr;"};
        strArr[66] = new String[]{"→", "&rarr;"};
        strArr[67] = new String[]{"↓", "&darr;"};
        strArr[68] = new String[]{"↔", "&harr;"};
        strArr[69] = new String[]{"↵", "&crarr;"};
        strArr[70] = new String[]{"⇐", "&lArr;"};
        strArr[71] = new String[]{"⇑", "&uArr;"};
        strArr[72] = new String[]{"⇒", "&rArr;"};
        strArr[73] = new String[]{"⇓", "&dArr;"};
        strArr[74] = new String[]{"⇔", "&hArr;"};
        strArr[75] = new String[]{"∀", "&forall;"};
        strArr[76] = new String[]{"∂", "&part;"};
        strArr[77] = new String[]{"∃", "&exist;"};
        strArr[78] = new String[]{"∅", "&empty;"};
        strArr[79] = new String[]{"∇", "&nabla;"};
        strArr[80] = new String[]{"∈", "&isin;"};
        strArr[81] = new String[]{"∉", "&notin;"};
        strArr[82] = new String[]{"∋", "&ni;"};
        strArr[83] = new String[]{"∏", "&prod;"};
        strArr[84] = new String[]{"∑", "&sum;"};
        strArr[85] = new String[]{"−", "&minus;"};
        strArr[86] = new String[]{"∗", "&lowast;"};
        strArr[87] = new String[]{"√", "&radic;"};
        strArr[88] = new String[]{"∝", "&prop;"};
        strArr[89] = new String[]{"∞", "&infin;"};
        strArr[90] = new String[]{"∠", "&ang;"};
        strArr[91] = new String[]{"∧", "&and;"};
        strArr[92] = new String[]{"∨", "&or;"};
        strArr[93] = new String[]{"∩", "&cap;"};
        strArr[94] = new String[]{"∪", "&cup;"};
        strArr[95] = new String[]{"∫", "&int;"};
        strArr[96] = new String[]{"∴", "&there4;"};
        strArr[97] = new String[]{"∼", "&sim;"};
        strArr[98] = new String[]{"≅", "&cong;"};
        strArr[99] = new String[]{"≈", "&asymp;"};
        strArr[100] = new String[]{"≠", "&ne;"};
        strArr[101] = new String[]{"≡", "&equiv;"};
        strArr[102] = new String[]{"≤", "&le;"};
        strArr[103] = new String[]{"≥", "&ge;"};
        strArr[104] = new String[]{"⊂", "&sub;"};
        strArr[105] = new String[]{"⊃", "&sup;"};
        strArr[106] = new String[]{"⊄", "&nsub;"};
        strArr[107] = new String[]{"⊆", "&sube;"};
        strArr[108] = new String[]{"⊇", "&supe;"};
        strArr[109] = new String[]{"⊕", "&oplus;"};
        strArr[110] = new String[]{"⊗", "&otimes;"};
        strArr[111] = new String[]{"⊥", "&perp;"};
        strArr[112] = new String[]{"⋅", "&sdot;"};
        strArr[113] = new String[]{"⌈", "&lceil;"};
        strArr[114] = new String[]{"⌉", "&rceil;"};
        strArr[115] = new String[]{"⌊", "&lfloor;"};
        strArr[116] = new String[]{"⌋", "&rfloor;"};
        strArr[117] = new String[]{"〈", "&lang;"};
        strArr[118] = new String[]{"〉", "&rang;"};
        strArr[119] = new String[]{"◊", "&loz;"};
        strArr[120] = new String[]{"♠", "&spades;"};
        strArr[121] = new String[]{"♣", "&clubs;"};
        strArr[122] = new String[]{"♥", "&hearts;"};
        strArr[123] = new String[]{"♦", "&diams;"};
        strArr[124] = new String[]{"Œ", "&OElig;"};
        strArr[125] = new String[]{"œ", "&oelig;"};
        strArr[126] = new String[]{"Š", "&Scaron;"};
        strArr[127] = new String[]{"š", "&scaron;"};
        strArr[128] = new String[]{"Ÿ", "&Yuml;"};
        strArr[129] = new String[]{"ˆ", "&circ;"};
        strArr[130] = new String[]{"˜", "&tilde;"};
        strArr[131] = new String[]{" ", "&ensp;"};
        strArr[132] = new String[]{" ", "&emsp;"};
        strArr[133] = new String[]{" ", "&thinsp;"};
        strArr[134] = new String[]{"‌", "&zwnj;"};
        strArr[135] = new String[]{"‍", "&zwj;"};
        strArr[136] = new String[]{"‎", "&lrm;"};
        strArr[137] = new String[]{"‏", "&rlm;"};
        strArr[138] = new String[]{"–", "&ndash;"};
        strArr[139] = new String[]{"—", "&mdash;"};
        strArr[140] = new String[]{"‘", "&lsquo;"};
        strArr[141] = new String[]{"’", "&rsquo;"};
        strArr[142] = new String[]{"‚", "&sbquo;"};
        strArr[143] = new String[]{"“", "&ldquo;"};
        strArr[144] = new String[]{"”", "&rdquo;"};
        strArr[145] = new String[]{"„", "&bdquo;"};
        strArr[146] = new String[]{"†", "&dagger;"};
        strArr[147] = new String[]{"‡", "&Dagger;"};
        strArr[148] = new String[]{"‰", "&permil;"};
        strArr[149] = new String[]{"‹", "&lsaquo;"};
        strArr[150] = new String[]{"›", "&rsaquo;"};
        strArr[151] = new String[]{"€", "&euro;"};
        HTML40_EXTENDED_ESCAPE = strArr;
    }

    public static String[][] ISO8859_1_UNESCAPE() {
        return (String[][]) ISO8859_1_UNESCAPE.clone();
    }

    public static String[][] HTML40_EXTENDED_ESCAPE() {
        return (String[][]) HTML40_EXTENDED_ESCAPE.clone();
    }

    public static String[][] HTML40_EXTENDED_UNESCAPE() {
        return (String[][]) HTML40_EXTENDED_UNESCAPE.clone();
    }

    public static String[][] BASIC_ESCAPE() {
        return (String[][]) BASIC_ESCAPE.clone();
    }

    public static String[][] BASIC_UNESCAPE() {
        return (String[][]) BASIC_UNESCAPE.clone();
    }

    public static String[][] APOS_ESCAPE() {
        return (String[][]) APOS_ESCAPE.clone();
    }

    public static String[][] APOS_UNESCAPE() {
        return (String[][]) APOS_UNESCAPE.clone();
    }

    public static String[][] JAVA_CTRL_CHARS_ESCAPE() {
        return (String[][]) JAVA_CTRL_CHARS_ESCAPE.clone();
    }

    public static String[][] JAVA_CTRL_CHARS_UNESCAPE() {
        return (String[][]) JAVA_CTRL_CHARS_UNESCAPE.clone();
    }

    public static String[][] invert(String[][] strArr) {
        String[][] strArr2 = (String[][]) Array.newInstance(String.class, new int[]{strArr.length, 2});
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i][0] = strArr[i][1];
            strArr2[i][1] = strArr[i][0];
        }
        return strArr2;
    }
}

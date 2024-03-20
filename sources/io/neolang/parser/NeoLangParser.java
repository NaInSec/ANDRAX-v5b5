package io.neolang.parser;

import io.neolang.ast.NeoLangToken;
import io.neolang.ast.NeoLangTokenType;
import io.neolang.ast.NeoLangTokenValue;
import io.neolang.ast.base.NeoLangAst;
import io.neolang.ast.node.NeoLangArrayNode;
import io.neolang.ast.node.NeoLangAttributeNode;
import io.neolang.ast.node.NeoLangBlockNode;
import io.neolang.ast.node.NeoLangGroupNode;
import io.neolang.ast.node.NeoLangNumberNode;
import io.neolang.ast.node.NeoLangProgramNode;
import io.neolang.ast.node.NeoLangStringNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0002J\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0002J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u0018H\u0002J\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#J\u0018\u0010$\u001a\u0004\u0018\u00010\u001d2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060%H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lio/neolang/parser/NeoLangParser;", "", "()V", "currentPosition", "", "currentToken", "Lio/neolang/ast/NeoLangToken;", "lexer", "Lio/neolang/parser/NeoLangLexer;", "tokens", "", "array", "Lio/neolang/ast/node/NeoLangArrayNode;", "arrayName", "Lio/neolang/ast/node/NeoLangStringNode;", "attribute", "Lio/neolang/ast/node/NeoLangAttributeNode;", "block", "Lio/neolang/ast/node/NeoLangBlockNode;", "attrName", "blockNonArrayElement", "group", "Lio/neolang/ast/node/NeoLangGroupNode;", "match", "", "tokenType", "Lio/neolang/ast/NeoLangTokenType;", "errorThrow", "parse", "Lio/neolang/ast/base/NeoLangAst;", "program", "Lio/neolang/ast/node/NeoLangProgramNode;", "setInputSource", "", "programCode", "", "updateParserStatus", "", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangParser.kt */
public final class NeoLangParser {
    private int currentPosition;
    private NeoLangToken currentToken;
    private final NeoLangLexer lexer = new NeoLangLexer();
    private List<NeoLangToken> tokens = new ArrayList();

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[NeoLangTokenType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[NeoLangTokenType.values().length];

        static {
            $EnumSwitchMapping$0[NeoLangTokenType.ARRAY_START.ordinal()] = 1;
            $EnumSwitchMapping$1[NeoLangTokenType.NUMBER.ordinal()] = 1;
            $EnumSwitchMapping$1[NeoLangTokenType.ID.ordinal()] = 2;
            $EnumSwitchMapping$1[NeoLangTokenType.STRING.ordinal()] = 3;
            $EnumSwitchMapping$1[NeoLangTokenType.BRACKET_START.ordinal()] = 4;
        }
    }

    public final void setInputSource(String str) {
        this.lexer.setInputSource$NeoLang(str);
    }

    public final NeoLangAst parse() {
        NeoLangAst updateParserStatus = updateParserStatus(this.lexer.lex$NeoLang());
        if (updateParserStatus != null) {
            return updateParserStatus;
        }
        throw new ParseException("AST is null");
    }

    private final NeoLangAst updateParserStatus(List<? extends NeoLangToken> list) {
        if (list.isEmpty()) {
            return NeoLangProgramNode.Companion.emptyNode();
        }
        this.tokens.clear();
        this.tokens.addAll(list);
        this.currentPosition = 0;
        this.currentToken = (NeoLangToken) list.get(this.currentPosition);
        return program();
    }

    static /* synthetic */ boolean match$default(NeoLangParser neoLangParser, NeoLangTokenType neoLangTokenType, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return neoLangParser.match(neoLangTokenType, z);
    }

    private final boolean match(NeoLangTokenType neoLangTokenType, boolean z) {
        NeoLangToken neoLangToken = this.currentToken;
        if (neoLangToken == null) {
            throw new InvalidTokenException("Unexpected token: null");
        } else if (neoLangToken.getTokenType() == neoLangTokenType) {
            this.currentPosition++;
            if (this.currentPosition >= this.tokens.size()) {
                this.currentToken = new NeoLangToken(NeoLangTokenType.EOF, NeoLangTokenValue.Companion.getEOF());
            } else {
                this.currentToken = this.tokens.get(this.currentPosition);
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            throw new InvalidTokenException("Unexpected token `" + neoLangToken.getTokenValue() + "' typed " + '`' + neoLangToken.getTokenType() + "' near line " + neoLangToken.getLineNumber() + ", " + "expected " + neoLangTokenType);
        }
    }

    private final NeoLangProgramNode program() {
        NeoLangGroupNode group;
        NeoLangToken neoLangToken = this.currentToken;
        NeoLangGroupNode group2 = group();
        if (group2 == null) {
            return NeoLangProgramNode.Companion.emptyNode();
        }
        List mutableListOf = CollectionsKt.mutableListOf(group2);
        while (true) {
            if ((neoLangToken != null ? neoLangToken.getTokenType() : null) != NeoLangTokenType.EOF && (group = group()) != null) {
                mutableListOf.add(group);
            }
        }
        return new NeoLangProgramNode(mutableListOf);
    }

    private final NeoLangGroupNode group() {
        NeoLangAttributeNode attribute;
        NeoLangToken neoLangToken = this.currentToken;
        if (neoLangToken != null) {
            NeoLangAttributeNode attribute2 = attribute();
            if (attribute2 == null) {
                return null;
            }
            List mutableListOf = CollectionsKt.mutableListOf(attribute2);
            while (neoLangToken.getTokenType() != NeoLangTokenType.EOF && neoLangToken.getTokenType() != NeoLangTokenType.BRACKET_END && neoLangToken.getTokenType() != NeoLangTokenType.ARRAY_END && (attribute = attribute()) != null) {
                mutableListOf.add(attribute);
            }
            Object[] array = mutableListOf.toArray(new NeoLangAttributeNode[0]);
            if (array != null) {
                return new NeoLangGroupNode((NeoLangAttributeNode[]) array);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new InvalidTokenException("Unexpected token: null");
    }

    private final NeoLangAttributeNode attribute() {
        NeoLangToken neoLangToken = this.currentToken;
        if (neoLangToken == null) {
            throw new InvalidTokenException("Unexpected token: null");
        } else if (!match$default(this, NeoLangTokenType.ID, false, 2, (Object) null)) {
            return null;
        } else {
            match(NeoLangTokenType.COLON, true);
            NeoLangStringNode neoLangStringNode = new NeoLangStringNode(neoLangToken);
            NeoLangBlockNode block = block(neoLangStringNode);
            if (block == null) {
                block = NeoLangBlockNode.Companion.emptyNode();
            }
            return new NeoLangAttributeNode(neoLangStringNode, block);
        }
    }

    private final NeoLangArrayNode array(NeoLangStringNode neoLangStringNode) {
        NeoLangBlockNode blockNonArrayElement;
        NeoLangToken neoLangToken = this.currentToken;
        if (neoLangToken != null) {
            NeoLangBlockNode blockNonArrayElement2 = blockNonArrayElement(neoLangStringNode);
            if (blockNonArrayElement2 == null) {
                return null;
            }
            int i = 1;
            List mutableListOf = CollectionsKt.mutableListOf(new NeoLangArrayNode.Companion.ArrayElement(0, blockNonArrayElement2));
            if (match$default(this, NeoLangTokenType.COMMA, false, 2, (Object) null)) {
                while (neoLangToken.getTokenType() != NeoLangTokenType.EOF && neoLangToken.getTokenType() != NeoLangTokenType.ARRAY_END && (blockNonArrayElement = blockNonArrayElement(neoLangStringNode)) != null) {
                    int i2 = i + 1;
                    mutableListOf.add(new NeoLangArrayNode.Companion.ArrayElement(i, blockNonArrayElement));
                    if (!match$default(this, NeoLangTokenType.COMMA, false, 2, (Object) null)) {
                        break;
                    }
                    i = i2;
                }
            }
            Object[] array = mutableListOf.toArray(new NeoLangArrayNode.Companion.ArrayElement[0]);
            if (array != null) {
                return new NeoLangArrayNode(neoLangStringNode, (NeoLangArrayNode.Companion.ArrayElement[]) array);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new InvalidTokenException("Unexpected token: null");
    }

    private final NeoLangBlockNode block(NeoLangStringNode neoLangStringNode) {
        NeoLangBlockNode blockNonArrayElement = blockNonArrayElement(neoLangStringNode);
        if (blockNonArrayElement != null) {
            return blockNonArrayElement;
        }
        NeoLangToken neoLangToken = this.currentToken;
        if (neoLangToken != null) {
            if (WhenMappings.$EnumSwitchMapping$0[neoLangToken.getTokenType().ordinal()] == 1) {
                match(NeoLangTokenType.ARRAY_START, true);
                NeoLangArrayNode array = array(neoLangStringNode);
                match(NeoLangTokenType.ARRAY_END, true);
                return array != null ? new NeoLangBlockNode(array) : NeoLangBlockNode.Companion.emptyNode();
            }
            throw new InvalidTokenException("Unexpected token `" + neoLangToken.getTokenValue() + "' typed `" + neoLangToken.getTokenType() + "' for block");
        }
        throw new InvalidTokenException("Unexpected token: null");
    }

    private final NeoLangBlockNode blockNonArrayElement(NeoLangStringNode neoLangStringNode) {
        NeoLangToken neoLangToken = this.currentToken;
        if (neoLangToken != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[neoLangToken.getTokenType().ordinal()];
            if (i == 1) {
                match(NeoLangTokenType.NUMBER, true);
                return new NeoLangBlockNode(new NeoLangNumberNode(neoLangToken));
            } else if (i == 2) {
                match(NeoLangTokenType.ID, true);
                return new NeoLangBlockNode(new NeoLangStringNode(neoLangToken));
            } else if (i == 3) {
                match(NeoLangTokenType.STRING, true);
                return new NeoLangBlockNode(new NeoLangStringNode(neoLangToken));
            } else if (i != 4) {
                return null;
            } else {
                match(NeoLangTokenType.BRACKET_START, true);
                NeoLangGroupNode group = group();
                match(NeoLangTokenType.BRACKET_END, true);
                return group != null ? new NeoLangBlockNode(group) : NeoLangBlockNode.Companion.emptyNode();
            }
        } else {
            throw new InvalidTokenException("Unexpected token: null");
        }
    }
}

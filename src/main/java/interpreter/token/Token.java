package interpreter.token;

import java.util.HashMap;
import java.util.Map;

public class Token {
    public enum TokenType {
        ILLEGAL("ILLEGAL"),
        EOF("EOF"),
        IDENT("IDENT"),
        INT("INT"),
        ASSIGN("="),
        PLUS("+"),
        MINUS("-"),
        ASTERISK("*"),
        SLASH("/"),
        COMMA(","),
        SEMICOLON(";"),
        LPAREN("("),
        RPAREN(")"),
        LBRACE("{"),
        RBRACE("}"),

        // 예약어
        FUNCTION("FUNCTION"),
        LET("LET");

        private final String literal;

        TokenType(String literal) {
            this.literal = literal;
        }

        public String getLiteral() {
            return literal;
        }
    }

    // Token 클래스 필드
    private TokenType type;
    private String literal;

    // 생성자
    public Token(TokenType type, String literal) {
        this.type = type;
        this.literal = literal;
    }

    // Getter 메서드
    public TokenType getType() {
        return type;
    }

    public String getLiteral() {
        return literal;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", literal='" + literal + '\'' +
                '}';
    }

    private static final Map<String, TokenType> keywords = new HashMap<>();

    static {
        keywords.put("fn", TokenType.FUNCTION);
        keywords.put("let", TokenType.LET);
    }
    public static TokenType lookupIdent(String ident) {
        return keywords.getOrDefault(ident, TokenType.IDENT);

    }
}

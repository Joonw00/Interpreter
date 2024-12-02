package interpreter.lexer;

import interpreter.token.Token;


public class Lexer {
    private String input;
    private int position;
    private int readPosition;
    private char ch;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
        this.readPosition = 0;
        this.ch = '\0';
        readChar();
    }


    public void readChar(){
        if (this.readPosition >= this.input.length()) {
            this.ch = '\0';
        } else {
            this.ch = this.input.charAt(this.readPosition);
        }
        this.position = this.readPosition;
        this.readPosition += 1;
    }
    public char getCh() {
        return this.ch;
    }

    public Token nextToken() {
        Token token = null;

        skipWhitespace();

        switch (this.ch) {
            case '=':
                token = new Token(Token.TokenType.ASSIGN, String.valueOf(this.ch));
                break;
            case ';':
                token = new Token(Token.TokenType.SEMICOLON, String.valueOf(this.ch));
                break;
            case '(':
                token = new Token(Token.TokenType.LPAREN, String.valueOf(this.ch));
                break;
            case ')':
                token = new Token(Token.TokenType.RPAREN, String.valueOf(this.ch));
                break;
            case ',':
                token = new Token(Token.TokenType.COMMA, String.valueOf(this.ch));
                break;
            case '+':
                token = new Token(Token.TokenType.PLUS, String.valueOf(this.ch));
                break;
            case '{':
                token = new Token(Token.TokenType.LBRACE, String.valueOf(this.ch));
                break;
            case '}':
                token = new Token(Token.TokenType.RBRACE, String.valueOf(this.ch));
                break;
            case '\0':
                token = new Token(Token.TokenType.EOF, "");
                break;
            default:
                if (isLetter(this.ch)) {
                    String literal = readIdentifier();
                    Token.TokenType type = Token.lookupIdent(literal);
                    return newToken(type, literal);
                } else if (isDigit(this.ch)) {
                    String literal = readNumber();
                    Token.TokenType type = Token.TokenType.INT;
                    return newToken(type, literal);
                } else {
                    token = newToken(Token.TokenType.ILLEGAL, String.valueOf(this.ch));
                }
                break;
        }
        readChar();
        return token;
    }

    private boolean isLetter(char ch) {
        return Character.isLetter(ch) || ch == '_';
    }

    private String readIdentifier() {
        int start = this.position;
        while (isLetter(this.ch)) {
            readChar();
        }
        return this.input.substring(start, this.position);
    }

    private boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }
    private String readNumber() {
        int start = this.position;
        while (isDigit(this.ch)) {
            readChar();
        }
        return this.input.substring(start, this.position);
    }

    private void skipWhitespace() {
        while (this.ch == ' ' || this.ch == '\t' || this.ch == '\n' || this.ch == '\r') {
            readChar();
        }
    }

    public Token newToken(Token.TokenType tokenType, String literal) {
        return new Token(tokenType, literal);
    }
}

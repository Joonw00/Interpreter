package interpreter.lexer;

import interpreter.token.Token;

public class lexer {
    private String input;
    private int position;
    private int readPosition;
    private char ch;

    public lexer(String input) {
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
        }
        readChar();
        return token;
    }

    public Token newToken(Token.TokenType tokenType, String literal) {
        return new Token(tokenType, literal);
    }
}

package interpreter.lexer;

import interpreter.token.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    @Test
    void readChar() {
        Lexer lexer = new Lexer("abc");
        assertEquals('a', lexer.getCh());
        lexer.readChar();
        assertEquals('b', lexer.getCh());
        lexer.readChar();
        assertEquals('c', lexer.getCh());
        lexer.readChar();
        assertEquals('\0', lexer.getCh());
    }

    @Test
    void getCh() {
        Lexer lexer = new Lexer("hello");
        assertEquals('h', lexer.getCh());
    }

    @Test
    void nextToken() {
        String input = "=+(){},;";
        Lexer lexer = new Lexer(input);

        Token[] expectedTokens = {
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.PLUS, "+"),
                new Token(Token.TokenType.LPAREN, "("),
                new Token(Token.TokenType.RPAREN, ")"),
                new Token(Token.TokenType.LBRACE, "{"),
                new Token(Token.TokenType.RBRACE, "}"),
                new Token(Token.TokenType.COMMA, ","),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.EOF, "")
        };

        for (Token expected : expectedTokens) {
            Token actual = lexer.nextToken();
            assertEquals(expected.getType(), actual.getType());
            assertEquals(expected.getLiteral(), actual.getLiteral());
        }
    }

    @Test
    void newToken() {
        Lexer lexer = new Lexer("");
        Token token = lexer.newToken(Token.TokenType.INT, "123");
        assertEquals(Token.TokenType.INT, token.getType());
        assertEquals("123", token.getLiteral());
    }


    @Test
    void testComplexLexing() {
        String input = """
                let five = 5;
                let ten = 10;

                let add = fn(x, y) {
                  x + y;
                };

                let result = add(five, ten);
                """;

        Lexer lexer = new Lexer(input);

        Token[] expectedTokens = {
                new Token(Token.TokenType.LET, "let"),
                new Token(Token.TokenType.IDENT, "five"),
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.INT, "5"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.LET, "let"),
                new Token(Token.TokenType.IDENT, "ten"),
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.INT, "10"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.LET, "let"),
                new Token(Token.TokenType.IDENT, "add"),
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.FUNCTION, "fn"),
                new Token(Token.TokenType.LPAREN, "("),
                new Token(Token.TokenType.IDENT, "x"),
                new Token(Token.TokenType.COMMA, ","),
                new Token(Token.TokenType.IDENT, "y"),
                new Token(Token.TokenType.RPAREN, ")"),
                new Token(Token.TokenType.LBRACE, "{"),
                new Token(Token.TokenType.IDENT, "x"),
                new Token(Token.TokenType.PLUS, "+"),
                new Token(Token.TokenType.IDENT, "y"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.RBRACE, "}"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.LET, "let"),
                new Token(Token.TokenType.IDENT, "result"),
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.IDENT, "add"),
                new Token(Token.TokenType.LPAREN, "("),
                new Token(Token.TokenType.IDENT, "five"),
                new Token(Token.TokenType.COMMA, ","),
                new Token(Token.TokenType.IDENT, "ten"),
                new Token(Token.TokenType.RPAREN, ")"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.EOF, "")
        };

        for (Token expected : expectedTokens) {
            Token actual = lexer.nextToken();
            assertEquals(expected.getType(), actual.getType());
            assertEquals(expected.getLiteral(), actual.getLiteral());
        }
    }
}

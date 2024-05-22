
public class language_interpreter {

	static int index;
	static String input;
	static char token;

	public static void main(String[] args) {
		//input = "x = 001;";
        //input = "x_2 = 0;";
        //input = "x = 0 y = x; z = ---(x+y);";
        input = "x = 0 y = x; z = ---(x+y);";
        index = 0;
        next_token();
        assignment();

	}
	static void next_token() {
		if (index == input.length())
			token = ';';
		else {
			token = input.charAt(index);
			index ++;
		}		
	}

	static void match(char in_token) {
		if (token == in_token)
			next_token();
		else
			error(in_token + " expected got " + token);
	}

	static void digit() {
		switch(token) {
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			next_token();
			return;
		default:
			error("expected 0-9");
		}
	}
	static void non_zero_digit() {
		switch(token) {
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			next_token();
			return;
		default:
			error("expected 1-9");
		}
	}
	static void identifier (){
		letter();
		switch (token){
		case '[':
			letter();
			digit();
			match(']');
			next_token();
			return;
        case '_':
		default:
			expression();
			return;
		}
	}
	static void letter () {
		switch(token) {
		case 'a':
		case 'A':
		case 'b':
		case 'B':
		case 'c':
		case 'C':
		case 'd':
		case 'D':
		case 'e':
		case 'E':
		case 'f':
		case 'F':
		case 'g':
		case 'G':
		case 'h':
		case 'H':
		case 'i':
		case 'I':
		case 'j':
		case 'J':
		case 'l':
		case 'L':
		case 'm':
		case 'M':
		case 'o':
		case 'O':
		case 'p':
		case 'P':
		case 'q':
		case 'Q':
		case 'r':
		case 'R':
		case 's':
		case 'S':
		case 't':
		case 'T':
		case 'u':
		case 'U':
		case 'v':
		case 'V':
		case 'w':
		case 'W':
		case 'x':
		case 'X':
		case 'y':
		case 'Y':
		case 'Z':
		case 'z':
        case '_':
			next_token();
			return;
		default:
			error("expected a-z or A-Z or _");
		}
	}
	static void expression() {
		term();
        expression_prime();
	}
	static void expression_prime() {
		switch (token) {
		case '+':
			term();
			expression_prime();
			next_token();
		case '-':
			term();
			expression_prime();
			next_token();
        case '_':
		default:
			error("expected + or -");
            return;
		}
	}
	static void term(){
		fact();
		term_prime();
	}
	static void term_prime(){
		if(token == '*') {
			fact();
			term_prime();
		} else {
            return;
        }
	}
	static void fact() {
		switch(token){
            case '(':
            next_token();
            expression();
            match (')');
            return;
            case '-':
            next_token();
            fact();
            case '+':
            next_token();
            fact();
            case '0':
            next_token();
            case '_':
            default:
            next_token();
        }
	}
	static void assignment() {
		identifier();
	}

	static void error (String msg) {
		throw new RuntimeException(msg);
	}

}


/* Generated By:JavaCC: Do not edit this line. SqlParserConstants.java */
package com.dbms.grammar;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface SqlParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int LINE_COMMENT = 7;
  /** RegularExpression Id. */
  int MULTI_LINE_COMMENT = 8;
  /** RegularExpression Id. */
  int CREATE = 9;
  /** RegularExpression Id. */
  int DATABASE = 10;
  /** RegularExpression Id. */
  int DROP = 11;
  /** RegularExpression Id. */
  int EXIT = 12;
  /** RegularExpression Id. */
  int FROM = 13;
  /** RegularExpression Id. */
  int INSERT = 14;
  /** RegularExpression Id. */
  int INTO = 15;
  /** RegularExpression Id. */
  int LIMIT = 16;
  /** RegularExpression Id. */
  int OFFSET = 17;
  /** RegularExpression Id. */
  int SELECT = 18;
  /** RegularExpression Id. */
  int SHOW = 19;
  /** RegularExpression Id. */
  int TABLE = 20;
  /** RegularExpression Id. */
  int USE = 21;
  /** RegularExpression Id. */
  int VALUES = 22;
  /** RegularExpression Id. */
  int WHERE = 23;
  /** RegularExpression Id. */
  int DATA_TYPE = 24;
  /** RegularExpression Id. */
  int INTEGER_LITERAL = 25;
  /** RegularExpression Id. */
  int FLOATING_POINT_LITERAL = 26;
  /** RegularExpression Id. */
  int EXPONENT = 27;
  /** RegularExpression Id. */
  int STRING_LITERAL = 28;
  /** RegularExpression Id. */
  int SEMICOLON = 29;
  /** RegularExpression Id. */
  int OPEN_BRACKET = 30;
  /** RegularExpression Id. */
  int CLOSE_BRACKET = 31;
  /** RegularExpression Id. */
  int COMMA = 32;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\r\\n\"",
    "\"\\\\\"",
    "\"\\t\"",
    "\" \"",
    "<LINE_COMMENT>",
    "<MULTI_LINE_COMMENT>",
    "\"CREATE \"",
    "\"DATABASE \"",
    "\"DROP \"",
    "\"EXIT\"",
    "\"FROM \"",
    "\"INSERT \"",
    "\"INTO \"",
    "\"LIMIT \"",
    "\"OFFSET \"",
    "\"SELECT \"",
    "\"SHOW \"",
    "\"TABLE \"",
    "\"USE \"",
    "\"VALUES \"",
    "\"WHERE \"",
    "<DATA_TYPE>",
    "<INTEGER_LITERAL>",
    "<FLOATING_POINT_LITERAL>",
    "<EXPONENT>",
    "<STRING_LITERAL>",
    "\";\"",
    "\"(\"",
    "\")\"",
    "\",\"",
  };

}

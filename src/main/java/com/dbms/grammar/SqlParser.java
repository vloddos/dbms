/* Generated By:JavaCC: Do not edit this line. SqlParser.java */
    package com.dbms.grammar;

    import com.dbms.structs.TypeDescription;

    import java.util.ArrayList;
    import java.util.LinkedHashMap;
    import java.util.Map;

    public class SqlParser implements SqlParserConstants {
        void run() throws Exception {
            while (true) {
                parse();
            }
        }

//____________________________________________Here main methods____________________________________________
  final public ArgsGuard parse() throws ParseException, Exception {
 ArgsGuard args = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CREATE:
      args = parseCreateDatabase();
         {if (true) return args;}
      break;
    case USE:
      args = parseUseDatabase();
         {if (true) return args;}
      break;
    case SHOW:
      args = parseShowCreateTable();
         {if (true) return args;}
      break;
    case INSERT:
      args = parseInsertIntoTable();
         {if (true) return args;}
      break;
    case SELECT:
      args = parseSelectFromTable();
         {if (true) return args;}
      break;
    case UPDATE:
      args = parseUpdate();
         {if (true) return args;}
      break;
    case DELETE:
      args = parseDelete();
         {if (true) return args;}
      break;
    case EXIT:
      args = parseExit();
         {if (true) return args;}
     {if (true) return args;}
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public ArgsGuard parseCreateDatabase() throws ParseException, Exception {
    Token name;
    ArgsGuard args = new ArgsGuard();
    jj_consume_token(CREATE);
    args.command = jj_consume_token(DATABASE);
    name = jj_consume_token(STRING_LITERAL);
    jj_consume_token(SEMICOLON);
        args.setName(name.image);

        if (args != null)
            {if (true) return args;}
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CREATE:
    case DELETE:
    case EXIT:
    case INSERT:
    case SELECT:
    case SHOW:
    case UPDATE:
    case USE:
      args = parse();
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
     {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  final public ArgsGuard parseUseDatabase() throws ParseException, Exception {
    Token name;
    ArgsGuard args = new ArgsGuard();
    args.command = jj_consume_token(USE);
    jj_consume_token(DATABASE);
    name = jj_consume_token(STRING_LITERAL);
    jj_consume_token(SEMICOLON);
        args.setName(name.image);
        if (args != null)
            {if (true) return args;}
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CREATE:
    case DELETE:
    case EXIT:
    case INSERT:
    case SELECT:
    case SHOW:
    case UPDATE:
    case USE:
      args = parse();
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
     {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  final public ArgsGuard parseCreateTable() throws ParseException, Exception {
    Token name;
    ArgsGuard args = new ArgsGuard();
    jj_consume_token(CREATE);
    args.command = jj_consume_token(TABLE);
    name = jj_consume_token(STRING_LITERAL);
    jj_consume_token(OPEN_BRACKET);
        args.setFields(getFields());
    jj_consume_token(CLOSE_BRACKET);
    jj_consume_token(SEMICOLON);
        args.setName(name.image);

        if (args != null)
            {if (true) return args;}
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CREATE:
    case DELETE:
    case EXIT:
    case INSERT:
    case SELECT:
    case SHOW:
    case UPDATE:
    case USE:
      args = parse();
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
     {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  final public ArgsGuard parseShowCreateTable() throws ParseException, Exception {
    Token name;
    ArgsGuard args = new ArgsGuard();
    args.command = jj_consume_token(SHOW);
    jj_consume_token(CREATE);
    jj_consume_token(TABLE);
    name = jj_consume_token(STRING_LITERAL);
    jj_consume_token(SEMICOLON);
        args.setName(name.image);

        if (args != null)
            {if (true) return args;}
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CREATE:
    case DELETE:
    case EXIT:
    case INSERT:
    case SELECT:
    case SHOW:
    case UPDATE:
    case USE:
      args = parse();
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
     {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  final public ArgsGuard parseInsertIntoTable() throws ParseException, Exception {
    Token name;

    ArrayList<String> insertableColumns = new ArrayList<String>();
    ArrayList<String> insertableValues = new ArrayList<String>();

    ArgsGuard args = new ArgsGuard();
    args.command = jj_consume_token(INSERT);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTO:
      jj_consume_token(INTO);
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    name = jj_consume_token(STRING_LITERAL);
    jj_consume_token(OPEN_BRACKET);
    insertableColumns = getColumns();
    jj_consume_token(CLOSE_BRACKET);
    jj_consume_token(VALUES);
    jj_consume_token(OPEN_BRACKET);
    insertableValues = getInsertableValues();
    jj_consume_token(CLOSE_BRACKET);
    jj_consume_token(SEMICOLON);
        args.setName(name.image);
        args.setInsertableColumns(insertableColumns);
        args.setInsertableValues(insertableValues);

        if (args != null)
            {if (true) return args;}
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CREATE:
    case DELETE:
    case EXIT:
    case INSERT:
    case SELECT:
    case SHOW:
    case UPDATE:
    case USE:
      args = parse();
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
     {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  final public ArgsGuard parseSelectFromTable() throws ParseException, Exception {
    Token name, limit = null, offset = null, star = null;

    ArrayList<String> insertableColumns = new ArrayList<String>();

    ArgsGuard args = new ArgsGuard();
    args.command = jj_consume_token(SELECT);
    insertableColumns = getColumns();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STAR:
      star = jj_consume_token(STAR);
      break;
    default:
      jj_la1[7] = jj_gen;
      ;
    }
    jj_consume_token(FROM);
    name = jj_consume_token(STRING_LITERAL);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LIMIT:
      jj_consume_token(LIMIT);
      limit = jj_consume_token(STRING_LITERAL);
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OFFSET:
      jj_consume_token(OFFSET);
      offset = jj_consume_token(STRING_LITERAL);
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
    jj_consume_token(SEMICOLON);
        args.setName(name.image);
        args.setInsertableColumns(insertableColumns);

        if (limit != null)
            args.setLimit(Integer.parseInt(limit.image));

        if (offset != null)
            args.setOffset(Integer.parseInt(offset.image));

        if (args != null)
            {if (true) return args;}
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CREATE:
    case DELETE:
    case EXIT:
    case INSERT:
    case SELECT:
    case SHOW:
    case UPDATE:
    case USE:
      args = parse();
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
     {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  final public ArgsGuard parseUpdate() throws ParseException, Exception {
    Token name, math, sign, updatableColumn, updatableValue,
        compareColumn = null, compareSign = null, compareValue = null, comparePredicat = null;

    ArrayList<String> updatableColumns = new ArrayList<String>();
    ArrayList<String> updatableValues = new ArrayList<String>();

    ArrayList<String> compareColumns = new ArrayList<String>();
    ArrayList<String> compareSigns = new ArrayList<String>();
    ArrayList<String> compareValues = new ArrayList<String>();
    ArrayList<String> comparePredicats = new ArrayList<String>();

    ArgsGuard args = new ArgsGuard();
    args.command = jj_consume_token(UPDATE);
    name = jj_consume_token(STRING_LITERAL);
    jj_consume_token(SET);
    updatableColumn = jj_consume_token(STRING_LITERAL);
    jj_consume_token(COMPARE);
    updatableValue = jj_consume_token(STRING_LITERAL);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MATH_OPERATION_LITEARAL:
      sign = jj_consume_token(MATH_OPERATION_LITEARAL);
            updatableValue.image += sign.image;
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }
        updatableColumns.add(updatableColumn.image);
        math = updatableValue;
        //updatableValues.add(updatableValue.image);

    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STRING_LITERAL:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_1;
      }
      updatableValue = jj_consume_token(STRING_LITERAL);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MATH_OPERATION_LITEARAL:
        sign = jj_consume_token(MATH_OPERATION_LITEARAL);
                    updatableValue.image += sign.image;
        break;
      default:
        jj_la1[13] = jj_gen;
        ;
      }
                math.image += updatableValue.image;
                //updatableValues.add(updatableValue.image);

    }
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_2;
      }
      jj_consume_token(COMMA);
      updatableColumn = jj_consume_token(STRING_LITERAL);
      jj_consume_token(COMPARE);
      updatableValue = jj_consume_token(STRING_LITERAL);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MATH_OPERATION_LITEARAL:
        sign = jj_consume_token(MATH_OPERATION_LITEARAL);
                updatableValue.image += sign.image;
        break;
      default:
        jj_la1[15] = jj_gen;
        ;
      }
            updatableValues.add(math.image);
            math.image = updatableValue.image;
            updatableColumns.add(updatableColumn.image);
            //updatableValues.add(updatableValue.image);

      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING_LITERAL:
          ;
          break;
        default:
          jj_la1[16] = jj_gen;
          break label_3;
        }
        updatableValue = jj_consume_token(STRING_LITERAL);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case MATH_OPERATION_LITEARAL:
          sign = jj_consume_token(MATH_OPERATION_LITEARAL);
                    updatableValue.image += sign.image;
          break;
        default:
          jj_la1[17] = jj_gen;
          ;
        }
                math.image += updatableValue.image;
                //updatableValues.add(updatableValue.image);

      }
    }
        updatableValues.add(math.image);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WHERE:
      jj_consume_token(WHERE);
      updatableValue = jj_consume_token(STRING_LITERAL);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMPARE:
        sign = jj_consume_token(COMPARE);
         updatableValue.image += sign.image;
        break;
      default:
        jj_la1[18] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MATH_OPERATION_LITEARAL:
        sign = jj_consume_token(MATH_OPERATION_LITEARAL);
         updatableValue.image += sign.image;
        break;
      default:
        jj_la1[19] = jj_gen;
        ;
      }
         math = updatableValue;
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING_LITERAL:
          ;
          break;
        default:
          jj_la1[20] = jj_gen;
          break label_4;
        }
        updatableValue = jj_consume_token(STRING_LITERAL);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMPARE:
          sign = jj_consume_token(COMPARE);
             updatableValue.image += sign.image;
          break;
        default:
          jj_la1[21] = jj_gen;
          ;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case MATH_OPERATION_LITEARAL:
          sign = jj_consume_token(MATH_OPERATION_LITEARAL);
              updatableValue.image += sign.image;
          break;
        default:
          jj_la1[22] = jj_gen;
          ;
        }
                math.image += updatableValue.image;
                //updatableValues.add(updatableValue.image);

      }
         compareValues.add(math.image);
      break;
    default:
      jj_la1[23] = jj_gen;
      ;
    }
    jj_consume_token(SEMICOLON);
        args.setName(name.image);
        args.setUpdatableColumns(updatableColumns);
        args.setUpdatableValues(updatableValues);

        args.setCompareColumns(compareColumns);
        args.setCompareSigns(compareSigns);
        args.setCompareValues(compareValues);
        args.setComparePredicats(comparePredicats);
        if (args != null)
            {if (true) return args;}
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CREATE:
    case DELETE:
    case EXIT:
    case INSERT:
    case SELECT:
    case SHOW:
    case UPDATE:
    case USE:
      args = parse();
      break;
    default:
      jj_la1[24] = jj_gen;
      ;
    }
     {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  final public ArgsGuard parseDelete() throws ParseException, Exception {
    Token name, math, sign,  updatableValue, compareColumn = null, compareSign = null, compareValue = null, comparePredicat = null, star = null;

    ArrayList<String> compareColumns = new ArrayList<String>();
    ArrayList<String> compareSigns = new ArrayList<String>();
    ArrayList<String> compareValues = new ArrayList<String>();
    ArrayList<String> comparePredicats = new ArrayList<String>();

    ArgsGuard args = new ArgsGuard();
    args.command = jj_consume_token(DELETE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STAR:
      star = jj_consume_token(STAR);
      break;
    default:
      jj_la1[25] = jj_gen;
      ;
    }
    jj_consume_token(FROM);
    name = jj_consume_token(STRING_LITERAL);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WHERE:
      jj_consume_token(WHERE);
      updatableValue = jj_consume_token(STRING_LITERAL);
         math = updatableValue;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMPARE:
        sign = jj_consume_token(COMPARE);
         updatableValue.image += sign.image;
        break;
      default:
        jj_la1[26] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MATH_OPERATION_LITEARAL:
        sign = jj_consume_token(MATH_OPERATION_LITEARAL);
         updatableValue.image += sign.image;
        break;
      default:
        jj_la1[27] = jj_gen;
        ;
      }
         math.image = updatableValue.image;
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING_LITERAL:
          ;
          break;
        default:
          jj_la1[28] = jj_gen;
          break label_5;
        }
        updatableValue = jj_consume_token(STRING_LITERAL);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMPARE:
          sign = jj_consume_token(COMPARE);
             updatableValue.image += sign.image;
          break;
        default:
          jj_la1[29] = jj_gen;
          ;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case MATH_OPERATION_LITEARAL:
          sign = jj_consume_token(MATH_OPERATION_LITEARAL);
              updatableValue.image += sign.image;
          break;
        default:
          jj_la1[30] = jj_gen;
          ;
        }
                math.image += updatableValue.image;
                //updatableValues.add(updatableValue.image);

      }
         compareValues.add(math.image);
      break;
    default:
      jj_la1[31] = jj_gen;
      ;
    }
    jj_consume_token(SEMICOLON);
        args.setName(name.image);
        args.setCompareColumns(compareColumns);
        args.setCompareSigns(compareSigns);
        args.setCompareValues(compareValues);
        args.setComparePredicats(comparePredicats);

        if (args != null)
            {if (true) return args;}
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CREATE:
    case DELETE:
    case EXIT:
    case INSERT:
    case SELECT:
    case SHOW:
    case UPDATE:
    case USE:
      args = parse();
      break;
    default:
      jj_la1[32] = jj_gen;
      ;
    }
     {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  final public ArgsGuard parseExit() throws ParseException {
    ArgsGuard args = new ArgsGuard();
    args.command = jj_consume_token(EXIT);
    jj_consume_token(SEMICOLON);
     {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

//____________________________________________Here helper methods____________________________________________
  final public ArrayList<String> getColumns() throws ParseException {
    Token columnName;

    ArrayList<String> insertableColumns = new ArrayList<String>();
    columnName = jj_consume_token(STRING_LITERAL);
     insertableColumns.add(columnName.image);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[33] = jj_gen;
        break label_6;
      }
      jj_consume_token(COMMA);
      columnName = jj_consume_token(STRING_LITERAL);
            insertableColumns.add(columnName.image);
    }
     {if (true) return insertableColumns;}
    throw new Error("Missing return statement in function");
  }

  final public ArrayList<String> getInsertableValues() throws ParseException {
    Token value;

    ArrayList<String> values = new ArrayList<String>();
    jj_consume_token(SINGLE_MARK);
    value = jj_consume_token(STRING_LITERAL);
    jj_consume_token(SINGLE_MARK);
     values.add(value.image);
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[34] = jj_gen;
        break label_7;
      }
      jj_consume_token(COMMA);
      jj_consume_token(SINGLE_MARK);
      value = jj_consume_token(STRING_LITERAL);
      jj_consume_token(SINGLE_MARK);
            values.add(value.image);
    }
     {if (true) return values;}
    throw new Error("Missing return statement in function");
  }

  final public Map<String, TypeDescription> getFields() throws ParseException {
    Token name, TypeName;
    TypeDescription typeDescription;

    Map<String, TypeDescription> fields = new LinkedHashMap<String, TypeDescription>();
    name = jj_consume_token(STRING_LITERAL);
        fields.put(name.image, getTypeDescription());
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[35] = jj_gen;
        break label_8;
      }
      jj_consume_token(COMMA);
      TypeName = jj_consume_token(STRING_LITERAL);
      typeDescription = getTypeDescription();
            fields.put(TypeName.image, typeDescription);
    }
     {if (true) return fields;}
    throw new Error("Missing return statement in function");
  }

  final public TypeDescription getTypeDescription() throws ParseException {
    Token type, length = null;

    TypeDescription typeDescription;
    type = jj_consume_token(STRING_LITERAL);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OPEN_BRACKET:
      jj_consume_token(OPEN_BRACKET);
      length = jj_consume_token(STRING_LITERAL);
      jj_consume_token(CLOSE_BRACKET);
      break;
    default:
      jj_la1[36] = jj_gen;
      ;
    }
        typeDescription = new TypeDescription(type.image);

        if (length != null)
            typeDescription.setLength(length.image);

        {if (true) return typeDescription;}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public SqlParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[37];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x6a2a80,0x6a2a80,0x6a2a80,0x6a2a80,0x6a2a80,0x4000,0x6a2a80,0x10000000,0x8000,0x10000,0x6a2a80,0x8000000,0x4000000,0x8000000,0x0,0x8000000,0x4000000,0x8000000,0x2000000,0x8000000,0x4000000,0x2000000,0x8000000,0x1000000,0x6a2a80,0x10000000,0x2000000,0x8000000,0x4000000,0x2000000,0x8000000,0x1000000,0x6a2a80,0x0,0x0,0x0,0x80000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2,0x2,0x2,0x0,};
   }

  /** Constructor with InputStream. */
  public SqlParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public SqlParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new SqlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 37; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 37; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public SqlParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new SqlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 37; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 37; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public SqlParser(SqlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 37; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(SqlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 37; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[34];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 37; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 34; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

    }

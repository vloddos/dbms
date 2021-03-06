package com.dbms.scripting;

import javafx.util.Pair;

import java.util.Map;
import java.util.regex.Pattern;

// TODO: 24.01.2019 сделать выражения на уровне парсера а не заговнокодить через евал
public class Expression {

    private String value;
    private String valueForEval;
    private Map<String, Pair<String, Integer>> fields;

    public Expression(String value, Map<String, Pair<String, Integer>> fields) throws RuntimeException {
        if (value == null) {
            this.value = null;
            this.valueForEval = null;
            this.fields = fields;
            return;
        }

        var eaReplaced = Pattern.compile("(?<![:><])=").matcher(value.toLowerCase()).replaceAll(mr -> "==").replaceAll(":=", "=");
        var identifierReplaced = Pattern.compile("['a-zA-Z_]\\w+").matcher(eaReplaced).replaceAll(
                mr -> {
                    var identifier = mr.group();
                    if (identifier.contains("'"))
                        return identifier;

                    switch (identifier) {
                        case "and":
                            return "&&";
                        case "or":
                            return "||";
                        case "not":
                            return "!";
                        case "true":
                        case "false":
                            return identifier;
                        default://field name
                            var p = fields.get(identifier);
                            if (p == null)
                                throw new RuntimeException(String.format("Unknown field '%s' in expression '%s'", identifier, value));
                            var r = p.getKey();
                            var i = p.getValue();
                            return String.format("%s.get(%s)", r, i);
                    }
                }
        );

        this.value = value;
        this.valueForEval = identifierReplaced;
        this.fields = fields;
    }

    public String getValue() {
        return value;
    }

    public String getValueForEval() {
        return valueForEval;
    }

    public Map<String, Pair<String, Integer>> getFields() {
        return fields;
    }
}
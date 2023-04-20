package com.onz.config;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class CustomMysqlDialect extends MySQL8Dialect {

    public CustomMysqlDialect() {
        registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
        registerFunction("SHA2", new StandardSQLFunction("SHA2", StandardBasicTypes.STRING));
        registerFunction("AES_ENCRYPT", new StandardSQLFunction("AES_ENCRYPT", StandardBasicTypes.BLOB));
        registerFunction("CONCAT", new StandardSQLFunction("CONCAT", StandardBasicTypes.STRING));
    }
}

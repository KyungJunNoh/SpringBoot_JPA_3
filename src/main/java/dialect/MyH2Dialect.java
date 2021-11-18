package dialect;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

// H2Dialect를 커스텀 , '사용자 정의 함수'
public class MyH2Dialect extends H2Dialect {

    // 함수를 등록
    public MyH2Dialect(){
        registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}

```
该插件的支持程度说明

对于xml的操作全量支持
    * 对象类型的参数需要在属性中添加注解@CryptField
    * 基本类型的参数，需要配合@Param+@CryptField

对于Mybatis-plus的BaseMapper中的方法
    * 支持参数列表中仅有一个参数且不带注解的，如insert
    * 支持除分页参数RowBounds外，参数列表中有且只有一个参数ew或者et的，ew不支持条件构造器写法，仅支持在ew的构造函数中放入对象
```
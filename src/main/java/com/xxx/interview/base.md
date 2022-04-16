1. jdk, jre, jvm区别
    + JDK：java development kit针对java程序员的产品；
    + JRE：java runtime environment是运行java的环境集合；
    + JVM：java虚拟机用于运行java字节码，跨平台的核心；
   
2. 常用数字类型的区别
   + byte 1个字节 -128～127
   + short 2字节   -32768～32767
   + int   4字节   -2147483648～2147483648
   + long  8字节
   + float  4字节
   + double  8字节
   
3. float在jvm的表达式及使用陷阱
   ```
   float d1 = 423423423f;
   float d2 = d1 + 1;
   if (d1 == d2) {
      Sustem.out.println("d1==d2");
   } else {
   Sustem.out.println("d1==d2");
   }
   ```
   + 打印结果为：d1 == d2;
   + float类型在内存中的存储形式为科学计数法，表达为：4.2342342E7;
   + d2用科学计数法表示同样为4.2342342E7;
   + 更高精度使用double类型（推荐），银行类业务：数据大精度高请使用*BigDecimal类*；

4. 随机生成30～100之间的整数；
   ```
   public static Integer randomInt1() {
        int min = 30;
        int max = 100;
        //  Random().nextInt(upperLimit)
        return new Random().nextInt(max - min) + min;
    }

    public static int randomInt2() {
        int min = 30;
        int max = 100;
        // Math.random() 返回 0.0~1.0 之间的数
        return (int) (Math.random() * (max - min)) + min;
    }
   ```

5. 列出1～1000的质数
   ```
   public static void primeNumber1() {
        long start = System.currentTimeMillis();
        for (int i = 2; i < 10000; i ++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(i);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    // 推荐效率更高
    public static void primeNumber2() {
        long start = System.currentTimeMillis();
        for (int i = 2; i < 10000; i++) {
            boolean isPrime = true;
            for (int j = 2; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(i);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
   ```
   
6. 面向对象的三大特征：
   + 封装：把对象的属性和（服务）结合为一个整体，尽可能的隐藏对象内部的实现细节。
      - 对象的数据封装特性彻底消除了传统结构方法中数据与操作分离所带来的种种问题，提高了程序的可复用性和可维护性，降低了程序员保持数据与 操作内容的负担。
      - 对象的数据封装特性还可以把对象的私有数据和公共数据分离开，保护了私有数据，减少了可能的模块间干扰，达到降低程序复杂性、提高可控性的目的。
   + 继承：继承表现的是特殊与一般的关系，常说的is-a的关系，子类继承父类，表明子类是一种特殊的父类，可以具有父类不具有的属性和方法。（子类不能继承父类中用private修饰的变量和方法。）
   + 多态：直白些就是 相同的事物，调用相同的方法，参数也相同时，但表现的行为却不同。
      - 实现多态有三个必要条件：继承、重写、向上转型。
      - 基于继承实现的多态；
      - 基于接口实现的多态。
      - 多态的弊端：不能使用子类特有的成员属性和子类特有的成员方法。
      - 多态的好处：程序的可扩展性及可维护性增强。

7. 抽象类：在Java中被abstract关键字修饰的类称为抽象类，被abstract关键字修饰的方法称为抽象方法，抽象方法只有方法的声明，没有方法体。
   + 抽象类不能被实例化只能被继承；
   + 包含抽象方法的一定是抽象类，但是抽象类不一定含有抽象方法；
   + 抽象类中的抽象方法的修饰符只能为public或者protected，默认为public；
   + 一个子类继承一个抽象类，则子类必须实现父类抽象方法，否则子类也必须定义为抽象类；
   + 抽象类可以包含属性、方法、构造方法，但是构造方法不能用于实例化，主要用途是被子类调用。

8. Java中接口使用interface关键字修饰，特点为:
   + 接口可以包含变量、方法；变量被隐式指定为public static final，方法被隐式指定为public abstract（JDK1.8之前）；
   + 接口支持多继承，即一个接口可以extends多个接口，间接的解决了Java中类的单继承问题；
   + 一个类可以实现多个接口；
   + JDK1.8中对接口增加了新的特性：
      - 默认方法（default method）：
         * JDK 1.8允许给接口添加非抽象的方法实现，但必须使用default关键字修饰；
         * 定义了default的方法可以不被实现子类所实现，但只能被实现子类的对象调用；
         * 如果子类实现了多个接口，并且这些接口包含一样的默认方法，则子类必须重写默认方法；
      - 静态方法（static method）：
         * JDK 1.8中允许使用static关键字修饰一个方法，并提供实现，称为接口静态方法。接口静态方法只能通过接口调用（接口名.静态方法名）。

9. 接口与抽象类的区别
   * 相同点：
      + 都不能被实例化;
      + 接口的实现类或抽象类的子类都只有实现了接口或抽象类中的方法后才能实例化;
   * 不同点：
      + 接口只有定义，不能有方法的实现，java 1.8中可以定义default方法体，而抽象类可以有定义与实现，方法可在抽象类中实现。
      + 实现接口的关键字为implements，继承抽象类的关键字为extends。一个类可以实现多个接口，但一个类只能继承一个抽象类。所以，使用接口可以间接地实现多重继承。
      + 接口强调特定功能的实现，而抽象类强调所属关系。
      + 接口成员变量默认为public static final，必须赋初值，不能被修改；其所有的成员方法都是public、abstract的。抽象类中成员变量默认default，可在子类中被重新定义，也可被重新赋值；抽象方法被abstract修饰，不能被private、static、synchronized和native等修饰，必须以分号结尾，不带花括号。

10. 静态变量和实例变量：
    + 静态变量：静态变量用 static字符修饰，随着类的加载而加载，静态变量存放在方法池中的静态区，可以直接通过"类名.变量名直接"调用，也可以通过类的对象调用。
    + 实例变量：实例变量相当于该类的属性，需要 "new对象 "才能被调用。而且该变量不常驻内存，当这个类不再被使用时会java回收机制所释放。
    + 区别：
        - 语法定义方面：静态变量前需要加static关键字修饰，实例变量前不加。
        - 存储区域不同：静态变量存储在静态存储区域，实例变量存储在堆中，会被释放。
        - 程序运行方面：实例变量属于某个对象的属性，必须创建了实例对象，其中的实例变量才被分配空间，才能使用这个实例变量。静态变量属于类，只要程序加载了类的字节码而不需要创建实例对象，静态变量就会被分配空间，就可以被使用。

    ```
    public class VariableTest {
    
        // 静态变量
        public static int staticVar = 0;
    
        // 实例变量
        public int instanceVar = 0;
    
        public VariableTest() {
    
            staticVar++;
            instanceVar++;
            System.out.println("staticVar=" + staticVar + "," + "instanceVar=" + instanceVar);
        }
    
        /**
         * 无论创建多少个实例对象，永远只分配一个staticVar变量，每创建一个实例对象，这个staticVar就会加1
         * 每创建一个实例对象，就会分配一个instanceVar，每个instanceVar的的值都只自加1
         * 
         * @param args
         */
        public static void main(String[] args) {
    
            // staticVar=1,instanceVar=1
            VariableTest v1 = new VariableTest();
    
            // staticVar=2,instanceVar=1
            VariableTest v2 = new VariableTest();
        }
    }
    ```



   


学习笔记





## 堆
堆内存是所有线程共享，存储Java程序中创建的所有对象，可以被所有线程访问，只要他们能拿到对象的引用地址。
不管是哪个线程创建的对象，都保存在堆中。
包装类型的变量（Byte，Integer，Long等）也是在堆中。
对象的成员变量和对象本身一起存在堆上，，不管成员变量的类型是原生数值还是对象引用。
类的静态变量和类定义一样保存在堆中。

## 栈
2.每个线程都有独立的线程栈。
每个线程只能访问自己的线程栈，不能访问其他线程其他线程的局部变量。
对于局部变量,如果是原生数据类型，内容保存在线程栈上，
如果是对象类型，栈中的局部变量槽位中保存着对象的引用地址，对象的实际内容保存在堆中



总结：方法中使用的原生数据类型和对象引用地址在栈上存储；对象，对象成员和类定义，静态变量存储在堆上。

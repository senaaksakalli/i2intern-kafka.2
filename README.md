# i2intern-kafka.2
# Java Producer-Consumer 

Bu proje, **i2i Systems** staj programı kapsamında,  nesne yayınlama ve okuma işlemlerini gerçekleştiren iki Java programından oluşmaktadır. Producer programı, `Student` sınıfını kullanarak Java nesnelerini üretir ve bu nesneleri Consumer programına gönderir. Consumer ise bu nesneleri alır ve konsola yazdırır.

## Proje İçeriği

Bu proje üç ana Java dosyasından oluşmaktadır:

1. **Student.java**: Nesne üretimi için kullanılan sınıf. Bu sınıf, öğrencinin adını ve ID'sini tutar.
2. **Producer.java**: `Student` nesnelerini üretir ve bunları bir soket aracılığıyla Consumer'a gönderir.
3. **Consumer.java**: Producer tarafından gönderilen nesneleri alır ve konsola yazdırır.
